package io.violabs.picard

import io.violabs.picard.domain.Container
import io.violabs.picard.domain.KubeletConfiguration
import io.violabs.picard.domain.Pod

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

    fun properties(key: String, items: List<String>?) {
        property(key, items)
    }

    private fun formatValue(value: Any?): String {
        return when (value) {
            null -> ""
            is String -> if (value.contains("\n")) "|\n${
                value.lines().joinToString("\n") { " ".repeat(indentation + 2) + it }
            }" else value
            is List<*> -> value.joinToString(", ", "[", "]") {
                val item = it.toString().replace("\'", "\"")
                "'$item'"
            }

            else -> value.toString()
        }
    }

    fun String.wrap(): String = "\"$this\""

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

fun buildPodYamlContent(pod: Pod): String = buildYaml {
    property("apiVersion", pod.apiVersion)
    property("kind", pod.kind)
    property("metadata") {
        property("name", pod.metadata.name)
    }
    property("spec") {
        pod.spec.containers?.let { containers ->
            containers(pod.spec.containers)
        }

        pod.spec.template?.let { template ->
            property("template") {
                property("spec") {
                    containers(template.spec?.containers)

                    template.spec?.restartPolicy?.let { restartPolicy ->
                        property("restartPolicy", restartPolicy)
                    }
                }
            }
        }
    }
}

fun YamlBuilder.containers(containers: List<Container>?) {
    properties("containers") {
        containers?.forEach {
            listItem("name", it.name, listDelimiter = true)
            listItem("image", it.image)
            it.ports?.let { ports ->
                properties("ports") {
                    ports.forEach { port ->
                        listItem("containerPort", port.containerPort, listDelimiter = true)
                    }
                }
            }

            it.command?.let { command -> properties("command", command) }
        }
    }
}

fun buildKubeletConfigYamlContent(config: KubeletConfiguration): String = buildYaml {
    property("apiVersion", config.apiVersion)
    property("kind", config.kind)
    property("address", config.address.wrap())
    property("port", config.port)
    property("serializeImagePulls", config.serializeImagePulls)
    property("evictionHard") {
        val evictionHard = config.evictionHard
        property("memory.available", evictionHard?.memory?.available?.wrap())
        val nodeFs = evictionHard?.nodefs
        property("nodefs.available", nodeFs?.available?.wrap())
        property("nodefs.inodesFree", nodeFs?.inodesFree?.wrap())
        val imageFs = evictionHard?.imagefs
        property("imagefs.available", imageFs?.available?.wrap())
        property("imagefs.inodesFree", imageFs?.inodesFree?.wrap())
    }
}