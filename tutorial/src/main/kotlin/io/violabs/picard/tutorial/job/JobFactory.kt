package io.violabs.picard.tutorial.job

import io.violabs.picard.buildManifest
import io.violabs.picard.buildManifestContent
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.PodResource
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.pod.volume.VolumeMount

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
        val volumeMount = VolumeMount(
            name = volumeName,
            mountPath = "/opt"
        )
        val config = buildManifest {
            resource {
                apiVersion = PodResource.Version.BATCH_V1
                kind = Kind.JOB
                metadata {
                    name = appName
                }

                spec {
                    template {
                        spec {
                            restartPolicy = RestartPolicy.NEVER
                            containers {
                                container {
                                    name = appName
                                    image = sharedImage
                                    command = listOf("sh", "-c", "echo \"logging\" > /opt/logs.txt")
                                    volumeMounts {
                                        volumeMount(volumeMount)
                                    }
                                }
                            }

                            initContainers {
                                container {
                                    name = "logshipper"
                                    image = sharedImage
                                    restartPolicy = RestartPolicy.ALWAYS
                                    command = listOf("sh", "-c", "tail -F /opt/logs.txt")
                                    volumeMounts {
                                        volumeMount(volumeMount)
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

        return buildManifestContent(config)
    }
}