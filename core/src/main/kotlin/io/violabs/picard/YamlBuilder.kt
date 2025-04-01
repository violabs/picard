package io.violabs.picard

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.domain.*

class YamlBuilder : DefaultLogger(YamlBuilder::class) {
    internal val content = StringBuilder()
    internal var indentation = 0

    fun property(key: String, value: Any? = null, listItem: Boolean = false, skipIndent: Boolean = false) {
        val prefix = if (listItem) "- " else ""

        val indent = if (skipIndent) 0 else indentation

        content.append(" ".repeat(indent))
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

    private fun indent(block: YamlBuilder.() -> Unit) {
        indentation += 2
        this.block()
        indentation -= 2
    }

    internal fun formatValue(value: Any?): String {
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
        fun listItem(
            key: String,
            value: Any?,
            listDelimiter: Boolean = false
        ) {
            // Create a special indentation for list items to ensure proper alignment
            val actualIndentation = indentation

            val prefix = if (listDelimiter) "-" else ""

            content.append(" ".repeat(actualIndentation))
                .append("$prefix $key: ")
                .append(formatValue(value))
                .append("\n")
        }
    }
}

fun buildYaml(block: YamlBuilder.() -> Unit): String {
    val builder = YamlBuilder()
    builder.block()
    return builder.build()
}

fun buildPodYamlContent(manifest: Manifest): String {
    return manifest.resources.joinToString("\n---\n") { buildPodYamlContent(it) }
}

fun buildPodYamlContent(resource: PodResource): String = buildYaml {
    property("apiVersion", resource.apiVersion)
    property("kind", resource.kind)
    property("metadata") {
        val metadata = resource.metadata

        metadata.name?.let {
            property("name", it)
        }

        metadata.labels?.let { labels ->
            property("labels") {
                labels.forEach { (key, value) ->
                    property(key, value)
                }
            }
        }
    }
    property("spec") {
        val spec = resource.spec

        renderSpec(resource.spec) {
            spec.template?.let { template ->
                property("template") {
                    log.debug(template)
                    template.metadata?.let { metadata ->
                        property("metadata") {
                            log.debug(metadata)
                            metadata.labels?.let { labels ->
                                property("labels") {
                                    labels.forEach { (key, value) ->
                                        property(key, value)
                                    }
                                }
                            }
                        }
                    }
                    property("spec") {
                        renderSpec(template.spec)
                    }
                }
            }
        }
    }

    resource.status?.let { status ->
        property("status") {
            properties("conditions") {
                status.conditions.forEach { condition ->
                    listItem("type", condition.type, listDelimiter = true)
                    listItem("status", condition.status)
                    listItem("lastProbeTime", condition.lastProbeTime)
                    listItem("lastTransitionTime", condition.lastTransitionTime)
                }
            }

            status.containerStatus?.let { containerStatus ->
                properties("containerStatuses") {
                    listItem("containerID", containerStatus.containerId, listDelimiter = true)
                    listItem("ready", containerStatus.ready)
                }
            }
        }
    }
}

fun YamlBuilder.renderSpec(spec: Spec, scope: Spec.() -> Unit = {}) {
    spec.restartPolicy?.let { restartPolicy ->
        property("restartPolicy", restartPolicy)
    }

    spec.replicas?.let { replicas ->
        property("replicas", replicas)
    }

    spec.selector?.let { selector ->
        property("selector") {
            properties("matchLabels") {
                selector.matchLabels.forEach { (key, value) ->
                    property(key, value)
                }
            }
        }
    }

    spec.containers?.let { containers -> renderContainers(containers) }

    spec.initContainers?.let { initContainers ->
        renderContainers(initContainers, "initContainers")
    }

    spec.readinessGates?.let { readinessGates ->
        properties("readinessGates") {
            readinessGates.forEach { readinessGate ->
                listItem(
                    "conditionType",
                    readinessGate.conditionType,
                    listDelimiter = true
                )
            }
        }
    }

    spec.ports?.let { ports ->
        // Use custom port formatting with proper indentation
        renderPorts(ports)
    }

    spec.volumes?.let { volumes ->
        properties("volumes") {
            volumes.forEach { volume ->
                property("- name", volume.name)
                property("  emptyDir", volume.emptyDir)
            }
        }
    }

    spec.scope()
}

// Custom function to render ports with correct indentation
fun YamlBuilder.renderPorts(ports: List<ServicePortConfig>) {
    property("ports") {
        ports.forEachIndexed { i, port ->
            // First line with the dash
            val headerIndent = indentation
            content.append(" ".repeat(headerIndent))
                .append("- protocol: ")
                .append(port.protocol)
                .append("\n")

            // Indented properties under the list item
            val propertyIndent = indentation + 2
            content.append(" ".repeat(propertyIndent))
                .append("port: ")
                .append(port.port)
                .append("\n")

            content.append(" ".repeat(propertyIndent))
                .append("targetPort: ")
                .append(port.targetPort)
                .append("\n")
        }
    }
}

// Container rendering with correct indentation
fun YamlBuilder.renderContainers(containers: List<Container>?, key: String = "containers") {
    property(key) {
        // This creates an indentation level for the containers list
        containers?.forEach { container ->
            // Convert the container's properties to YAML format with proper indentation
            val containerName = " ".repeat(indentation) + "- name: " + container.name
            content.append(containerName).append("\n")

            // Extra indentation for properties under a list item
            val imageIndent = indentation + 2  // Add 2 spaces for list item properties
            val imageProperty = " ".repeat(imageIndent) + "image: " + container.image
            content.append(imageProperty).append("\n")

            container.restartPolicy?.let { restartPolicy ->
                val restartPolicyIndent = indentation + 2
                val restartPolicyProperty = " ".repeat(restartPolicyIndent) + "restartPolicy: " + restartPolicy
                content.append(restartPolicyProperty).append("\n")
            }

            container.command?.let { command ->
                val commandIndent = indentation + 2
                val commandProperty = " ".repeat(commandIndent) + "command: " + formatValue(command)
                content.append(commandProperty).append("\n")
            }

            container.ports?.let { ports ->
                val portsIndent = indentation + 2
                val portsProperty = " ".repeat(portsIndent) + "ports:"
                content.append(portsProperty).append("\n")

                ports.forEach { port ->
                    val portIndent = indentation + 4  // Another level deeper
                    val portProperty = " ".repeat(portIndent) + "- containerPort: " + port.containerPort
                    content.append(portProperty).append("\n")
                }
            }

            container.volumeMounts?.let { volumeMounts ->
                val volumeMountsIndent = indentation + 2
                val volumeMountsProperty = " ".repeat(volumeMountsIndent) + "volumeMounts:"
                content.append(volumeMountsProperty).append("\n")

                volumeMounts.forEach { volumeMount ->
                    val volumeMountIndent = indentation + 4
                    val volumeMountNameProperty = " ".repeat(volumeMountIndent) + "- name: " + volumeMount.name
                    val volumeMountPathProperty =
                        " ".repeat(volumeMountIndent) + "  mountPath: " + volumeMount.mountPath
                    content.append(volumeMountNameProperty).append("\n")
                    content.append(volumeMountPathProperty).append("\n")
                }
            }
        }
    }
}

fun buildKubeletConfigYamlContent(config: KubeletConfiguration): String = buildYaml {
    property("apiVersion", config.apiVersion)
    property("kind", config.kind)
    config.crashLoopBackOff?.let { clbo ->
        property("crashLoopBackOff") {
            property("maxContainerRestartPeriod", clbo.maxContainerRestartPeriod.wrap())
        }
    }
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