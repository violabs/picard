package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SubjectTest : FailureBuildSim<Subject, SubjectDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SubjectTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Subject, SubjectDslBuilder> {
            requireScenario("kind") {
                given(SubjectDslBuilder())
            }

            requireScenario("name") {
                given(SubjectDslBuilder()) {
                    kind = Subject.Kind.Group
                }
            }
        }
    }
}