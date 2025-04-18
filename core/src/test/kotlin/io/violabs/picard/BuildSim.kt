package io.violabs.picard

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.authorization.K8sSubject
import io.violabs.picard.domain.k8sResources.authorization.PolicyRule
import io.violabs.picard.domain.k8sResources.authorization.RoleRef
import org.junit.jupiter.api.TestTemplate
import java.time.LocalDateTime
import kotlin.reflect.KClass

abstract class SuccessBuildSim<T, B : DSLBuilder<T>> : BuildSim<T, B>() {
    @TestTemplate
    override fun `build happy path - #scenario`(builder: B, result: T) {
        verifyHappyPath(builder, result)
    }
}

abstract class FailureBuildSim<T, B : DSLBuilder<T>> : BuildSim<T, B>() {
    @TestTemplate
    override fun `build failure path - #scenario`(builder: B, exceptionMessage: ExceptionMessage) {
        verifyRequiredField(builder, exceptionMessage)
    }
}

abstract class FullBuildSim<T, B : DSLBuilder<T>> : BuildSim<T, B>() {
    @TestTemplate
    override fun `build happy path - #scenario`(builder: B, result: T) {
        verifyHappyPath(builder, result)
    }

    @TestTemplate
    override fun `build failure path - #scenario`(builder: B, exceptionMessage: ExceptionMessage) {
        verifyRequiredField(builder, exceptionMessage)
    }
}

/**
 * Top level class for building simulations for DSL builders.
 * If the functions are overridden they will run based on the [TestTemplate]
 * Use the [BuildSim.buildSetup] function to set up the simulations.
 */
abstract class BuildSim<T, B : DSLBuilder<T>> : UnitSim() {
    open fun `build happy path - #scenario`(builder: B, result: T) {

    }

    open fun `build failure path - #scenario`(builder: B, exceptionMessage: ExceptionMessage) {

    }

    companion object {
        fun <S : BuildSim<T, B>, T, B : DSLBuilder<T>> buildSetup(
            klass: KClass<S>,
            successScenariosSet: TestScenarioSet<T, B>? = null,
            failureScenariosSet: TestScenarioSet<T, B>? = null
        ) {
            val successSimulationGroup = SimulationGroup.vars("scenario", "builder", "result")
            val failureSimulationGroup = SimulationGroup.vars("scenario", "builder", "exceptionMessage")

            successScenariosSet?.scenarios?.forEach {
                successSimulationGroup.with(it.id, it.given, it.expected)
            }

            failureScenariosSet?.scenarios?.forEach {
                failureSimulationGroup.with(it.id, it.given, it.exceptionMessage)
            }

            setup(
                klass,
                successSimulationGroup to { this::`build happy path - #scenario` },
                failureSimulationGroup to { this::`build failure path - #scenario` }
            )
        }

        const val PLACEHOLDER = "test_placeholder"
        val NOW = LocalDateTime.now()
        val BYTES: List<Byte> = listOf(0b1, 0b01)

        fun <T, B : ResourceDSLBuilder<T>> B.sharedMetadata() {
            metadata {
                name = PLACEHOLDER
                generatedName = PLACEHOLDER
                namespace = PLACEHOLDER
                labels {
                    label(PLACEHOLDER, PLACEHOLDER)
                }

                annotations {
                    annotations(PLACEHOLDER, PLACEHOLDER)
                }
            }
        }

        fun <T, L : K8sListResource<*, T>, B : ResourceListDSLBuilder<T, *, *, L>> B.sharedMetadata() {
            metadata {
                continueGather = PLACEHOLDER
                remainingItemCount = 1
                resourceVersion = PLACEHOLDER
            }
        }

        val METADATA = ObjectMetadata(
            name = PLACEHOLDER,
            generatedName = PLACEHOLDER,
            namespace = PLACEHOLDER,
            labels = listOf(Label(PLACEHOLDER, PLACEHOLDER)),
            annotations = listOf(K8sAnnotation(PLACEHOLDER, PLACEHOLDER))
        )

        val LIST_METADATA = ListMeta(
            continueGather = PLACEHOLDER,
            remainingItemCount = 1,
            resourceVersion = PLACEHOLDER
        )

        @JvmStatic
        protected val LABEL_SELECTOR = LabelSelector(
            matchExpressions = listOf(
                LabelSelectorRequirement(
                    key = PLACEHOLDER,
                    operator = PLACEHOLDER,
                    values = listOf(PLACEHOLDER)
                )
            ),
            matchLabels = listOf(
                Label(PLACEHOLDER, PLACEHOLDER)
            )
        )

        @JvmStatic
        protected fun LabelSelector.Builder.sharedSelector() {
            matchExpressions {
                requirement {
                    key = PLACEHOLDER
                    operator = PLACEHOLDER
                    values(PLACEHOLDER)
                }
            }

            matchLabels {
                label(PLACEHOLDER, PLACEHOLDER)
            }
        }

        @JvmStatic
        protected val ROLE_REF = RoleRef(
            apiGroup = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER
        )

        @JvmStatic
        protected fun RoleRef.Builder.sharedRoleRef() {
            apiGroup = PLACEHOLDER
            kind = PLACEHOLDER
            name = PLACEHOLDER
        }

        @JvmStatic
        protected val POLICY_RULE = PolicyRule(
            verbs = listOf(PLACEHOLDER),
            apiGroups = listOf(PLACEHOLDER),
            resources = listOf(PLACEHOLDER),
            resourceNames = listOf(PLACEHOLDER),
            nonResourceURLs = listOf(PLACEHOLDER)
        )

        @JvmStatic
        protected fun PolicyRule.Group.sharedPolicyRule() {
            rule {
                verbs(PLACEHOLDER)
                apiGroups(PLACEHOLDER)
                resources(PLACEHOLDER)
                resourceNames(PLACEHOLDER)
                nonResourceURLs(PLACEHOLDER)
            }
        }

        @JvmStatic
        protected val SUBJECT = K8sSubject(
            kind = PLACEHOLDER,
            name = PLACEHOLDER,
            apiGroup = PLACEHOLDER,
            namespace = PLACEHOLDER
        )

        @JvmStatic
        protected fun K8sSubject.Group.sharedSubject() {
            subject {
                kind = PLACEHOLDER
                name = PLACEHOLDER
                apiGroup = PLACEHOLDER
                namespace = PLACEHOLDER
            }
        }
    }
}