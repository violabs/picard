package io.violabs.picard

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.DslBuilder

fun <B : DslBuilder<T>, T> dslBuild(builder: B, block: B.() -> Unit): T {
    return builder.apply(block).build()
}

fun <T> UnitSim.verifyRequiredField(fieldName: String, builder: DslBuilder<T>) = test<Unit> {
    given {
        wheneverThrows<IllegalArgumentException> {
            whenFn = { builder.build() }
            result = {
                assertOrLog(
                    it.message,
                    "$fieldName must not be null"
                )
            }
        }
    }
}

fun <T> UnitSim.verifyRequiredField(builder: DslBuilder<T>, exceptionMessage: ExceptionMessage) = test<Unit> {
    given {
        wheneverThrows<IllegalArgumentException> {
            whenFn = { builder.build() }
            result = {
                assertOrLog(
                    it.message,
                    exceptionMessage.content
                )
            }
        }
    }
}

fun <B : DslBuilder<T>, T> UnitSim.verifyHappyPath(
    builder: B,
    expected: T,
    scope: B.() -> Unit = {}
) = test {
    given {
        val finalBuilder = builder.apply(scope)

        expect { expected }

        whenever {
            finalBuilder.build()
        }
    }
}