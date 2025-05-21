package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
class Trace(
    /**
     *     # When enabled, low-level S3 HTTP operation information is logged at the
     *     # debug level.
     *     # CLI flag: -<prefix>.s3.trace.enabled
     *     [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null
)