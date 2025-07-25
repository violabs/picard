//package io.violabs.picard.tutorial.pod
//
//import io.violabs.picard.YamlBuilder
//import io.violabs.picard.buildManifest
//import io.violabs.picard.domain.RestartPolicy
//import io.violabs.picard.domain.k8sResources.Protocol
//
//object ComplexPodFactory {
//
//    fun buildWithInitContainers(): String {
//        val config = buildManifest {
//            workloadSection {
//                pod {
//                    metadata {
//                        name = "myapp-pod"
//                        labels {
//                            label("app.kubernetes.io/name", "MyApp")
//                        }
//                    }
//
//                    spec {
//                        containers {
//                            container {
//                                name = "myapp-container"
//                                image = "busybox:1.28"
//                                command("sh", "-c", "echo The app is running! && sleep 3600")
//                            }
//                        }
//
//                        initContainers {
//                            container {
//                                name = "init-myservice"
//                                image = "busybox:1.28"
//                                command(
//                                    "sh", "-c",
//                                    "until nslookup myservice." +
//                                        "\$(cat /var/run/secrets/kubernetes.io/serviceaccount/namespace).svc.cluster.local; " +
//                                        "do echo waiting for myservice; sleep 2; done")
//                            }
//
//                            container {
//                                name = "init-mydb"
//                                image = "busybox:1.28"
//                                command(
//                                    "sh", "-c",
//                                    "until nslookup mydb." +
//                                        "\$(cat /var/run/secrets/kubernetes.io/serviceaccount/namespace).svc.cluster.local; " +
//                                        "do echo waiting for mydb; sleep 2; done")
//                            }
//                        }
//                    }
//                }
//            }
//
//            serviceSection {
//                service {
//                    metadata {
//                        name = "myservice"
//                    }
//
//                    spec {
//                        ports {
//                            addServicePort {
//                                protocol = Protocol.TCP
//                                port = 80
//                                targetPort(9376)
//                            }
//                        }
//                    }
//                }
//
//                service {
//                    metadata {
//                        name = "mydb"
//                    }
//
//                    spec {
//                        ports {
//                            addServicePort {
//                                protocol = Protocol.TCP
//                                port = 80
//                                targetPort(9377)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return YamlBuilder.build(config)
//    }
//
//    fun buildWithSideCar(): String {
//        val appName = "myapp"
//        val labelKey = "app"
//        val sharedImage = "alpine:latest"
//        val volumeName = "data"
//        val volumeMountPath = "/opt"
//
//        val config = buildManifest {
//            workloadSection {
//                deployment {
//                    metadata {
//                        name = appName
//                        labels {
//                            label(labelKey, appName)
//                        }
//                    }
//
//                    spec {
//                        replicas = 1
//                        selector {
//                            matchLabels {
//                                label(labelKey, appName)
//                            }
//                        }
//
//                        template {
//                            metadata {
//                                labels {
//                                    label(labelKey, appName)
//                                }
//                            }
//
//                            templateSpec {
//                                containers {
//                                    container {
//                                        name = appName
//                                        image = sharedImage
//                                        command("sh", "-c", "echo \"logging\" > /opt/logs.txt")
//                                        volumeMounts {
//                                            volumeMount {
//                                                name = volumeName
//                                                mountPath = volumeMountPath
//                                            }
//                                        }
//                                    }
//
//                                    initContainers {
//                                        container {
//                                            name = "logshipper"
//                                            image = sharedImage
//                                            restartPolicy = RestartPolicy.Always
//                                            command("sh", "-c", "tail -F /opt/logs.txt")
//                                            volumeMounts {
//                                                volumeMount {
//                                                    name = volumeName
//                                                    mountPath = volumeMountPath
//                                                }
//                                            }
//                                        }
//                                    }
//
//                                    volumes {
//                                        addVolume {
//                                            name = volumeName
//                                            emptyDirObject()
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return YamlBuilder.build(config)
//    }
//}