package io.violabs.picard.sandbox

import io.violabs.picard.YamlBuilder
import io.violabs.picard.buildManifest
import io.violabs.picard.cmd.dsl.kubectl
import io.violabs.picard.common.FileManager
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.service.Service

val fileManager = FileManager("SANDBOX")

private const val GENERATED_DIR = "sandbox/src/main/resources/generated"

private const val POSTGRES_FILE_NAME = "$GENERATED_DIR/postgres.yaml"
private const val PLANET_TRACKER_FILE_NAME = "$GENERATED_DIR/planet-tracker-app.yaml"

private const val POSTGRES_MANIFEST_NAME = "postgres"
private const val PLANET_TRACKER_MANIFEST_NAME = "planet-tracker-app"

private const val POSTGRES_USERNAME = "postgres"
private const val POSTGRES_PASSWORD = "password"
private const val POSTGRES_DB = "postgres"
private const val POSTGRES_OUTBOUND_PORT = 5432

private const val DEFAULT_DOCKER_K8s_STORAGE_CLASS = "hostpath"

private const val NAMESPACE = "picard-sandbox"

fun main(vararg args: String) {
//    addManifestForPostgres()
//    addManifestForPlanetTracker()
    runManifest(
        POSTGRES_FILE_NAME,
        apply = false,
        list = false,
        describe = false,
        disabled = false
    )
    runManifest(
        PLANET_TRACKER_FILE_NAME,
        apply = false,
        list = true,
        describe = false,
        disabled = false
    )
}

fun runManifest(
    fileName: String,
    apply: Boolean = false,
    list: Boolean = true,
    describe: Boolean = false,
    disabled: Boolean = false
) = kubectl {
    if (disabled) return@kubectl

    applyPod {
        fileLocation = fileName
        enabled = apply
    }

    getPods {
        allNameSpaces()
        enabled = list
    }

    describePod {
        fileLocation = fileName
        enabled = describe
    }
}

fun addManifestForPostgres() = with(fileManager) {
    val postgresManifest = buildManifest {
        val configName = "postgres-config"
        val secretName = "postgres-secret"
        val storageName = "postgres-storage"
        val volumeClaimName = "postgres-pvc"
        val appKey = "app"

        clusterSection {
            namespace {
                metadata { name = NAMESPACE }
            }
        }

        configSection {
            configMap {
                metadata {
                    name = configName
                    namespace = NAMESPACE
                }

                data("POSTGRES_DB" to POSTGRES_DB)
            }

            secret {
                metadata {
                    name = secretName
                    namespace = NAMESPACE
                }
                type = Secret.Type.OPAQUE
                data(
                    "POSTGRES_USERNAME" to POSTGRES_USERNAME,
                    "POSTGRES_PASSWORD" to POSTGRES_PASSWORD
                )
            }
        }

        storageSection {
            persistentVolumeClaim {
                metadata {
                    name = volumeClaimName
                    namespace = NAMESPACE
                }

                spec {
                    accessModes(AccessMode.ReadWriteOnce)
                    resources {
                        requests(
                            "storage" to "5Gi"
                        )
                    }
                    storageClassName = DEFAULT_DOCKER_K8s_STORAGE_CLASS
                }
            }
        }

        workloadSection {
            deployment {
                metadata {
                    name = POSTGRES_MANIFEST_NAME
                    namespace = NAMESPACE
                }

                spec {
                    replicas = 1
                    selector {
                        matchLabels {
                            label(appKey, POSTGRES_MANIFEST_NAME)
                        }
                    }

                    template {
                        metadata {
                            labels {
                                label(appKey, POSTGRES_MANIFEST_NAME)
                            }
                        }

                        templateSpec {
                            containers {
                                container {
                                    name = POSTGRES_MANIFEST_NAME
                                    image = "postgres:latest"
                                    ports {
                                        addContainerPort {
                                            containerPort = POSTGRES_OUTBOUND_PORT
                                        }
                                    }
                                    envFrom {
                                        add {
                                            configMapRef {
                                                name = configName
                                            }
                                        }

                                        add {
                                            secretRef {
                                                name = secretName
                                            }
                                        }
                                    }
                                    volumeMounts {
                                        volumeMount {
                                            mountPath = "/var/lib/postgresql/data"
                                            name = storageName
                                        }
                                    }
                                }
                            }

                            volumes {
                                addVolume {
                                    name = storageName
                                    persistentVolumeClaim {
                                        claimName = volumeClaimName
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        serviceSection {
            service {
                metadata {
                    name = POSTGRES_MANIFEST_NAME
                    namespace = NAMESPACE
                }

                spec {
                    selector(appKey to POSTGRES_MANIFEST_NAME)
                    ports {
                        addServicePort {
                            port = POSTGRES_OUTBOUND_PORT
                            targetPort(POSTGRES_OUTBOUND_PORT)
                        }
                    }
                }
            }
        }
    }

    val yml = YamlBuilder.build(postgresManifest)

    addFile(
        "postgres",
        "./sandbox/src/main/resources/generated/postgres.yaml",
        yml
    )
}

fun addManifestForPlanetTracker() = with(fileManager) {
    val appKey = "app"
    val postgresSecretName = "postgres-credentials"

    val planetTrackerManifest = buildManifest {
        workloadSection {
            deployment {
                metadata {
                    name = PLANET_TRACKER_MANIFEST_NAME
                    namespace = NAMESPACE
                }

                spec {
                    replicas = 1
                    selector {
                        matchLabels {
                            label(appKey, PLANET_TRACKER_MANIFEST_NAME)
                        }
                    }

                    template {
                        metadata {
                            labels {
                                label(appKey, PLANET_TRACKER_MANIFEST_NAME)
                            }
                        }

                        templateSpec {
                            containers {
                                container {
                                    name = PLANET_TRACKER_MANIFEST_NAME
                                    image = "violabs/enterprise-planet-tracker:0.0.2-SNAPSHOT"
                                    ports {
                                        addContainerPort {
                                            containerPort = 8080
                                        }
                                    }
                                    env {
                                        add {
                                            name = "POSTGRES_USERNAME"
                                            valueFrom {
                                                secretKeyRef {
                                                    name = postgresSecretName
                                                    key = "username"
                                                }
                                            }
                                        }

                                        add {
                                            name = "POSTGRES_PASSWORD"
                                            valueFrom {
                                                secretKeyRef {
                                                    name = "postgres-credentials"
                                                    key = "password"
                                                }
                                            }
                                        }

                                        add {
                                            name = "POSTGRES_HOST"
                                            value = POSTGRES_MANIFEST_NAME
                                        }

                                        add {
                                            name = "POSTGRES_PORT"
                                            value = POSTGRES_OUTBOUND_PORT.toString()
                                        }

                                        add {
                                            name = "POSTGRES_DB"
                                            value = POSTGRES_DB
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        configSection {
            secret {
                metadata {
                    name = postgresSecretName
                    namespace = NAMESPACE
                }

                type = Secret.Type.OPAQUE
                data(
                    "username" to POSTGRES_USERNAME,
                    "password" to POSTGRES_PASSWORD
                )
            }
        }

        serviceSection {
            service {
                metadata {
                    name = "planet-tracker-app-service"
                    namespace = NAMESPACE
                }

                spec {
                    selector(appKey to PLANET_TRACKER_MANIFEST_NAME)
                    ports {
                        addServicePort {
                            port = 80
                            targetPort(8080)
                        }
                    }
                    type = Service.Spec.Type.ClusterIP
                }
            }
        }
    }

    val yml = YamlBuilder.build(planetTrackerManifest)

    addFile(
        "planet-tracker-app",
        "./sandbox/src/main/resources/generated/planet-tracker-app.yaml",
        yml
    )
}