package io.violabs.picard.cmd

import io.violabs.picard.cmd.dsl.Kubectl
import io.violabs.picard.cmd.dsl.kubectl

private const val USE_EXAMPLE = false

private const val GENERATED_DIR = "tutorial/src/main/resources/generated"

fun main(vararg args: String) = kubectl {
    if (USE_EXAMPLE) {
        examples(Example.DELETE_SIMPLE_POD)
        return@kubectl
    }

    val fileName = "$GENERATED_DIR/pod-sidecar.yaml"

    applyPod {
        fileLocation = fileName
        enabled = true
    }

    getPods {
        allNameSpaces()
        enabled = false
    }

    describePod {
        fileLocation = fileName
        enabled = false
    }

    deletePod {
        name = "myapp-pod"
        enabled = false
    }

    logs {
        name = "myapp-pod"
        container = "init-mydb"
        enabled = false
    }
}

private fun Kubectl.examples(example: Example) {
    when(example) {
        Example.APPLY_SIMPLE_POD -> {
            applyPod {
                fileLocation = "tutorial/basic/simple-pod.yml"
            }
        }
        Example.WIDE_ALL_NAME_SPACES -> {
            getPods {
                allNameSpaces()
                wide()
            }
        }
        Example.LABEL_FILTER -> {
            getPods {
                filter {
                    byLabel("app", "nginx")
                }
            }
        }
        Example.NAMESPACE_FILTER -> {
            getPods {
                filter {
                    byNamespace("kube-system")
                }
            }
        }
        Example.SORT_BY_CREATION_TIMESTAMP -> {
            getPods {
                sortBy = ".metadata.creationTimestamp"
            }
        }
        Example.DELETE_SIMPLE_POD -> {
            deletePod {
                name = "nginx"
            }
        }
    }
}

enum class Example {
    APPLY_SIMPLE_POD,
    WIDE_ALL_NAME_SPACES,
    LABEL_FILTER,
    NAMESPACE_FILTER,
    SORT_BY_CREATION_TIMESTAMP,
    DELETE_SIMPLE_POD,
}