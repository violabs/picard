package io.violabs.picard.starCharts.loki

import io.violabs.geordi.UnitSim
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuild
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildBuilder
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildBuilderBackoffConfig
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildPlanner
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildPlannerQueue
import io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildPlannerRetention
import org.junit.jupiter.api.Test

const val DEFAULT_DURATION_STR = "1m"
val DEFAULT_DURATION = Duration(DEFAULT_DURATION_STR)
const val DEFAULT_INT = 1
const val DEFAULT_STRING = "PLACEHOLDER"

class BloomBuildTest : UnitSim() {

    @Test
    fun `bloomBuild minimum`() = test {
        given {
            expect { BloomBuild() }

            whenever {
                bloomBuild {

                }
            }
        }
    }

    @Test
    fun `bloomBuild full`() = test {
        given {
            expect {
                BloomBuild(
                    enabled = true,
                    planner = BloomBuildPlanner(
                        planningInterval = DEFAULT_DURATION,
                        minTableOffset = DEFAULT_INT,
                        maxTableOffset = DEFAULT_INT,
                        retention = BloomBuildPlannerRetention(enabled = true),
                        queue = BloomBuildPlannerQueue(
                            maxQueuedTasksPerTenant = DEFAULT_INT,
                            storeTasksOnDisk = true,
                            tasksDiskDirectory = DEFAULT_STRING,
                            cleanTasksDirectory = true
                        )
                    ),
                    builder = BloomBuildBuilder(
                        grpcConfig = DEFAULT_STRING,
                        plannerAddress = DEFAULT_STRING,
                        backoffConfig = BloomBuildBuilderBackoffConfig(
                            minPeriod = DEFAULT_DURATION,
                            maxPeriod = DEFAULT_DURATION,
                            maxRetries = DEFAULT_INT
                        )
                    )
                )
            }

            whenever {
                bloomBuild {
                    enabled()
                    planner {
                        planningInterval(DEFAULT_DURATION_STR)
                        minTableOffset = DEFAULT_INT
                        maxTableOffset = DEFAULT_INT
                        retention {
                            enabled()
                        }
                        queue {
                            maxQueuedTasksPerTenant = DEFAULT_INT
                            storeTasksOnDisk()
                            tasksDiskDirectory = DEFAULT_STRING
                            cleanTasksDirectory()
                        }
                    }

                    builder {
                        grpcConfig = DEFAULT_STRING
                        plannerAddress = DEFAULT_STRING
                        backoffConfig {
                            minPeriod(DEFAULT_DURATION_STR)
                            maxPeriod(DEFAULT_DURATION_STR)
                            maxRetries = DEFAULT_INT
                        }
                    }
                }
            }
        }
    }
}