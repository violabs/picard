package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionNames
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionVersion
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinition
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinitionList
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClass
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClassList
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfigurationList
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfigurationList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExtendResourceSectionTest : FullBuildSim<ExtendResourceSection, ExtendResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExtendResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val CUSTOM_RESOURCE_DEFINITION = CustomResourceDefinition(spec = CustomResourceDefinition.Spec(
            group = PLACEHOLDER,
            names = CustomResourceDefinitionNames(
                kind = PLACEHOLDER,
                plural = PLACEHOLDER
            ),
            scope = PLACEHOLDER,
            versions = listOf(
                CustomResourceDefinitionVersion(
                    name = PLACEHOLDER,
                    served = true,
                    storage = true
                )
            )
        ))

        private fun CustomResourceDefinition.Builder.sharedCustomResourceDefinition() {
            spec {
                group = PLACEHOLDER
                names {
                    kind = PLACEHOLDER
                    plural = PLACEHOLDER
                }
                scope = PLACEHOLDER
                versions {
                    addCustomResourceDefinitionVersion {
                        name = PLACEHOLDER
                        served()
                        storage()
                    }
                }
            }
        }

        private val DEVICE_CLASS = DeviceClass(spec = DeviceClass.Spec())
        
        private val SUCCESS_POSSIBILITIES = possibilities<ExtendResourceSection, ExtendResourceSection.Builder> {
            scenario {
                id = "full"
                given(ExtendResourceSection.Builder()) {
                    customResourceDefinition {
                        sharedCustomResourceDefinition()
                    }
                    customResourceDefinitionList {
                        items {
                            definition {
                                sharedCustomResourceDefinition()
                            }
                        }
                    }
                    deviceClass {
                        spec {  }
                    }
                    deviceClassList {
                        items {
                            deviceClassItem {
                                spec {}
                            }
                        }
                    }
                    mutatingWebhook { }
                    mutatingWebhookList {
                        items {
                            config {}
                        }
                    }
                    validatingWebhook { }
                    validatingWebhookList {
                        items {
                            config {}
                        }
                    }
                }
                expected = ExtendResourceSection(
                    listOf(
                        CUSTOM_RESOURCE_DEFINITION,
                        CustomResourceDefinitionList(items = listOf(CUSTOM_RESOURCE_DEFINITION)),
                        DEVICE_CLASS,
                        DeviceClassList(items = listOf(DEVICE_CLASS)),
                        MutatingWebhookConfiguration(),
                        MutatingWebhookConfigurationList(items = listOf(MutatingWebhookConfiguration())),
                        ValidatingWebhookConfiguration(),
                        ValidatingWebhookConfigurationList(items = listOf(ValidatingWebhookConfiguration()))
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ExtendResourceSection, ExtendResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(ExtendResourceSection.Builder())
            }
        }
    }
}