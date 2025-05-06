package io.violabs.picard.tutorial.job

import io.violabs.picard.YamlBuilder
import io.violabs.picard.buildManifest
import io.violabs.picard.domain.RestartPolicy

object JobFactory {

    /**
     * apiVersion: batch/v1
     * kind: Job
     * metadata:
     *   name: myjob
     * spec:
     *   template:
     *     spec:
     *       containers:
     *         - name: myjob
     *           image: alpine:latest
     *           command: ['sh', '-c', 'echo "logging" > /opt/logs.txt']
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
     *       restartPolicy: Never
     *       volumes:
     *         - name: data
     *           emptyDir: {}
     */
    fun buildJobWithSideCar(): String {
        val appName = "myjob"
        val sharedImage = "alpine:latest"
        val volumeName = "data"

        val manifest = buildManifest {
            workloadSection {
                job {
                    metadata {
                        name = appName
                    }
                    spec {
                        template {
                            templateSpec {
                                containers {
                                    container {
                                        name = appName
                                        image = sharedImage
                                        command("sh", "-c", "echo \"logging\" > /opt/logs.txt")
                                        volumeMounts {
                                            volumeMount {
                                                name = volumeName
                                                mountPath = "/opt"
                                            }
                                        }
                                    }
                                }

                                initContainers {
                                    container {
                                        name = "logshipper"
                                        image = sharedImage
                                        command("sh", "-c", "tail -F /opt/logs.txt")
                                        volumeMounts {
                                            volumeMount {
                                                name = volumeName
                                                mountPath = "/opt"
                                            }
                                        }
                                    }
                                }

                                restartPolicy = RestartPolicy.Never

                                volumes {
                                    addVolume {
                                        name = volumeName
                                        emptyDirObject()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return YamlBuilder.build(manifest)
    }
}