package io.violabs.picard.starCharts.loki.schemaConfig

import com.fasterxml.jackson.annotation.JsonProperty

data class Ingester(
    @JsonProperty("chunk_encoding")
    val chunkEncoding: String
)