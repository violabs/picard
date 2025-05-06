package io.violabs.picard.tutorial.podTemplate

import io.violabs.picard.*
import io.violabs.picard.domain.RestartPolicy

object PodTemplateFactory {
    fun buildPodTemplateDsl(): String {
        val config = buildManifest {
            workloadSection {
                job {
                    metadata {
                        name = "hello"
                    }

                    spec {
                        template {
                            templateSpec {
                                restartPolicy = RestartPolicy.OnFailure
                                containers {
                                    container {
                                        name = "hello"
                                        image = "busybox:1.28"
                                        command("sh", "-c", "echo \"Hello, Kubernetes!\" && sleep 3600")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return YamlBuilder.build(config)
    }
}