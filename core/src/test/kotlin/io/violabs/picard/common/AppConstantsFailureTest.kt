package io.violabs.picard.common

import org.junit.jupiter.api.Test

class AppConstantsFailureTest {
    
    @Test
    fun `AppConstants DefaultValue constants are accessible`() {
        // Since AppConstants just contains constants, we mainly test basic access
        // This provides coverage for the object and its nested object
        
        assert(AppConstants.DefaultValue.KAPI_VERSION_PACKAGE.isNotEmpty())
        assert(AppConstants.DefaultValue.KAPI_VERSION_CLASS.isNotEmpty())
        
        // Verify the constant values are as expected
        assert(AppConstants.DefaultValue.KAPI_VERSION_PACKAGE == "io.violabs.picard.domain.k8sResources")
        assert(AppConstants.DefaultValue.KAPI_VERSION_CLASS == "KAPIVersion")
    }
    
    @Test
    fun `AppConstants object can be referenced multiple times`() {
        // Test that the object singleton behavior works correctly
        val ref1 = AppConstants
        val ref2 = AppConstants
        
        assert(ref1 === ref2) // Same object reference
        assert(AppConstants.DefaultValue === AppConstants.DefaultValue) // Same nested object reference
    }
}