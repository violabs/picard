package io.violabs.picard

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CoreDslFailureTest {
    
    @Test
    fun `buildManifest fails when no resources provided`() {
        val exception = assertThrows<IllegalArgumentException> {
            buildManifest {
                // Empty manifest - no resource sections added
            }
        }
        
        // The validation message comes from vRequireNotEmpty in Manifest.Builder
        assert(exception.message?.contains("resources") == true)
    }
    
    @Test
    fun `buildManifest fails when resource sections are empty`() {
        val exception = assertThrows<IllegalArgumentException> {
            buildManifest {
                // Adding sections but with no actual resources
                configSection {
                    // No configMap or secret resources added
                }
            }
        }
        
        // Should fail due to empty resources in ConfigResourceSection
        assert(exception.message?.contains("resources") == true)
    }
}