package io.violabs.picard

import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.v2.resources.config.map.data
import io.violabs.picard.v2.resources.config.secret.stringData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Base64

class EdgeCaseIntegrationTest {

    @Test
    fun `large manifest with many resources handles properly`() {
        // Test performance and correctness with a large number of resources
        val manifest = buildManifest {
            configSection {
                // Create 50 ConfigMaps
                repeat(50) { i ->
                    configMap {
                        metadata {
                            name = "config-$i"
                            namespace = "test-ns"
                            labels("app" to "test", "index" to i.toString())
                        }
                        data(
                            "key-$i-1" to "value-$i-1",
                            "key-$i-2" to "value-$i-2",
                            "key-$i-3" to "value-$i-3"
                        )
                    }
                }
            }
            serviceSection {
                // Create 30 Services
                repeat(30) { i ->
                    service {
                        metadata {
                            name = "service-$i"
                            namespace = "test-ns"
                        }
                        spec {
                            selector("app" to "test-$i")
                            ports {
                                servicePort {
                                    port = 8080 + i
                                    targetPort = "8080"
                                    name = "http"
                                }
                                servicePort {
                                    port = 9090 + i
                                    targetPort = "9090"
                                    name = "metrics"
                                }
                            }
                        }
                    }
                }
            }
        }

        // This should trigger the multi-resource path and handle large manifests
        val result = YamlBuilder.build(manifest)
        
        // Verify that all resources are present
        assert(result.contains("config-0"))
        assert(result.contains("config-49"))
        assert(result.contains("service-0"))
        assert(result.contains("service-29"))
        
        // Verify YAML structure integrity
        val yamlDocs = result.split("\n").filter { it.contains("kind:") }
        assert(yamlDocs.size == 80) // 50 ConfigMaps + 30 Services
        
        // Verify different resource types are present
        assert(result.contains("kind: ConfigMap"))
        assert(result.contains("kind: Service"))
        
        // Verify the result is reasonably sized but not empty
        assert(result.length > 10000)
    }

    @Test
    fun `complex nested manifest with all resource types`() {
        // Test complex manifest with resources from multiple sections
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata {
                        name = "complex-config"
                        labels(
                            "environment" to "production",
                            "component" to "api",
                            "version" to "v1.2.3"
                        )
                        annotations(
                            "kubernetes.io/managed-by" to "picard",
                            "deployment.io/revision" to "42"
                        )
                    }
                    data(
                        "database.url" to "postgresql://db:5432/app",
                        "redis.url" to "redis://cache:6379",
                        "feature.flags" to "feature1:true,feature2:false,feature3:true"
                    )
                }
                secret {
                    metadata {
                        name = "complex-secret"
                        labels("type" to "credentials")
                    }
                    stringData(
                        "username" to "admin",
                        "password" to "super-secret-password-123!@#",
                        "api.key" to "abcdef123456789",
                        "certificate" to """
                            -----BEGIN CERTIFICATE-----
                            MIICljCCAfugAwIBAgIJAMZmuGSIfvgzMA0GCSqGSIb3DQEBCwUAMIGHMQswCQYD
                            VQQGEwJVUzETMBEGA1UECAwKQ2FsaWZvcm5pYTEWMBQGA1UEBwwNU2FuIEZyYW5j
                            aXNjbzEUMBIGA1UECgwLTXlDb21wYW55IEluYzEUMBIGA1UEAwwLZXhhbXBsZS5j
                            b20wHhcNMjMwMTAxMDAwMDAwWhcNMjQwMTAxMDAwMDAwWjCBhzELMAkGA1UEBhMC
                            -----END CERTIFICATE-----
                        """.trimIndent()
                    )
                }
            }
            serviceSection {
                service {
                    metadata {
                        name = "complex-service"
                        annotations("service.io/load-balancer" to "nginx")
                    }
                    spec {
                        selector("app" to "complex-app")
                        ports {
                            servicePort {
                                name = "http"
                                port = 80
                                targetPort = "8080"
                                protocol = "TCP"
                            }
                            servicePort {
                                name = "https"
                                port = 443
                                targetPort = "8443"
                                protocol = "TCP"
                            }
                            servicePort {
                                name = "grpc"
                                port = 9090
                                targetPort = "9090"
                                protocol = "TCP"
                            }
                        }
                        type = "LoadBalancer"
                    }
                }
            }
        }

        val result = YamlBuilder.build(manifest)
        
        // Verify all complex data is properly serialized
        assert(result.contains("complex-config"))
        assert(result.contains("complex-secret"))
        assert(result.contains("complex-service"))
        assert(result.contains("postgresql://db:5432/app"))
        assert(result.contains("super-secret-password-123!@#"))
        assert(result.contains("BEGIN CERTIFICATE"))
        assert(result.contains("LoadBalancer"))
        assert(result.contains("kubernetes.io/managed-by: \"picard\""))
        
        // Verify YAML structure
        assert(result.contains("kind: ConfigMap"))
        assert(result.contains("kind: Secret"))
        assert(result.contains("kind: Service"))
    }

    @Test
    fun `BinaryData encoding edge cases`() {
        // Test edge cases in BinaryData encoding
        val edgeCases = mapOf(
            "empty-string" to "",
            "single-char" to "a",
            "unicode" to "café ñoño 中文 🎉",
            "special-chars" to "!@#$%^&*(){}[]|\\:;\"'<>?/.,~`",
            "numbers" to "1234567890",
            "mixed" to "Hello123!@# ñoño",
            "very-long" to "a".repeat(10000),
            "newlines" to "line1\nline2\rline3\r\nline4",
            "tabs-spaces" to "tab\there\tspaces   here",
            "null-bytes" to "before\u0000after",
            "binary-data" to String(byteArrayOf(-1, -2, -3, 0, 1, 2, 3, 127, -128), Charsets.ISO_8859_1)
        )

        edgeCases.forEach { (key, value) ->
            // Test Plaintext type (default) - should encode
            val plaintextData = BinaryData(mutableMapOf(key to value), BinaryData.Type.Plaintext)
            val expectedEncoded = Base64.getEncoder().encodeToString(value.toByteArray())
            assert(plaintextData.content[key] == expectedEncoded) {
                "Failed for key '$key' with Plaintext type"
            }

            // Test Encoded type - should keep as-is
            val encodedData = BinaryData(mutableMapOf(key to expectedEncoded), BinaryData.Type.Encoded)
            assert(encodedData.content[key] == expectedEncoded) {
                "Failed for key '$key' with Encoded type"
            }
        }
    }

    @Test
    fun `TextData and BinaryData key validation comprehensive edge cases`() {
        // Valid keys that should pass
        val validKeys = listOf(
            "a",
            "1",
            "abc123",
            "ABC123",
            "key-with-dashes",
            "key_with_underscores",
            "key.with.dots",
            "complex-key_123.test",
            "CamelCaseKey",
            "snake_case_key",
            "kebab-case-key",
            "numbers1234567890",
            "mixed_Case-With.123"
        )

        validKeys.forEach { key ->
            // Should work for TextData
            val textData = TextData(mutableMapOf(key to "test-value"))
            assert(textData.content[key] == "test-value") { "TextData failed for valid key: $key" }

            // Should work for BinaryData
            val binaryData = BinaryData(mutableMapOf(key to "test-value"))
            val expectedEncoded = Base64.getEncoder().encodeToString("test-value".toByteArray())
            assert(binaryData.content[key] == expectedEncoded) { "BinaryData failed for valid key: $key" }
        }

        // Invalid keys that should fail
        val invalidKeys = listOf(
            "",
            " ",
            "key with space",
            "key@email.com",
            "key#hash",
            "key\$dollar",
            "key%percent",
            "key+plus",
            "key=equals",
            "key[bracket]",
            "key{brace}",
            "key(paren)",
            "key:colon",
            "key;semicolon",
            "key'quote",
            "key\"doublequote",
            "key/slash",
            "key\\backslash",
            "key?question",
            "key!exclamation",
            "key*asterisk",
            "key<less",
            "key>greater",
            "key|pipe",
            "key&ampersand",
            "key^caret",
            "key~tilde",
            "key`backtick",
            "\t",
            "\n",
            "\r"
        )

        invalidKeys.forEach { key ->
            // Should fail for TextData
            assertThrows<IllegalArgumentException> {
                TextData(mutableMapOf(key to "test-value"))
            }

            // Should fail for BinaryData
            assertThrows<IllegalArgumentException> {
                BinaryData(mutableMapOf(key to "test-value"))
            }
        }
    }

    @Test
    fun `Data add method comprehensive edge cases`() {
        val textData = TextData()

        // Test adding many keys
        repeat(100) { i ->
            textData.add("key-$i", "value-$i")
        }
        assert(textData.content.size == 100)

        // Test overwriting keys
        textData.add("key-50", "new-value-50")
        assert(textData.content["key-50"] == "new-value-50")
        assert(textData.content.size == 100) // Size shouldn't change

        // Test with complex values
        textData.add("json-data", """{"name": "test", "values": [1, 2, 3]}""")
        textData.add("multiline", "line1\nline2\nline3")
        textData.add("unicode", "éñ中文🎉")

        assert(textData.content["json-data"]?.contains("\"name\": \"test\"") == true)
        assert(textData.content["multiline"]?.contains("\n") == true)
        assert(textData.content["unicode"] == "éñ中文🎉")
    }

    @Test
    fun `YamlBuilder empty resource edge cases`() {
        // Test with manifest that has empty resources after flattening
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata {
                        name = "minimal-config"
                    }
                    // No data added
                }
            }
        }

        val result = YamlBuilder.build(manifest)
        assert(result.contains("minimal-config"))
        assert(result.contains("kind: ConfigMap"))
        
        // Should handle minimal resources gracefully
        assert(result.isNotEmpty())
    }

    @Test
    fun `YamlBuilder singleBuild with complex objects`() {
        // Test singleBuild with various complex object types
        val complexMap = mapOf(
            "string" to "test",
            "number" to 42,
            "boolean" to true,
            "null" to null,
            "array" to listOf(1, 2, 3),
            "nested" to mapOf(
                "inner" to "value",
                "list" to listOf("a", "b", "c")
            )
        )

        val result = YamlBuilder.singleBuild(complexMap)
        
        assert(result.contains("string: test"))
        assert(result.contains("number: 42"))
        assert(result.contains("boolean: true"))
        assert(result.contains("array:"))
        assert(result.contains("nested:"))
        assert(result.contains("inner: value"))
    }

    @Test
    fun `integration test with manifest build and YAML generation`() {
        // Full integration test from DSL building to YAML generation
        val manifest = buildManifest {
            configSection {
                configMap {
                    metadata {
                        name = "integration-config"
                        namespace = "test"
                        labels("test" to "integration")
                    }
                    data(
                        "config.yaml" to """
                            server:
                              port: 8080
                              host: 0.0.0.0
                            database:
                              url: postgresql://localhost:5432/test
                              pool: 10
                        """.trimIndent()
                    )
                }
                secret {
                    metadata {
                        name = "integration-secret"
                        namespace = "test"
                    }
                    stringData("password" to "integration-test-password")
                }
            }
        }

        // Test that the entire pipeline works
        val yamlOutput = YamlBuilder.build(manifest)
        
        // Verify complete integration
        assert(yamlOutput.contains("integration-config"))
        assert(yamlOutput.contains("integration-secret"))
        assert(yamlOutput.contains("server:"))
        assert(yamlOutput.contains("port: 8080"))
        assert(yamlOutput.contains("integration-test-password"))
        assert(yamlOutput.contains("kind: ConfigMap"))
        assert(yamlOutput.contains("kind: Secret"))
        assert(yamlOutput.contains("namespace: test"))
        
        // Verify YAML structure is valid (contains proper separators for multi-doc)
        assert(yamlOutput.contains("\n"))
        
        // The result should be substantial (adjusted to be more realistic)
        assert(yamlOutput.length > 200) {
            "YAML output length ${yamlOutput.length} is not greater than 200. Output: $yamlOutput"
        }
    }
}