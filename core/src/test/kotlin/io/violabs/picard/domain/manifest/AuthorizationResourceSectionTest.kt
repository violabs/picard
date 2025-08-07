package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authorization.accessReview.LocalSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectRulesReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRoleList
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBinding
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBindingList
import io.violabs.picard.domain.k8sResources.authorization.role.Role
import io.violabs.picard.domain.k8sResources.authorization.role.RoleList
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBinding
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBindingList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuthorizationResourceSectionTest :
    FullBuildSim<AuthorizationResourceSection, AuthorizationResourceSectionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuthorizationResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUBJECT_ACCESS_REVIEW_SPEC = SubjectAccessReviewSpec()

        private val CLUSTER_ROLE_BINDING = ClusterRoleBinding(roleRef = ROLE_REF)

        private val ROLE_BINDING = RoleBinding(roleRef = ROLE_REF)

        private val SUCCESS_POSSIBILITIES =
            possibilities<AuthorizationResourceSection, AuthorizationResourceSectionDslBuilder> {
                scenario {
                    id = "full"
                    given(AuthorizationResourceSectionDslBuilder()) {
                        clusterRole { }

                        clusterRoleList {
                            items {
                                role { }
                            }
                        }

                        clusterRoleBinding {
                            roleRef { sharedRoleRef() }
                        }

                        clusterRoleBindingList {
                            items {
                                binding {
                                    roleRef { sharedRoleRef() }
                                }
                            }
                        }

                        localSubjectAccessReview {
                            spec {

                            }
                        }

                        role { }

                        roleList {
                            items {
                                roleItem { }
                            }
                        }

                        roleBinding {
                            roleRef { sharedRoleRef() }
                        }

                        roleBindingList {
                            items {
                                binding {
                                    roleRef { sharedRoleRef() }
                                }
                            }
                        }

                        subjectAccessReview {
                            spec {

                            }
                        }

                        selfSubjectAccessReview {
                            spec {

                            }
                        }

                        selfSubjectRulesReview {
                            spec {
                                namespace = PLACEHOLDER
                            }
                        }
                    }
                    expected = AuthorizationResourceSection(
                        resources = listOf(
                            ClusterRole(),
                            CLUSTER_ROLE_BINDING,
                            ClusterRoleBindingList(items = listOf(CLUSTER_ROLE_BINDING)),
                            ClusterRoleList(items = listOf(ClusterRole())),
                            LocalSubjectAccessReview(spec = SUBJECT_ACCESS_REVIEW_SPEC),
                            Role(),
                            ROLE_BINDING,
                            RoleBindingList(items = listOf(ROLE_BINDING)),
                            RoleList(items = listOf(Role())),
                            SelfSubjectAccessReview(spec = SelfSubjectAccessReviewSpec()),
                            SelfSubjectRulesReview(spec = SelfSubjectRulesReviewSpec(namespace = PLACEHOLDER)),
                            SubjectAccessReview(spec = SUBJECT_ACCESS_REVIEW_SPEC)
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<AuthorizationResourceSection, AuthorizationResourceSectionDslBuilder> {
                requireNotEmptyScenario("resources") {
                    given(AuthorizationResourceSectionDslBuilder())
                }
            }
    }
}