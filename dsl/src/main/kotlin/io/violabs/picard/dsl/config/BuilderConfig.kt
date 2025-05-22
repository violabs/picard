package io.violabs.picard.dsl.config

import io.violabs.picard.common.Logger

class BuilderConfig(
    map: Map<String, String?>,
    logger: Logger
) {
    val dslBuilderClasspath: String by map
    val dslMarkerClass: String? by map

    init {
        logger.debug("dslBuilderClasspath: $dslBuilderClasspath")
        logger.debug("dslMarkerClass: $dslMarkerClass")
    }
}