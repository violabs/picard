package io.violabs.picard.v2.resources.authorization.review.rules


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewTest : FullBuildSim<SelfSubjectRulesReview, SelfSubjectRulesReviewDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectRulesReview, SelfSubjectRulesReviewDslBuilder> {
            scenario {
                id = "minimum"
                given(SelfSubjectRulesReviewDslBuilder()) {
                    spec {
                        namespace = PLACEHOLDER
                    }
                }
                expected = SelfSubjectRulesReview(
                    spec = SelfSubjectRulesReviewSpec(PLACEHOLDER)
                )
            }

            scenario {
                id = "full"
                given(SelfSubjectRulesReviewDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        namespace = PLACEHOLDER
                    }

                    status {
                        incomplete()
                        nonResourceRules {
                            nonResourceRule {
                                verbs(PLACEHOLDER)
                                nonResourceURLs(PLACEHOLDER)
                            }
                        }

                        resourceRules {
                            resourceRule {
                                verbs(PLACEHOLDER)
                                apiGroups(PLACEHOLDER)
                                resourceNames(PLACEHOLDER)
                                resources(PLACEHOLDER)
                            }
                        }

                        evaluationError = PLACEHOLDER
                    }
                }
                expected = SelfSubjectRulesReview(
                    spec = SelfSubjectRulesReviewSpec(PLACEHOLDER),
                    status = SelfSubjectRulesReviewStatus(
                        incomplete = true,
                        nonResourceRules = listOf(
                            NonResourceRule(
                                verbs = PLACEHOLDER_LIST,
                                nonResourceURLs = PLACEHOLDER_LIST
                            )
                        ),
                        resourceRules = listOf(
                            ResourceRule(
                                verbs = PLACEHOLDER_LIST,
                                apiGroups = PLACEHOLDER_LIST,
                                resourceNames = PLACEHOLDER_LIST,
                                resources = PLACEHOLDER_LIST
                            )
                        ),
                        evaluationError = PLACEHOLDER
                    ),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SelfSubjectRulesReview, SelfSubjectRulesReviewDslBuilder> {
            requireScenario("spec") {
                given(SelfSubjectRulesReviewDslBuilder())
            }
        }
    }
}