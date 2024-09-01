package cherhy.example.plugins

import cherhy.example.util.DatabaseType
import cherhy.example.util.TransactionType
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.StatementInterceptor
import org.jetbrains.exposed.sql.statements.StatementType
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> reactiveTransaction(
    transactionType: TransactionType = TransactionType.WRITE,
    block: suspend () -> T,
): T =
    if (transactionType == TransactionType.WRITE) {
        newSuspendedTransaction(Dispatchers.IO, DatabaseType.masterDatabase) {
            block()
        }
    } else {
        newSuspendedTransaction(Dispatchers.IO, DatabaseType.slaveDatabase) {
            TransactionManager.current().registerInterceptor(TransactionInterceptor)
            block()
        }
    }

private object TransactionInterceptor : StatementInterceptor {
    override fun beforeExecution(
        transaction: Transaction,
        context: StatementContext,
    ) =
        when (context.statement.type) {
            StatementType.INSERT,
            StatementType.UPDATE,
            StatementType.DELETE,
            -> throw IllegalStateException("Read-only transaction is not allowed to execute write operations.")
            else -> Unit
        }
}