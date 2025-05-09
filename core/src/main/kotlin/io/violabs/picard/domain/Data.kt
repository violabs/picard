package io.violabs.picard.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.serialization.DataClassSerializer
import java.util.Base64

/**
 * Non-UTF-8 encoded binary data
 */
data class BinaryData(
    override val content: MutableMap<String, String> = mutableMapOf(),
    val type: Type = Type.Plaintext
) : Data(content) {
    init {
        content.forEach { (key, value) ->
            checkKey(key)

            this.content[key] = if (type == Type.Encoded) {
                value
            } else {
                Base64.getEncoder().encodeToString(value.toByteArray())
            }
        }
    }

    enum class Type {
        Encoded, Plaintext
    }
}

/**
 * UTF-8 encoded string
 */
data class TextData(
    override val content: MutableMap<String, String> = mutableMapOf()
) : Data(content) {
    init {
        content.forEach { (key, _) ->
            checkKey(key)
        }
    }
}

@JsonSerialize(using = DataClassSerializer::class)
abstract class Data(open val content: MutableMap<String, String> = mutableMapOf()) {
    fun add(key: String, value: String) {
        checkKey(key)
        content[key] = value
    }

    protected fun checkKey(key: String) {
        if (!key.contains("[a-z0-9.-_]".toRegex())) {
            throw IllegalArgumentException("Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: $key")
        }
    }
}