//package io.violabs.picard.domain.manifest
//
//
//import io.violabs.picard.FullBuildSim
//import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
//import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole
//import io.violabs.picard.domain.k8sResources.cluster.apiService.APIService
//import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
//import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration
//import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema
//import io.violabs.picard.domain.k8sResources.service.ingress.Ingress
//import io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolume
//import io.violabs.picard.domain.k8sResources.workload.pod.Pod
//import io.violabs.picard.possibilities
//import org.junit.jupiter.api.BeforeAll
//
//class ManifestTest : FullBuildSim<Manifest, ManifestDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            ManifestTest::class,
//            SUCCESS_POSSIBILITIES,
//            FAILURE_POSSIBILITIES
//        )
//
//        private val SUCCESS_POSSIBILITIES = possibilities<Manifest, ManifestDslBuilder> {
//            scenario {
//                id = "full"
//                given(ManifestDslBuilder()) {
//                    authenticationSection {
//                        tokenRequest { }
//                    }
//
//                    authorizationSection {
//                        clusterRole { }
//                    }
//
//                    clusterSection {
//                        apiService { }
//                    }
//
//                    configSection {
//                        configMap { }
//                    }
//
//                    extendSection {
//                        mutatingWebhook { }
//                    }
//
//                    policySection {
//                        flowSchema { }
//                    }
//
//                    serviceSection {
//                        ingress { }
//                    }
//
//                    storageSection {
//                        persistentVolume { }
//                    }
//
//                    workloadSection {
//                        pod {  }
//                    }
//                }
//                expected = Manifest(
//                    resources = listOf(
//                        AuthenticationResourceSection(
//                            resources = listOf(TokenRequest())
//                        ),
//                        AuthorizationResourceSection(
//                            resources = listOf(ClusterRole())
//                        ),
//                        ClusterResourceSection(
//                            resources = listOf(APIService())
//                        ),
//                        ConfigResourceSection(
//                            resources = listOf(ConfigMap())
//                        ),
//                        ExtendResourceSection(
//                            resources = listOf(MutatingWebhookConfiguration())
//                        ),
//                        PolicyResourceSection(
//                            resources = listOf(FlowSchema())
//                        ),
//                        ServiceResourceSection(
//                            resources = listOf(Ingress())
//                        ),
//                        StorageResourceSection(
//                            resources = listOf(PersistentVolume())
//                        ),
//                        WorkloadResourceSection(
//                            resources = listOf(Pod())
//                        )
//                    )
//                )
//            }
//        }
//
//        private val FAILURE_POSSIBILITIES = possibilities<Manifest, ManifestDslBuilder> {
//            requireNotEmptyScenario("resources") {
//                given(ManifestDslBuilder())
//            }
//        }
//    }
//}