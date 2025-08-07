package io.violabs.picard.v2.resources.config.secret

import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData

fun SecretDslBuilder.data(vararg bytes: Pair<String, String>) {
    data = BinaryData(bytes.toMap().toMutableMap())
}

fun SecretDslBuilder.data(type: BinaryData.Type, vararg bytes: Pair<String, String>) {
    data = BinaryData(bytes.toMap().toMutableMap(), type)
}

fun SecretDslBuilder.stringData(vararg strings: Pair<String, String>) {
    stringData = TextData(strings.toMap().toMutableMap())
}