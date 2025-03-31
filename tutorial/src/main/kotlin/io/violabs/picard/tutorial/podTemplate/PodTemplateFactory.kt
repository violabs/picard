package io.violabs.picard.tutorial.podTemplate

import io.violabs.picard.*

object PodTemplateFactory {
    fun buildPodTemplateDsl(): String {
        val config = buildPod {
            apiVersion = Pod.Version.BATCH_V1
            kind = Kind.JOB
            metadata {
                name = "hello"
            }
            spec {
                template {
                    spec {
                        containers {
                            container {
                                name = "hello"
                                image = "busybox:1.28"
                                command = listOf("sh", "-c", "echo 'Hello, Kubernetes!' && sleep 3600")
                            }
                        }
                        restartPolicy = RestartPolicy.ON_FAILURE
                    }
                }
            }
        }

        return buildPodYamlContent(config)
    }
}