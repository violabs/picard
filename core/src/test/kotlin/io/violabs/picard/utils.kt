package io.violabs.picard

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.DSLBuilder

fun <B : DSLBuilder<T>, T> dslBuild(builder: B, block: B.() -> Unit): T {
    return builder.apply(block).build()
}

fun <T> UnitSim.verifyRequiredField(fieldName: String, builder: DSLBuilder<T>) = test<Unit> {
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

fun <T> UnitSim.verifyRequiredField(builder: DSLBuilder<T>, exceptionMessage: ExceptionMessage) = test<Unit> {
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

fun <B : DSLBuilder<T>, T> UnitSim.verifyHappyPath(
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