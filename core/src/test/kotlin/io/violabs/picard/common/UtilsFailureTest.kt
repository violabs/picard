package io.violabs.picard.common

import io.violabs.picard.domain.k8sResources.Quantity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.jvm.isAccessible

class UtilsFailureTest {
    
    @Test
    fun `vRequireNotNull throws exception for null property`() {
        class TestClass {
            val nullValue: String? = null
        }
        
        val testInstance = TestClass()
        val property = testInstance::nullValue
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotNull(property)
        }
        
        assert(exception.message?.contains("nullValue is required") == true)
    }
    
    @Test
    fun `vRequireNotNull returns value for non-null property`() {
        class TestClass {
            val nonNullValue: String? = "test"
        }
        
        val testInstance = TestClass()
        val property = testInstance::nonNullValue
        
        val result = vRequireNotNull(property)
        
        assert(result == "test")
    }
    
    @Test
    fun `vRequireNotEmpty with List and name throws exception for null list`() {
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(null as List<String>?, "testField")
        }
        
        assert(exception.message?.contains("testField is required and cannot be empty") == true)
    }
    
    @Test
    fun `vRequireNotEmpty with List and name throws exception for empty list`() {
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(emptyList<String>(), "testField")
        }
        
        assert(exception.message?.contains("testField is required and cannot be empty") == true)
    }
    
    @Test
    fun `vRequireNotEmpty with List and name returns list for valid list`() {
        val testList = listOf("item1", "item2")
        
        val result = vRequireNotEmpty(testList, "testField")
        
        assert(result == testList)
    }
    
    @Test
    fun `vRequireNotEmpty with property throws exception for empty list property`() {
        class TestClass {
            val emptyList: List<String>? = emptyList()
        }
        
        val testInstance = TestClass()
        val property = testInstance::emptyList
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(property)
        }
        
        assert(exception.message?.contains("emptyList is required and cannot be empty") == true)
    }
    
    @Test
    fun `vRequireNotEmpty with property throws exception for null list property`() {
        class TestClass {
            val nullList: List<String>? = null
        }
        
        val testInstance = TestClass()
        val property = testInstance::nullList
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(property)
        }
        
        assert(exception.message?.contains("nullList is required and cannot be empty") == true)
    }
    
    @Test
    fun `vRequireNotEmpty with property returns list for valid property`() {
        class TestClass {
            val validList: List<String>? = listOf("item1", "item2")
        }
        
        val testInstance = TestClass()
        val property = testInstance::validList
        
        val result = vRequireNotEmpty(property)
        
        assert(result.size == 2)
        assert(result[0] == "item1")
        assert(result[1] == "item2")
    }
    
    @Test
    fun `vRequireNotEmpty with function throws exception for empty list function`() {
        class TestClass {
            fun emptyListFunction(): List<String>? = emptyList()
        }
        
        val testInstance = TestClass()
        val function = testInstance::emptyListFunction
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(function)
        }
        
        assert(exception.message?.contains("emptyListFunction is required and cannot be empty") == true)
    }
    
    @Test
    fun `vRequireNotEmpty with function returns list for valid function`() {
        class TestClass {
            fun validListFunction(): List<String>? = listOf("item1", "item2")
        }
        
        val testInstance = TestClass()
        val function = testInstance::validListFunction
        
        val result = vRequireNotEmpty(function)
        
        assert(result.size == 2)
        assert(result[0] == "item1")
        assert(result[1] == "item2")
    }
    
    @Test
    fun `String quantity extension creates Quantity object`() {
        val quantityString = "100m"
        
        val result = quantityString.quantity()
        
        assert(result is Quantity)
        assert(result.value == quantityString)
    }
    
    @Test
    fun `String quantity extension handles empty string`() {
        val emptyString = ""
        
        val result = emptyString.quantity()
        
        assert(result is Quantity)
        assert(result.value == "")
    }
    
    @Test
    fun `String quantity extension handles various formats`() {
        val testCases = listOf("1Gi", "500Mi", "1", "1.5", "100m")
        
        for (testCase in testCases) {
            val result = testCase.quantity()
            assert(result is Quantity)
            assert(result.value == testCase)
        }
    }
}