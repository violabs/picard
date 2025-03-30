package io.violabs.picard

class YamlBuilder {
    private val content = StringBuilder()
    private var indentation = 0

    fun indent(block: YamlBuilder.() -> Unit) {
        indentation += 2
        this.block()
        indentation -= 2
    }

    fun indent(
        key: String,
        value: Any,
        block: YamlBuilder.() -> Unit
    ) {
        property(key, value)
        indent {
            block()
        }
    }

    fun property(key: String, value: Any? = null, listItem: Boolean = false) {
        val prefix = if (listItem) "- " else ""

        content.append(" ".repeat(indentation))
            .append("$prefix$key: ")
            .append(formatValue(value))
            .append("\n")
    }

    fun property(key: String, block: YamlBuilder.() -> Unit) {
        property(key)
        indent {
            block()
        }
    }

    fun properties(
        key: String,
        block: ListItem.() -> Unit
    ) {
        property(key)
        indent {
            ListItem().block()
        }
    }

    private fun formatValue(value: Any?): String {
        return when (value) {
            null -> ""
            is String -> if (value.contains("\n")) "|\n${
                value.lines().joinToString("\n") { " ".repeat(indentation + 2) + it }
            }" else value

            else -> value.toString()
        }
    }

    fun build(): String = content.toString()

    inner class ListItem {
        fun listItem(key: String, value: Any?, listDelimiter: Boolean = false) {
            var indentChange = 0
            val prefix = if (listDelimiter) {
                indentChange = 2
                "-"
            } else {
                indentChange = 1
                ""
            }

            indentation -= indentChange
            content.append(" ".repeat(indentation))
                .append("$prefix $key: ")
                .append(formatValue(value))
                .append("\n")
            indentation += indentChange
        }
    }
}

fun buildYaml(block: YamlBuilder.() -> Unit): String {
    val builder = YamlBuilder()
    builder.block()
    return builder.build()
}