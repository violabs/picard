package io.violabs.picard.domain

/**
 * Non-UTF-8 encoded binary data
 */
data class BinaryData(
    override val content: MutableMap<String, List<Byte>> = mutableMapOf()
) : Data<List<Byte>>()

/**
 * UTF-8 encoded string
 */
data class TextData(
    override val content: MutableMap<String, String> = mutableMapOf()
) : Data<String>()

abstract class Data<V> {
    open val content: MutableMap<String, V> = mutableMapOf()

    fun add(key: String, value: V) {
        checkKey(key)
        content[key] = value
    }

    private fun checkKey(key: String) {
        if (!key.contains("a-z0-9.-_")) {
            throw IllegalArgumentException("Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: $key")
        }
    }
}