//package io.violabs.picard.domain.manifest
//
//
//import io.violabs.picard.FullBuildSim
//import io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevision
//import io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevisionList
//import io.violabs.picard.domain.k8sResources.workload.cronJob.CronJob
//import io.violabs.picard.domain.k8sResources.workload.cronJob.CronJobList
//import io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSet
//import io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSetList
//import io.violabs.picard.domain.k8sResources.workload.deployment.Deployment
//import io.violabs.picard.domain.k8sResources.workload.deployment.DeploymentList
//import io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscaler
//import io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscalerList
//import io.violabs.picard.domain.k8sResources.workload.job.Job
//import io.violabs.picard.domain.k8sResources.workload.job.JobList
//import io.violabs.picard.domain.k8sResources.workload.pod.Pod
//import io.violabs.picard.domain.k8sResources.workload.pod.PodList
//import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
//import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplateList
//import io.violabs.picard.domain.k8sResources.workload.priorityClass.PriorityClass
//import io.violabs.picard.domain.k8sResources.workload.priorityClass.PriorityClassList
//import io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSet
//import io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSetList
//import io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationController
//import io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationControllerList
//import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
//import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaimList
//import io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplate
//import io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplateList
//import io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSlice
//import io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSliceList
//import io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSet
//import io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSetList
//import io.violabs.picard.possibilities
//import org.junit.jupiter.api.BeforeAll
//
//class WorkloadResourceSectionTest : FullBuildSim<WorkloadResourceSection, WorkloadResourceSectionDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            WorkloadResourceSectionTest::class,
//            SUCCESS_POSSIBILITIES,
//            FAILURE_POSSIBILITIES
//        )
//
//        private val CONTROLLER_REVISION = ControllerRevision(
//            revision = 1
//        )
//
//        private val PRIORITY_CLASS = PriorityClass(
//            value = 1
//        )
//
//        private val RESOURCE_CLAIM_SPEC = ResourceClaimSpec()
//
//        private val RESOURCE_CLAIM = ResourceClaim(
//            spec = RESOURCE_CLAIM_SPEC
//        )
//
//        private val RESOURCE_CLAIM_TEMPLATE = ResourceClaimTemplate(
//            spec = ResourceClaimTemplateSpec(
//                spec = RESOURCE_CLAIM_SPEC
//            )
//        )
//
//        private val RESOURCE_SLICE = ResourceSlice(
//            spec = ResourceSliceSpec(
//                driver = PLACEHOLDER,
//                pool = RESOURCE_POOL
//            )
//        )
//
//        private val SUCCESS_POSSIBILITIES = possibilities<WorkloadResourceSection, WorkloadResourceSectionDslBuilder> {
//            scenario {
//                id = "full"
//                given(WorkloadResourceSectionDslBuilder()) {
//                    controllerRevision {
//                        revision = 1
//                    }
//
//                    controllerRevisionList {
//                        items {
//                            controllerRevisionItem {
//                                revision = 1
//                            }
//                        }
//                    }
//
//                    cronJob { }
//
//                    cronJobList {
//                        items {
//                            cronJobItem { }
//                        }
//                    }
//
//                    daemonSet { }
//
//                    daemonSetList {
//                        items {
//                            daemonSetItem { }
//                        }
//                    }
//
//                    deployment { }
//
//                    deploymentList {
//                        items {
//                            deploymentItem { }
//                        }
//                    }
//
//                    horizontalPodAutoscaler { }
//
//                    horizontalPodAutoscalerList {
//                        items {
//                            horizontalPodAutoscalerItem { }
//                        }
//                    }
//
//                    job { }
//
//                    jobList {
//                        items {
//                            jobItem { }
//                        }
//                    }
//
//                    pod { }
//
//                    podList {
//                        items {
//                            podItem { }
//                        }
//                    }
//
//                    podTemplate { }
//
//                    podTemplateList {
//                        items {
//                            podTemplateItem { }
//                        }
//                    }
//
//                    priorityClass {
//                        value = 1
//                    }
//
//                    priorityClassList {
//                        items {
//                            priorityClassItem {
//                                value = 1
//                            }
//                        }
//                    }
//
//                    replicaSet { }
//
//                    replicaSetList {
//                        items {
//                            replicaSetItem { }
//                        }
//                    }
//
//                    replicationController { }
//
//                    replicationControllerList {
//                        items {
//                            replicationControllerItem { }
//                        }
//                    }
//
//                    resourceClaim {
//                        spec {}
//                    }
//
//                    resourceClaimList {
//                        items {
//                            resourceClaimItem {
//                                spec {}
//                            }
//                        }
//                    }
//
//                    resourceClaimTemplate {
//                        spec {
//                            resourceClaimSpec {  }
//                        }
//                    }
//
//                    resourceClaimTemplateList {
//                        items {
//                            resourceClaimTemplateItem {
//                                spec {
//                                    resourceClaimSpec {  }
//                                }
//                            }
//                        }
//                    }
//
//                    resourceSlice {
//                        spec {
//                            driver = PLACEHOLDER
//                            pool {
//                                sharedResourcePool()
//                            }
//                        }
//                    }
//
//                    resourceSliceList {
//                        items {
//                            resourceSliceItem {
//                                spec {
//                                    driver = PLACEHOLDER
//                                    pool {
//                                        sharedResourcePool()
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    statefulSet { }
//
//                    statefulSetList {
//                        items {
//                            statefulSetItem { }
//                        }
//                    }
//                }
//                expected = WorkloadResourceSection(
//                    resources = listOf(
//                        CONTROLLER_REVISION,
//                        ControllerRevisionList(items = listOf(CONTROLLER_REVISION)),
//                        CronJob(),
//                        CronJobList(items = listOf(CronJob())),
//                        DaemonSet(),
//                        DaemonSetList(items = listOf(DaemonSet())),
//                        Deployment(),
//                        DeploymentList(items = listOf(Deployment())),
//                        HorizontalPodAutoscaler(),
//                        HorizontalPodAutoscalerList(items = listOf(HorizontalPodAutoscaler())),
//                        Job(),
//                        JobList(items = listOf(Job())),
//                        Pod(),
//                        PodList(items = listOf(Pod())),
//                        PodTemplate(),
//                        PodTemplateList(items = listOf(PodTemplate())),
//                        PRIORITY_CLASS,
//                        PriorityClassList(items = listOf(PRIORITY_CLASS)),
//                        ReplicaSet(),
//                        ReplicaSetList(items = listOf(ReplicaSet())),
//                        ReplicationController(),
//                        ReplicationControllerList(items = listOf(ReplicationController())),
//                        RESOURCE_CLAIM,
//                        ResourceClaimList(items = listOf(RESOURCE_CLAIM)),
//                        RESOURCE_CLAIM_TEMPLATE,
//                        ResourceClaimTemplateList(items = listOf(RESOURCE_CLAIM_TEMPLATE)),
//                        RESOURCE_SLICE,
//                        ResourceSliceList(items = listOf(RESOURCE_SLICE)),
//                        StatefulSet(),
//                        StatefulSetList(items = listOf(StatefulSet()))
//                    )
//                )
//            }
//        }
//
//        private val FAILURE_POSSIBILITIES = possibilities<WorkloadResourceSection, WorkloadResourceSectionDslBuilder> {
//            requireNotEmptyScenario("resources") {
//                given(WorkloadResourceSectionDslBuilder())
//            }
//        }
//    }
//}