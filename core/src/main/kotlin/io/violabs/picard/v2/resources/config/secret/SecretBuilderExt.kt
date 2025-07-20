package io.violabs.picard.v2.resources.config.secret

import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData

fun SecretV2DslBuilder.data(vararg bytes: Pair<String, String>) {
    data = BinaryData(bytes.toMap().toMutableMap())
}

fun SecretV2DslBuilder.data(type: BinaryData.Type, vararg bytes: Pair<String, String>) {
    data = BinaryData(bytes.toMap().toMutableMap(), type)
}

fun SecretV2DslBuilder.stringData(vararg strings: Pair<String, String>) {
    stringData = TextData(strings.toMap().toMutableMap())
}