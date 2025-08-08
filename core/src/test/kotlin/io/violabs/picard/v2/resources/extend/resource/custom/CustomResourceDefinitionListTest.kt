package io.violabs.picard.v2.resources.extend.resource.custom


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionListTest :
    FullBuildSim<CustomResourceDefinitionList, CustomResourceDefinitionListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<CustomResourceDefinitionList, CustomResourceDefinitionListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(CustomResourceDefinitionListDslBuilder()) {
                        items {
                            customResourceDefinition {
                                spec {
                                    group = PLACEHOLDER
                                    names {
                                        kind = PLACEHOLDER
                                        plural = PLACEHOLDER
                                    }
                                    scope = PLACEHOLDER
                                    versions {
                                        customResourceDefinitionVersion {
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
                                spec = CustomResourceDefinitionSpec(
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
            possibilities<CustomResourceDefinitionList, CustomResourceDefinitionListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(CustomResourceDefinitionListDslBuilder())
                }
            }
    }
}