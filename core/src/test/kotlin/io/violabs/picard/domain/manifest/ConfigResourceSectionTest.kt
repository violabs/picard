package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.config.secret.SecretList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigResourceSectionTest : FullBuildSim<ConfigResourceSection, ConfigResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigResourceSection, ConfigResourceSection.Builder> {
            scenario {
                id = "full"
                given(ConfigResourceSection.Builder()) {
                    configMap {

                    }

                    configMapList {
                        items {
                            map { }
                        }
                    }

                    secret {

                    }

                    secretList {
                        items {
                            secretItem {  }
                        }
                    }
                }
                expected = ConfigResourceSection(
                    resources = listOf(
                        ConfigMap(),
                        ConfigMapList(items = listOf(ConfigMap())),
                        Secret(),
                        SecretList(items = listOf(Secret()))
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ConfigResourceSection, ConfigResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ConfigResourceSection.Builder())
            }
        }
    }
}