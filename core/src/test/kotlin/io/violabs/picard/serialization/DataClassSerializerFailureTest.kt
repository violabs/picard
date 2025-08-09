package io.violabs.picard.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DataClassSerializerFailureTest {
    
    @Test
    fun `DataClassSerializer with invalid key in TextData should fail during construction`() {
        // TextData constructor should validate keys and throw exception for invalid keys
        assertThrows<IllegalArgumentException> {
            TextData(mutableMapOf("invalid@key" to "value"))
        }
    }
    
    @Test
    fun `DataClassSerializer integrates with ObjectMapper for TextData`() {
        val mapper = ObjectMapper()
        val textData = TextData(mutableMapOf("test-key" to "test_value"))
        
        // Should serialize without exception
        val result = mapper.writeValueAsString(textData)
        
        assert(result.contains("test-key"))
        assert(result.contains("test_value"))
    }
    
    @Test
    fun `DataClassSerializer integrates with ObjectMapper for BinaryData`() {
        val mapper = ObjectMapper()
        val binaryData = BinaryData(mutableMapOf("key1" to "value1"), BinaryData.Type.Encoded)
        
        // Should serialize without exception
        val result = mapper.writeValueAsString(binaryData)
        
        assert(result.contains("key1"))
    }
    
    @Test
    fun `DataClassSerializer handles empty content`() {
        val mapper = ObjectMapper()
        val textData = TextData()
        
        // Should serialize empty content as empty object
        val result = mapper.writeValueAsString(textData)
        
        assert(result.contains("{}"))
    }
    
    @Test
    fun `DataClassSerializer validates BinaryData keys`() {
        assertThrows<IllegalArgumentException> {
            BinaryData(mutableMapOf("invalid@key" to "value"))
        }
    }
}