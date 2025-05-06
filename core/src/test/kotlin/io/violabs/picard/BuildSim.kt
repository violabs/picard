package io.violabs.picard

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceDSLBuilder
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.ConditionGroup
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.authorization.K8sSubject
import io.violabs.picard.domain.k8sResources.authorization.PolicyRule
import io.violabs.picard.domain.k8sResources.authorization.RoleRef
import io.violabs.picard.domain.k8sResources.policy.MatchResources
import io.violabs.picard.domain.k8sResources.policy.NamedRuleWithOperations
import io.violabs.picard.domain.k8sResources.workload.BaseStrategy
import io.violabs.picard.domain.k8sResources.workload.UpdateStrategy
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelectorTerm
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourcePool
import io.violabs.picard.domain.label.Label
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.label.LabelSelectorRequirement
import org.junit.jupiter.api.TestTemplate
import java.time.Instant
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
        val PLACEHOLDER_LIST: List<String> = listOf(PLACEHOLDER)
        val NOW = LocalDateTime.now()
        val TIMESTAMP = Instant.now()
        val BYTE_1: Byte = 0b1
        val BYTE_2: Byte = 0b01
        val BYTES: List<Byte> = listOf(BYTE_1, BYTE_2)
        val PORT_NUMBER = 8080
        val QUANTITY = Quantity(PLACEHOLDER)
        val QUANTITY_MAP = mapOf(PLACEHOLDER to QUANTITY)
        val QUANTITY_PAIR = PLACEHOLDER to QUANTITY
        val INT_OR_STRING_1 = IntOrString(1)
        val INT_OR_STRING_2 = IntOrString(str = "1")
        val STRING_MAP = mapOf(PLACEHOLDER to PLACEHOLDER)

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
                addLabelSelectorRequirement {
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
            addPolicyRule {
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
            addSubject {
                kind = PLACEHOLDER
                name = PLACEHOLDER
                apiGroup = PLACEHOLDER
                namespace = PLACEHOLDER
            }
        }

        @JvmStatic
        protected val CONDITION = Condition(
            status = BooleanType.True,
            type = PLACEHOLDER,
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER
        )

        @JvmStatic
        protected fun ConditionGroup<Condition, Condition.Builder>.sharedCondition() {
            condition {
                status = BooleanType.True
                type = PLACEHOLDER
                lastTransitionTime = NOW
                message = PLACEHOLDER
                reason = PLACEHOLDER
            }
        }

        @JvmStatic
        protected val NODE_CONDITION = _root_ide_package_.io.violabs.picard.domain.condition.NodeCondition(
            status = BooleanType.True,
            type = PLACEHOLDER,
            lastProbeTime = NOW,
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER
        )

        @JvmStatic
        protected fun io.violabs.picard.domain.condition.NodeConditionGroup.sharedNodeCondition() {
            condition {
                status = BooleanType.True
                type = PLACEHOLDER
                lastProbeTime = NOW
                lastTransitionTime = NOW
                message = PLACEHOLDER
                reason = PLACEHOLDER
            }
        }

        @JvmStatic
        protected val OBJECT_REFERENCE = ObjectReference(
            apiVersion = KAPIVersion.APIRegistrationV1,
            fieldPath = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            resourceVersion = PLACEHOLDER,
            uid = PLACEHOLDER
        )

        @JvmStatic
        protected fun ObjectReference.Builder.sharedObjectReference() {
            apiVersion = KAPIVersion.APIRegistrationV1
            fieldPath = PLACEHOLDER
            kind = PLACEHOLDER
            name = PLACEHOLDER
            namespace = PLACEHOLDER
            resourceVersion = PLACEHOLDER
            uid = PLACEHOLDER
        }

        @JvmStatic
        protected val NODE_SELECTOR_REQUIREMENT = NodeSelectorRequirement(
            key = PLACEHOLDER,
            operator = PLACEHOLDER,
            values = PLACEHOLDER_LIST
        )

        @JvmStatic
        protected val NODE_SELECTOR_TERM = NodeSelectorTerm(
            matchExpression = listOf(NODE_SELECTOR_REQUIREMENT),
            matchFields = listOf(NODE_SELECTOR_REQUIREMENT)
        )

        @JvmStatic
        protected val NODE_SELECTOR = NodeSelector(
            nodeSelectorTerms = listOf(NODE_SELECTOR_TERM)
        )

        @JvmStatic
        protected fun NodeSelectorTerm.Builder.sharedNodeSelectorTerm() {
            matchExpressions {
                requirement {
                    key = PLACEHOLDER
                    operator = PLACEHOLDER
                    values(PLACEHOLDER)
                }
            }

            matchFields {
                requirement {
                    key = PLACEHOLDER
                    operator = PLACEHOLDER
                    values(PLACEHOLDER)
                }
            }
        }

        @JvmStatic
        protected val STANDARD_CONTAINER = Container(PLACEHOLDER)

        private val NAMED_RULE = NamedRuleWithOperations(
            apiGroups = PLACEHOLDER_LIST,
            apiVersions = PLACEHOLDER_LIST,
            operations = PLACEHOLDER_LIST,
            resources = PLACEHOLDER_LIST,
            scope = PLACEHOLDER,
            resourceNames = PLACEHOLDER_LIST
        )

        @JvmStatic
        protected val MATCH_RESOURCES = MatchResources(
            excludeResourceRules = listOf(NAMED_RULE),
            matchPolicy = PLACEHOLDER,
            namespaceSelector = LABEL_SELECTOR,
            objectSelector = LABEL_SELECTOR,
            resourceRules = listOf(NAMED_RULE)
        )

        @JvmStatic
        protected fun MatchResources.Builder.sharedMatchResources() {
            excludeResourceRules {
                addNamedRuleWithOperations {
                    apiGroups(PLACEHOLDER)
                    apiVersions(PLACEHOLDER)
                    operations(PLACEHOLDER)
                    resources(PLACEHOLDER)
                    scope = PLACEHOLDER
                    resourceNames(PLACEHOLDER)
                }
            }

            matchPolicy = PLACEHOLDER

            namespaceSelector {
                sharedSelector()
            }

            objectSelector {
                sharedSelector()
            }

            resourceRules {
                addNamedRuleWithOperations {
                    apiGroups(PLACEHOLDER)
                    apiVersions(PLACEHOLDER)
                    operations(PLACEHOLDER)
                    resources(PLACEHOLDER)
                    scope = PLACEHOLDER
                    resourceNames(PLACEHOLDER)
                }
            }
        }

        @JvmStatic
        protected val OPAQUE_DEVICE_CONFIG = OpaqueDeviceConfiguration(
            driver = PLACEHOLDER,
            parameters = PLACEHOLDER
        )

        @JvmStatic
        protected val RESOURCE_POOL = ResourcePool(
            generation = 1,
            name = PLACEHOLDER,
            resourceSliceCount = 1
        )

        @JvmStatic
        protected fun ResourcePool.Builder.sharedResourcePool() {
            generation = 1
            name = PLACEHOLDER
            resourceSliceCount = 1
        }

        @JvmStatic
        protected val UPDATE_STRATEGY = UpdateStrategy(
            type = BaseStrategy.Type.RollingUpdate,
            rollingUpdate = BaseStrategy.RollingUpdate(
                maxUnavailable = 1,
                maxSurge = 1
            )
        )
    }
}