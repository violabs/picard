package io.violabs.picard.tutorial.pod

import io.violabs.picard.buildManifest
import io.violabs.picard.buildPodYamlContent
import io.violabs.picard.domain.*

object ComplexPodFactory {

    fun buildWithInitContainers(): String {
        val config = buildManifest {
            resource {
                apiVersion = PodResource.Version.V1
                kind = Kind.POD
                metadata {
                    name = "myapp-pod"
                    labels {
                        label("app.kubernetes.io/name", "MyApp")
                    }
                }
                spec {
                    containers {
                        container {
                            name = "myapp-container"
                            image = "busybox:1.28"
                            command = listOf("sh", "-c", "echo The app is running! && sleep 3600")
                        }
                    }
                    initContainers {
                        container {
                            name = "init-myservice"
                            image = "busybox:1.28"
                            command = listOf(
                                "sh",
                                "-c",
                                "until nslookup myservice.$(cat /var/run/secrets/kubernetes.io/serviceaccount/namespace).svc.cluster.local; do echo waiting for myservice; sleep 2; done"
                            )
                        }
                        container {
                            name = "init-mydb"
                            image = "busybox:1.28"
                            command = listOf(
                                "sh",
                                "-c",
                                "until nslookup mydb.$(cat /var/run/secrets/kubernetes.io/serviceaccount/namespace).svc.cluster.local; do echo waiting for mydb; sleep 2; done"
                            )
                        }
                    }
                }
            }

            resource {
                apiVersion = PodResource.Version.V1
                kind = Kind.SERVICE
                metadata {
                    name = "myservice"
                }
                spec {
                    ports {
                        port {
                            protocol = ServicePortConfig.Protocol.TCP
                            port = 80
                            targetPort = 9376
                        }
                    }
                }
            }

            resource {
                apiVersion = PodResource.Version.V1
                kind = Kind.SERVICE
                metadata {
                    name = "mydb"
                }
                spec {
                    ports {
                        port {
                            protocol = ServicePortConfig.Protocol.TCP
                            port = 80
                            targetPort = 9377
                        }
                    }
                }
            }
        }

        return buildPodYamlContent(config)
    }

    /**
     * apiVersion: apps/v1
     * kind: Deployment
     * metadata:
     *   name: myapp
     *   labels:
     *     app: myapp
     * spec:
     *   replicas: 1
     *   selector:
     *     matchLabels:
     *       app: myapp
     *   template:
     *     metadata:
     *       labels:
     *         app: myapp
     *     spec:
     *       containers:
     *         - name: myapp
     *           image: alpine:latest
     *           command: ['sh', '-c', 'while true; do echo "logging" >> /opt/logs.txt; sleep 1; done']
     *           volumeMounts:
     *             - name: data
     *               mountPath: /opt
     *       initContainers:
     *         - name: logshipper
     *           image: alpine:latest
     *           restartPolicy: Always
     *           command: ['sh', '-c', 'tail -F /opt/logs.txt']
     *           volumeMounts:
     *             - name: data
     *               mountPath: /opt
     *       volumes:
     *         - name: data
     *           emptyDir: {}
     */
    fun buildWithSideCar(): String {
        val appName = "myapp"
        val labelKey = "app"
        val label = Label(labelKey, appName)
        val sharedImage = "alpine:latest"
        val volumeName = "data"
        val volumeMountPath = "/opt"
        val sharedVolumeMount = VolumeMount(
            name = volumeName,
            mountPath = volumeMountPath
        )
        val config = buildManifest {
            resource {
                apiVersion = PodResource.Version.APPS_V1
                kind = Kind.DEPLOYMENT
                metadata {
                    name = appName
                    labels {
                        label(label)
                    }
                }
                spec {
                    replicas = 1
                    selector {
                        matchLabels {
                            label(label)
                        }
                    }
                    template {
                        templateMetadata {
                            labels {
                                // as example
                                label(labelKey, appName)
                            }
                        }
                        spec {
                            containers {
                                container {
                                    name = appName
                                    image = sharedImage
                                    command(
                                        "sh",
                                        "-c",
                                        "while true; do echo \"logging\" >> /opt/logs.txt; sleep 1; done"
                                    )
                                    volumeMounts {
                                        // save for example
                                        volumeMount {
                                            name = volumeName
                                            mountPath = volumeMountPath
                                        }
                                    }
                                }
                            }

                            initContainers {
                                container {
                                    name = "logshipper"
                                    image = sharedImage
                                    restartPolicy = RestartPolicy.ALWAYS
                                    command(
                                        "sh",
                                        "-c",
                                        "tail -F /opt/logs.txt"
                                    )
                                    volumeMounts {
                                        volumeMount(sharedVolumeMount)
                                    }
                                }
                            }

                            volumes {
                                defaultEmptyDirVolume(volumeName)
                            }
                        }
                    }
                }
            }
        }

        return buildPodYamlContent(config)
    }
}