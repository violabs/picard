package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.violabs.picard.common.Logger
import io.violabs.picard.starCharts.FileManager
import io.violabs.picard.starCharts.StarChartsYamlBuilder
import io.violabs.picard.starCharts.loki.gatewayConfig.Service
import java.time.LocalDate

private val logger = Logger("LokiMain")

val yamlBuilder = StarChartsYamlBuilder(
    objectMapperBuilder = {
        setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    }
)

val fileManager = FileManager()

val lokiRunner = LokiRunner(yamlBuilder)

fun main(vararg args: String) {
    logger.info("Running Loki DSL...")

    lokiRunner.addResource {
        config {
            schemaConfig {
                configs {
                    periodConfig {
                        from = LocalDate.now()
                    }
                }
            }

        }

        deploymentMode = DeploymentMode.SimpleScalable

        backend {
            replicas = 3
        }

        read {
            replicas = 3
        }

        write {
            replicas = 3
        }

        minio {
            enabled(false)
        }

        gateway {
            service(Service.Type.LoadBalancer)
        }
    }

    logger.info("Building YAML configuration...")
    val yaml = lokiRunner.buildYaml()

    fileManager.addFile("Loki Configuration", "./star-charts/loki/src/main/resources/examples/generated/loki.yaml", yaml)

    logger.info("Finished Loki DSL execution.")
}