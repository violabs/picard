package io.violabs.picard.v2.resources.config.map

import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData

fun ConfigMapV2DslBuilder.binaryData(vararg bytes: Pair<String, String>) {
    binaryData = BinaryData(bytes.toMap().toMutableMap())
}

fun ConfigMapV2DslBuilder.binaryData(type: BinaryData.Type, vararg bytes: Pair<String, String>) {
    binaryData = BinaryData(bytes.toMap().toMutableMap(), type)
}

fun ConfigMapV2DslBuilder.data(vararg strings: Pair<String, String>) {
    data = TextData(strings.toMap().toMutableMap())
}