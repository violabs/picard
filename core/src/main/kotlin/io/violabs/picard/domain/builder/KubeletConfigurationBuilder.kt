package io.violabs.picard.domain.builder

import io.violabs.picard.domain.CrashLoopBackOff
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.KubeletConfiguration

class KubeletConfigurationBuilder : Builder<KubeletConfiguration> {
    var kind: Kind? = null
    var port: Int? = null
    var address: String? = null
    var serializeImagePulls: Boolean = false
    private var apiVersion: String? = null
    private var evictionHard: KubeletConfiguration.EvictionHard? = null
    private var crashLoopBackOff: CrashLoopBackOff? = null

    override fun build(): KubeletConfiguration {
        return KubeletConfiguration(
            apiVersion = requireNotNull(apiVersion) { "Please provide an apiVersion" },
            kind = requireNotNull(kind) { "Please provide a kind. ${Kind.entries}" },
            port = requireNotNull(port) { "Please provide a port" },
            address = requireNotNull(address) { "Please provide an address" },
            serializeImagePulls = serializeImagePulls,
            evictionHard = evictionHard,
            crashLoopBackOff = crashLoopBackOff
        )
    }

    fun apiVersion(scope: ApiVersionBuilder.() -> Unit) {
        apiVersion = scopedBuild(::ApiVersionBuilder, scope)
    }

    fun evictionHard(scope: EvictionHardBuilder.() -> Unit) {
        evictionHard = scopedBuild(::EvictionHardBuilder, scope)
    }

    fun crashLoopBackOff(scope: CrashLoopBackOffBuilder.() -> Unit) {
        crashLoopBackOff = scopedBuild(::CrashLoopBackOffBuilder, scope)
    }
}