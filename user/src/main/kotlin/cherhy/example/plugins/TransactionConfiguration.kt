package cherhy.example.plugins

import cherhy.example.util.TransactionType
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> reactiveTransaction(
    transactionType: TransactionType = TransactionType.WRITE,
    block: suspend () -> T,
): T {
    val database =
        if (transactionType == TransactionType.WRITE) DatabaseConfig.masterDatabase
        else DatabaseConfig.slaveDatabase

    return newSuspendedTransaction(Dispatchers.IO, database) { block() }
}