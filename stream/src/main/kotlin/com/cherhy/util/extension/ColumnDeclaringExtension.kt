package com.cherhy.util.extension

import org.ktorm.dsl.like
import org.ktorm.expression.ArgumentExpression
import org.ktorm.expression.BinaryExpression
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.VarcharSqlType

infix fun ColumnDeclaring<*>.contains(
    value: String,
): BinaryExpression<Boolean> {
    return this like ArgumentExpression("%$value%", VarcharSqlType)
}