package io.violabs.picard

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.v2.resources.authorization.role.binding.RoleRef
import org.junit.jupiter.api.TestTemplate
import java.time.Instant
import java.time.LocalDateTime
import kotlin.reflect.KClass

abstract class SuccessBuildSim<T, B : DslBuilder<T>> : BuildSim<T, B>() {
    @TestTemplate
    override fun `build happy path - #scenario`(builder: B, result: T) {
        verifyHappyPath(builder, result)
    }
}

abstract class FailureBuildSim<T, B : DslBuilder<T>> : BuildSim<T, B>() {
    @TestTemplate
    override fun `build failure path - #scenario`(builder: B, exceptionMessage: ExceptionMessage) {
        verifyRequiredField(builder, exceptionMessage)
    }
}

abstract class FullBuildSim<T, B : DslBuilder<T>> : BuildSim<T, B>() {
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
abstract class BuildSim<T, B : DslBuilder<T>> : UnitSim() {
    open fun `build happy path - #scenario`(builder: B, result: T) {

    }

    open fun `build failure path - #scenario`(builder: B, exceptionMessage: ExceptionMessage) {

    }

    companion object {
        fun <S : BuildSim<T, B>, T, B : DslBuilder<T>> buildSetup(
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

        val LIST_METADATA = Common.LIST_META
    }
}