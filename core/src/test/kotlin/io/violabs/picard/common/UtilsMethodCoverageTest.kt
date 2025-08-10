package io.violabs.picard.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

class UtilsMethodCoverageTest {

    // Test data class to use for property reflection testing
    private class TestData(
        val nullableProperty: String? = null,
        val emptyList: List<String> = emptyList(),
        val nonEmptyList: List<String> = listOf("item1", "item2"),
        val nullListProperty: List<String>? = null
    ) {
        fun getNullListFunction(): List<String>? = null
        fun getEmptyListFunction(): List<String> = emptyList()
        fun getNonEmptyListFunction(): List<String> = listOf("func1", "func2")
    }

    @Test
    fun `vRequireNotNull with valid property returns value`() {
        val testData = TestData()
        val property = testData::nonEmptyList
        
        val result = vRequireNotNull(property)
        
        assert(result == listOf("item1", "item2"))
    }

    @Test
    fun `vRequireNotNull with null property throws exception`() {
        val testData = TestData()
        val property = testData::nullableProperty
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotNull(property)
        }
        
        assert(exception.message?.contains("nullableProperty") == true)
        assert(exception.message?.contains("is required") == true)
    }

    @Test
    fun `vRequireNotEmpty with valid list and name returns list`() {
        val validList = listOf("item1", "item2")
        
        val result = vRequireNotEmpty(validList, "testList")
        
        assert(result == validList)
    }

    @Test
    fun `vRequireNotEmpty with null list throws exception`() {
        val nullList: List<String>? = null
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(nullList, "nullList")
        }
        
        assert(exception.message?.contains("nullList") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `vRequireNotEmpty with empty list throws exception`() {
        val emptyList = emptyList<String>()
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(emptyList, "emptyList")
        }
        
        assert(exception.message?.contains("emptyList") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `vRequireNotEmpty with property accessor and valid list returns list`() {
        val testData = TestData()
        val property = testData::nonEmptyList
        
        val result = vRequireNotEmpty(property)
        
        assert(result == listOf("item1", "item2"))
    }

    @Test
    fun `vRequireNotEmpty with property accessor and empty list throws exception`() {
        val testData = TestData()
        val property = testData::emptyList
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(property)
        }
        
        assert(exception.message?.contains("emptyList") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `vRequireNotEmpty with property accessor and null list throws exception`() {
        val testData = TestData()
        val property = testData::nullListProperty
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(property)
        }
        
        assert(exception.message?.contains("nullListProperty") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `vRequireNotEmpty with function accessor and valid list returns list`() {
        val testData = TestData()
        val function: KFunction<List<String>> = testData::getNonEmptyListFunction
        
        val result = vRequireNotEmpty(function)
        
        assert(result == listOf("func1", "func2"))
    }

    @Test
    fun `vRequireNotEmpty with function accessor and empty list throws exception`() {
        val testData = TestData()
        val function: KFunction<List<String>> = testData::getEmptyListFunction
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(function)
        }
        
        assert(exception.message?.contains("getEmptyListFunction") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `vRequireNotEmpty with function accessor and null list throws exception`() {
        val testData = TestData()
        val function: KFunction<List<String>?> = testData::getNullListFunction
        
        val exception = assertThrows<IllegalArgumentException> {
            vRequireNotEmpty(function)
        }
        
        assert(exception.message?.contains("getNullListFunction") == true)
        assert(exception.message?.contains("is required and cannot be empty") == true)
    }

    @Test
    fun `quantity extension function coverage`() {
        // Test the public quantity extension function for full method coverage
        val quantityString = "100Mi"
        
        val quantity = quantityString.quantity()
        
        assert(quantity.value == "100Mi")
    }
}