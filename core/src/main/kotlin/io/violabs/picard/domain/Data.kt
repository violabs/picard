package io.violabs.picard.domain

/**
 * Non-UTF-8 encoded binary data
 */
data class BinaryData(
    override val content: MutableMap<String, List<Byte>> = mutableMapOf()
) : Data<List<Byte>>(content) {
    init {
        content.forEach { (key, _) ->
            checkKey(key)
        }
    }
}

/**
 * UTF-8 encoded string
 */
data class TextData(
    override val content: MutableMap<String, String> = mutableMapOf()
) : Data<String>(content) {
    init {
        content.forEach { (key, _) ->
            checkKey(key)
        }
    }
}

abstract class Data<V>(open val content: MutableMap<String, V> = mutableMapOf()) {
    fun add(key: String, value: V) {
        checkKey(key)
        content[key] = value
    }

    protected fun checkKey(key: String) {
        if (!key.contains("[a-z0-9.-_]".toRegex())) {
            throw IllegalArgumentException("Invalid key. Can only contain alphanumeric, '.', '-', '_' - key: $key")
        }
    }
}