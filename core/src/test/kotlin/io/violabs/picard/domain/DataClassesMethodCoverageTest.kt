package io.violabs.picard.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Base64

class DataClassesMethodCoverageTest {

    @Test
    fun `BinaryData with Plaintext type encodes values to Base64`() {
        val originalValue = "test-value"
        val content = mutableMapOf("key1" to originalValue)
        
        val binaryData = BinaryData(content, BinaryData.Type.Plaintext)
        
        val expectedEncodedValue = Base64.getEncoder().encodeToString(originalValue.toByteArray())
        assert(binaryData.content["key1"] == expectedEncodedValue)
        assert(binaryData.type == BinaryData.Type.Plaintext)
    }

    @Test
    fun `BinaryData with Encoded type keeps values unchanged`() {
        val encodedValue = "dGVzdC12YWx1ZQ==" // Base64 for "test-value"
        val content = mutableMapOf("key1" to encodedValue)
        
        val binaryData = BinaryData(content, BinaryData.Type.Encoded)
        
        assert(binaryData.content["key1"] == encodedValue)
        assert(binaryData.type == BinaryData.Type.Encoded)
    }

    @Test
    fun `BinaryData with default type uses Plaintext and encodes values`() {
        val originalValue = "default-test"
        val content = mutableMapOf("key1" to originalValue)
        
        val binaryData = BinaryData(content)
        
        val expectedEncodedValue = Base64.getEncoder().encodeToString(originalValue.toByteArray())
        assert(binaryData.content["key1"] == expectedEncodedValue)
        assert(binaryData.type == BinaryData.Type.Plaintext)
    }

    @Test
    fun `BinaryData validates keys during initialization`() {
        val content = mutableMapOf("invalid key!" to "value")
        
        val exception = assertThrows<IllegalArgumentException> {
            BinaryData(content)
        }
        
        assert(exception.message?.contains("Invalid key") == true)
        assert(exception.message?.contains("invalid key!") == true)
    }

    @Test
    fun `BinaryData with empty content works correctly`() {
        val binaryData = BinaryData()
        
        assert(binaryData.content.isEmpty())
        assert(binaryData.type == BinaryData.Type.Plaintext)
    }

    @Test
    fun `BinaryData with multiple valid keys processes all keys`() {
        val originalValues = mapOf(
            "key1" to "value1",
            "key-2" to "value2",
            "key_3" to "value3",
            "key.4" to "value4",
            "key5ABC" to "value5"
        )
        val content = originalValues.toMutableMap()
        
        val binaryData = BinaryData(content, BinaryData.Type.Plaintext)
        
        assert(binaryData.content.size == 5)
        originalValues.keys.forEach { key ->
            val expectedValue = Base64.getEncoder().encodeToString(originalValues[key]!!.toByteArray())
            assert(binaryData.content[key] == expectedValue)
        }
    }

    @Test
    fun `TextData validates keys during initialization`() {
        val content = mutableMapOf("valid-key" to "value1", "invalid@key" to "value2")
        
        val exception = assertThrows<IllegalArgumentException> {
            TextData(content)
        }
        
        assert(exception.message?.contains("Invalid key") == true)
        assert(exception.message?.contains("invalid@key") == true)
    }

    @Test
    fun `TextData with valid keys works correctly`() {
        val content = mutableMapOf(
            "key1" to "value1",
            "key-2" to "value2",
            "key_3" to "value3",
            "key.4" to "value4"
        )
        
        val textData = TextData(content)
        
        assert(textData.content == content)
        assert(textData.content.size == 4)
    }

    @Test
    fun `TextData with empty content works correctly`() {
        val textData = TextData()
        
        assert(textData.content.isEmpty())
    }

    @Test
    fun `Data add method with valid key adds to content`() {
        val textData = TextData()
        
        textData.add("valid-key", "test-value")
        
        assert(textData.content["valid-key"] == "test-value")
        assert(textData.content.size == 1)
    }

    @Test
    fun `Data add method with invalid key throws exception`() {
        val textData = TextData()
        
        val exception = assertThrows<IllegalArgumentException> {
            textData.add("invalid@key", "value")
        }
        
        assert(exception.message?.contains("Invalid key") == true)
        assert(exception.message?.contains("invalid@key") == true)
    }

    @Test
    fun `Data add method overwrites existing key`() {
        val textData = TextData(mutableMapOf("key1" to "original"))
        
        textData.add("key1", "updated")
        
        assert(textData.content["key1"] == "updated")
        assert(textData.content.size == 1)
    }

    @Test
    fun `checkKey validates alphanumeric characters`() {
        val textData = TextData()
        
        // These should work without throwing exceptions
        textData.add("abc123", "value")
        textData.add("ABC123", "value")
        textData.add("key-with-dashes", "value")
        textData.add("key_with_underscores", "value")
        textData.add("key.with.dots", "value")
        textData.add("a", "value")
        textData.add("1", "value")
        
        assert(textData.content.size == 7)
    }

    @Test
    fun `checkKey rejects invalid characters`() {
        val textData = TextData()
        
        val invalidKeys = listOf(
            "key with spaces",
            "key@symbol",
            "key#hash",
            "key\$dollar",
            "key%percent",
            "key+plus",
            "key=equals",
            "key[bracket",
            "key{brace",
            "key(paren",
            "key:colon",
            "key;semicolon",
            "key'quote",
            "key\"doublequote",
            "key/slash",
            "key\\backslash",
            "key?question",
            "key!exclamation"
        )
        
        invalidKeys.forEach { invalidKey ->
            val exception = assertThrows<IllegalArgumentException> {
                textData.add(invalidKey, "value")
            }
            assert(exception.message?.contains("Invalid key") == true)
            assert(exception.message?.contains(invalidKey) == true)
        }
        
        // Ensure no keys were added
        assert(textData.content.isEmpty())
    }

    @Test
    fun `checkKey handles edge cases`() {
        val textData = TextData()
        
        // Test empty string key
        val exception1 = assertThrows<IllegalArgumentException> {
            textData.add("", "value")
        }
        assert(exception1.message?.contains("Invalid key") == true)
        
        // Test key with only valid characters at boundaries
        textData.add("a-b_c.d", "value") // Should work
        assert(textData.content["a-b_c.d"] == "value")
    }

    @Test
    fun `BinaryData Type enum has correct string representations`() {
        assert(BinaryData.Type.Plaintext.toString() == "Plaintext")
        assert(BinaryData.Type.Encoded.toString() == "Encoded")
    }
}