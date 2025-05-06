package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionNames
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionVersion
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionListTest :
    FullBuildSim<CustomResourceDefinitionList, CustomResourceDefinitionList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CustomResourceDefinitionList, CustomResourceDefinitionList.Builder> {
                scenario {
                    id = "minimum"
                    given(CustomResourceDefinitionList.Builder()) {
                        items {
                            definition {
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
                        }
                    }
                    expected = CustomResourceDefinitionList(
                        items = listOf(
                            CustomResourceDefinition(
                                spec = CustomResourceDefinition.Spec(
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
                                )
                            )
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionList, CustomResourceDefinitionList.Builder> {
                requireNotEmptyScenario("items") {
                    given(CustomResourceDefinitionList.Builder())
                }
            }
    }
}