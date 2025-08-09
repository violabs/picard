package io.violabs.picard.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import io.violabs.picard.domain.k8sResources.IntOrString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IntOrStringSerializerFailureTest {
    val mapper = ObjectMapper().apply {
        registerModule(SimpleModule().addSerializer(IntOrString::class.java, IntOrStringSerializer()))
    }


    @Test
    fun `IntOrStringSerializer with ObjectMapper serializes numeric value`() {
        val intOrString = IntOrString(num = 42)
        
        val result = mapper.writeValueAsString(intOrString)
        
        // Should serialize as a number, not a string
        assert(result.contains("42"))
        assert(!result.contains("\"42\"")) // Should not be quoted
    }
    
    @Test
    fun `IntOrStringSerializer with ObjectMapper serializes string value as number`() {
        val intOrString = IntOrString(str = "123")
        
        val result = mapper.writeValueAsString(intOrString)
        
        // Should serialize as a number
        assert(result.contains("123"))
    }
    
    @Test
    fun `IntOrStringSerializer integration test validates both values null`() {
        val intOrString = IntOrString(num = null, str = null)
        
        // This should fail during serialization due to requireNotNull
        assertThrows<Exception> {
            mapper.writeValueAsString(intOrString)
        }
    }

    @Test
    fun `IntOrStringSerializer integration test validates invalid string`() {
        val intOrString = IntOrString(str = "not_a_number")

        // This should fail during serialization due to String.toInt() failure
        assertThrows<Exception> {
            mapper.writeValueAsString(intOrString)
        }
    }

    @Test
    fun `IntOrStringSerializer integration test validates empty string`() {
        val intOrString = IntOrString(str = "")

        // This should fail during serialization
        assertThrows<Exception> {
            mapper.writeValueAsString(intOrString)
        }
    }

    @Test
    fun `IntOrStringSerializer integration test validates decimal string`() {
        val intOrString = IntOrString(str = "42.5")

        // This should fail during serialization since it's not a valid integer
        assertThrows<Exception> {
            mapper.writeValueAsString(intOrString)
        }
    }

    @Test
    fun `IntOrStringSerializer handles negative string numbers`() {
        val intOrString = IntOrString(str = "-42")
        
        val result = mapper.writeValueAsString(intOrString)
        
        // Should serialize negative number correctly
        assert(result.contains("-42"))
    }
}