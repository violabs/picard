package io.violabs.picard.domain.builder

import io.violabs.picard.domain.*

class PodResourceBuilder : Builder<PodResource> {
    var apiVersion: PodResource.Version = PodResource.Version.V1
    var kind: Kind? = null
    private var metadata: Metadata? = null
    var spec: Spec? = null
    var status: PodStatus? = null

    override fun build(): PodResource {
        return PodResource(
            apiVersion = apiVersion,
            kind = requireNotNull(kind) { "Please provide a kind. ${Kind.entries}" },
            metadata = requireNotNull(metadata) { "Please provide a metadata" },
            spec = requireNotNull(spec) { "Please provide a spec" },
            status = status
        )
    }

    fun metadata(scope: MetadataBuilder.() -> Unit) {
        metadata = scopedBuild(::MetadataBuilder, scope)
    }

    fun spec(scope: SpecBuilder.() -> Unit) {
        spec = scopedBuild(::SpecBuilder, scope)
    }

    fun status(scope: PodStatusBuilder.() -> Unit) {
        status = scopedBuild(::PodStatusBuilder, scope)
    }
}