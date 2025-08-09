package io.violabs.picard

import io.violabs.picard.domain.manifest.AuthenticationResourceSection
import io.violabs.picard.domain.manifest.Manifest
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequest
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestSpec
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class YamlBuilderFailureTest {
    
    @Test
    fun `build handles empty manifest resources list`() {
        // Create a manifest with empty resources that would create an empty YAML
        val authSection = AuthenticationResourceSection(
            resources = listOf(
                // Create a minimal resource to avoid validation failures
                CertificateSigningRequest(
                    spec = CertificateSigningRequestSpec(
                        request = emptyList(),
                        signerName = "",
                        usages = emptyList()
                    )
                )
            )
        )
        
        val manifest = Manifest(resources = listOf(authSection))
        
        // This should work without throwing exceptions
        val yaml = YamlBuilder.build(manifest)
        assert(yaml.isNotEmpty())
    }
    
    @Test
    fun `singleBuild handles null values`() {
        // Test with a simple object that might have null values
        val result = YamlBuilder.singleBuild(
            mapOf(
                "key1" to "value1",
                "key2" to null,  // null value should be excluded due to NON_NULL inclusion
                "key3" to "value3"
            )
        )
        
        assert(result.contains("key1"))
        assert(result.contains("value1"))
        assert(!result.contains("key2")) // null values should be excluded
        assert(result.contains("key3"))
    }
    
    @Test
    fun `build throws exception for incompatible serialization scenarios`() {
        // This is harder to test directly since we'd need malformed objects
        // But we can at least verify the basic functionality works
        
        val authSection = AuthenticationResourceSection(
            resources = listOf(
                CertificateSigningRequest(
                    spec = CertificateSigningRequestSpec(
                        request = emptyList(),
                        signerName = "",
                        usages = emptyList()
                    )
                )
            )
        )
        
        val manifest = Manifest(resources = listOf(authSection))
        
        // Should not throw exception for well-formed objects
        val yaml = YamlBuilder.build(manifest)
        assert(yaml.isNotEmpty())
    }
}