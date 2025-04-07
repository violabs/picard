package io.violabs.picard.domain

/**
 * Non-UTF-8 encoded binary data
 */
class BinaryData : Data<List<Byte>>()

/**
 * UTF-8 encoded string
 */
class TextData : Data<String>()

abstract class Data<V> {
    private val content: MutableMap<String, V> = mutableMapOf()

    fun add(key: String, value: V) {
        checkKey(key)
        content[key] = value
    }

    private fun checkKey(key: String) {
        if (!key.contains("a-z0-9.-_")) {
            throw IllegalArgumentException("Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: $key")
        }
    }

    override fun toString(): String = content.toString()
}