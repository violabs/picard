package io.violabs.picard

import io.violabs.picard.v2.resources.config.map.data
import io.violabs.picard.v2.resources.config.secret.stringData
import org.junit.jupiter.api.Test

class YamlBuilderBranchCoverageTest {

    @Test
    fun `build handles multi-resource scenarios correctly`() {
        // Create a manifest with multiple resources to trigger resources.size > 1 branch
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata {
                        name = "test-config"
                    }
                    data("key1" to "value1")
                }
            }
            serviceSection {
                service {
                    metadata {
                        name = "test-service"
                    }
                    spec {
                        selector("app" to "test")
                        ports {
                            servicePort {
                                port = 80
                                targetPort = "8080"
                            }
                        }
                    }
                }
            }
        }

        // This should trigger the multi-resource path (resources.size > 1)
        val result = YamlBuilder.build(manifest)
        
        // Verify that result contains multiple YAML documents separated by newlines
        assert(result.contains("\n"))
        assert(result.contains("kind: ConfigMap"))
        assert(result.contains("kind: Service"))
    }

    @Test
    fun `build handles single resource correctly`() {
        // Create a manifest with only one resource
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata {
                        name = "single-config"
                    }
                    data("single-key" to "single-value")
                }
            }
        }

        // This should trigger the single resource path (resources.size <= 1)
        val result = YamlBuilder.build(manifest)
        
        // Verify single resource output
        assert(result.contains("kind: ConfigMap"))
        assert(result.contains("single-config"))
    }

    @Test
    fun `build handles multiple sections with multiple resources`() {
        // Test with many resources to ensure multi-resource branch is well exercised
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata { name = "config1" }
                    data("key1" to "value1")
                }
                configMap {
                    metadata { name = "config2" }
                    data("key2" to "value2")
                }
            }
            serviceSection {
                service {
                    metadata { name = "service1" }
                    spec {
                        selector("app" to "test1")
                        ports {
                            servicePort { port = 80 }
                        }
                    }
                }
                service {
                    metadata { name = "service2" }
                    spec {
                        selector("app" to "test2")
                        ports {
                            servicePort { port = 90 }
                        }
                    }
                }
            }
        }

        // This should definitely trigger resources.size > 1
        val result = YamlBuilder.build(manifest)
        
        // Verify multiple resources are present
        assert(result.contains("config1"))
        assert(result.contains("config2"))
        assert(result.contains("service1"))
        assert(result.contains("service2"))
        assert(result.split("\n").size > 10) // Multiple YAML docs
    }

    @Test
    fun `singleBuild method coverage`() {
        // Test the singleBuild method directly for method coverage
        val testObject = mapOf("test" to "value", "another" to 123)
        
        val result = YamlBuilder.singleBuild(testObject)
        
        assert(result.contains("test: value"))
        assert(result.contains("another: 123"))
    }

    @Test
    fun `yamlFactory and listYamlFactory configuration coverage`() {
        // Test that the factories are configured correctly
        val yamlFactory = YamlBuilder.yamlFactory
        val listYamlFactory = YamlBuilder.listYamlFactory
        
        // Verify they are properly configured YAMLFactory instances
        assert(yamlFactory != null)
        assert(listYamlFactory != null)
        assert(yamlFactory != listYamlFactory) // They should be different instances
    }

    @Test
    fun `build processes complex nested structures`() {
        // Test complex nested structures that might trigger serialization edge cases
        val manifest = buildManifest {
            configSection {
                secret {
                    metadata {
                        name = "complex-secret"
                    }
                    stringData("nested" to """
                        {
                            "complex": {
                                "data": ["item1", "item2"],
                                "numbers": [1, 2, 3]
                            }
                        }
                    """.trimIndent())
                }
            }
        }

        val result = YamlBuilder.build(manifest)
        assert(result.contains("kind: Secret"))
        assert(result.contains("complex-secret"))
    }
}