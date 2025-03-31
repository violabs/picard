package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.Pod
import io.violabs.picard.domain.Spec
import io.violabs.picard.scopedBuild
import io.violabs.picard.domain.Metadata

class PodBuilder : Builder<Pod> {
    var apiVersion: Pod.Version = Pod.Version.V1
    var kind: Kind? = null
    private var metadata: Metadata? = null
    var spec: Spec? = null

    override fun build(): Pod {
        return Pod(
            apiVersion = apiVersion,
            kind = requireNotNull(kind) { "Please provide a kind. ${Kind.entries}" },
            metadata = requireNotNull(metadata) { "Please provide a metadata" },
            spec = requireNotNull(spec) { "Please provide a spec" }
        )
    }

    fun metadata(scope: MetadataBuilder.() -> Unit) {
        metadata = scopedBuild(::MetadataBuilder, scope)
    }

    fun spec(scope: SpecBuilder.() -> Unit) {
        spec = scopedBuild(::SpecBuilder, scope)
    }
}