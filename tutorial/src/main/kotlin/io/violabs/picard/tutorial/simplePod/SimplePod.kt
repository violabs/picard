package io.violabs.picard.tutorial.simplePod

import io.violabs.picard.*

object SimplePodFactory {
    fun buildSimplePod(): String {
        val config = Pod(
            apiVersion = Pod.Version.V1,
            kind = Kind.POD,
            metadata = Metadata(
                name = "nginx"
            ),
            spec = Spec(
                containers = listOf(
                    Container(
                        name = "nginx",
                        image = "nginx:1.14.2",
                        ports = listOf(
                            Container.Port(
                                containerPort = 80
                            )
                        )
                    )
                )
            )
        )

        return buildPodYaml(config)
    }

    fun buildSimplePodDsl(): String {
        return ""
    }
}

