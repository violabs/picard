package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole
import io.violabs.picard.domain.k8sResources.cluster.apiService.APIService
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ManifestTest : FullBuildSim<Manifest, Manifest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ManifestTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Manifest, Manifest.Builder> {
            scenario {
                id = "full"
                given(Manifest.Builder()) {
                    authentication {
                        tokenRequest { }
                    }

                    authorization {
                        clusterRole { }
                    }

                    cluster {
                        apiService { }
                    }

                    config {
                        configMap { }
                    }

                    extend {
                        mutatingWebhook { }
                    }

                    policy {
                        flowSchema { }
                    }
                }
                expected = Manifest(
                    resources = listOf(
                        AuthenticationResourceSection(
                            resources = listOf(TokenRequest())
                        ),
                        AuthorizationResourceSection(
                            resources = listOf(ClusterRole())
                        ),
                        ClusterResourceSection(
                            resources = listOf(APIService())
                        ),
                        ConfigResourceSection(
                            resources = listOf(ConfigMap())
                        ),
                        ExtendResourceSection(
                            resources = listOf(MutatingWebhookConfiguration())
                        ),
                        PolicyResourceSection(
                            resources = listOf(FlowSchema())
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<Manifest, Manifest.Builder> {
            requireNotEmptyScenario("resources") {
                given(Manifest.Builder())
            }
        }
    }
}