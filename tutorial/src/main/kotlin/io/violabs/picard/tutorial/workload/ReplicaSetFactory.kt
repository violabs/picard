package io.violabs.picard.tutorial.workload

import io.violabs.picard.buildManifestContent
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.Label
import io.violabs.picard.domain.PodResource

object ReplicaSetFactory {

    fun buildSimpleReplicaSet(): String = buildManifestContent {
        val tier = Label("tier", "frontend")

        resource {
            apiVersion = PodResource.Version.APPS_V1
            kind = Kind.REPLICA_SET
            metadata {
                name = "frontend"
                labels {
                    label("app", "guestbook")
                    label(tier)
                }
            }

            spec {
                replicas = 3
                selector {
                    matchLabels {
                        label(tier)
                    }
                }

                template {
                    metadata {
                        labels {
                            label(tier)
                        }
                    }

                    spec {
                        containers {
                            container {
                                name = "php-redis"
                                image = "us-docker.pkg.dev/google-samples/containers/gke/gb-frontend:v5"
                            }
                        }
                    }
                }
            }
        }
    }
}