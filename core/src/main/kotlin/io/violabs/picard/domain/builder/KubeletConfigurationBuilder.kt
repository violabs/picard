package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.KubeletConfiguration

class KubeletConfigurationBuilder : Builder<KubeletConfiguration> {
    var kind: Kind? = null
    var port: Int? = null
    var address: String? = null
    var serializeImagePulls: Boolean = false
    private var apiVersion: ApiVersionBuilder? = null
    private var evictionHard: KubeletConfiguration.EvictionHard? = null

    override fun build(): KubeletConfiguration {
        val apiVersion = requireNotNull(apiVersion) { "Please provide an apiVersion" }

        val apiVersionString = requireNotNull(apiVersion.definedVersion?.ref ?: apiVersion.customVersion) {
            "Please provide a version"
        }

        return KubeletConfiguration(
            apiVersion = apiVersionString,
            kind = requireNotNull(kind) { "Please provide a kind. ${Kind.entries}" },
            port = requireNotNull(port) { "Please provide a port" },
            address = requireNotNull(address) { "Please provide an address" },
            serializeImagePulls = serializeImagePulls,
            evictionHard = evictionHard
        )
    }

    fun apiVersion(scope: ApiVersionBuilder.() -> Unit) {
        apiVersion = ApiVersionBuilder().apply(scope)
    }

    fun evictionHard(scope: EvictionHardBuilder.() -> Unit) {
        evictionHard = EvictionHardBuilder().apply(scope).build()
    }
}