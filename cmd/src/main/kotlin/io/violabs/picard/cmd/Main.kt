package io.violabs.picard.cmd

import io.violabs.picard.cmd.dsl.Kubectl
import io.violabs.picard.cmd.dsl.kubectl

//# Filter pods by label
//kubectl get pods -l app=nginx
//
//# Filter pods by namespace
//kubectl get pods -n <namespace-name>
//
//# Sort pods by creation time
//kubectl get pods --sort-by=.metadata.creationTimestamp
fun main(vararg args: String) = kubectl {

//    examples(Example.LABEL_FILTER)

    getPods {
        filter {
            byNamespace("default")
        }
    }
}

private fun Kubectl.examples(example: Example) {
    when(example) {
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
        Example.APPLY_SIMPLE_POD -> {
            applyPod {
                fileLocation = "tutorial/basic/simple-pod.yml"
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
    WIDE_ALL_NAME_SPACES,
    LABEL_FILTER,
    NAMESPACE_FILTER,
    APPLY_SIMPLE_POD,
    DELETE_SIMPLE_POD,
}