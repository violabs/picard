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

    applyPod {
        fileLocation = "$GENERATED_DIR/pod-template-dsl.yaml"
    }

    getPods {
        allNameSpaces()
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