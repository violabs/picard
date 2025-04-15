package io.violabs.picard.domain.k8sResources.authorization.accessReview


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.HTTPVerb
import io.violabs.picard.domain.k8sResources.authorization.NonResourceRule
import io.violabs.picard.domain.k8sResources.authorization.ResourceRule
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelfSubjectRulesReviewTest : FullBuildSim<SelfSubjectRulesReview, SelfSubjectRulesReview.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelfSubjectRulesReviewTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SelfSubjectRulesReview, SelfSubjectRulesReview.Builder> {
            scenario {
                id = "minimum"
                given(SelfSubjectRulesReview.Builder()) {
                    spec {
                        namespace = PLACEHOLDER
                    }
                }
                expected = SelfSubjectRulesReview(
                    spec = SelfSubjectRulesReview.Spec(PLACEHOLDER)
                )
            }

            scenario {
                id = "full"
                given(SelfSubjectRulesReview.Builder()) {
                    spec {
                        namespace = PLACEHOLDER
                    }

                    status {
                        incomplete()
                        nonResourceRules {
                            rule {
                                verbs(HTTPVerb.GET)
                                nonResourceURLs = PLACEHOLDER
                            }
                        }

                        resourceRules {
                            rule {
                                verbs(HTTPVerb.GET)
                                apiGroups(PLACEHOLDER)
                                resourceNames = PLACEHOLDER
                                resources = PLACEHOLDER
                            }
                        }

                        evaluationError = PLACEHOLDER
                    }

                    sharedMetadata()
                }
                expected = SelfSubjectRulesReview(
                    spec = SelfSubjectRulesReview.Spec(PLACEHOLDER),
                    status = SelfSubjectRulesReview.Status(
                        incomplete = true,
                        nonResourceRules = listOf(
                            NonResourceRule(
                                verbs = listOf(HTTPVerb.GET),
                                nonResourceURLs = PLACEHOLDER
                            )
                        ),
                        resourceRules = listOf(
                            ResourceRule(
                                verbs = listOf(HTTPVerb.GET),
                                apiGroups = listOf(PLACEHOLDER),
                                resourceNames = PLACEHOLDER,
                                resources = PLACEHOLDER
                            )
                        ),
                        evaluationError = PLACEHOLDER
                    ),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SelfSubjectRulesReview, SelfSubjectRulesReview.Builder> {
            requireScenario("spec") {
                given(SelfSubjectRulesReview.Builder())
            }
        }
    }
}