package cherhy.example.util.extension

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.statements.InsertStatement

fun <T : Comparable<T>> InsertStatement<Number>.generatedKey(
    expression: Expression<EntityID<T>>,
) =
    this.resultedValues?.getOrNull(0)?.get(expression)?.value
        ?: throw IllegalArgumentException("No generated key returned")