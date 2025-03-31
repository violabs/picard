package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Metadata

class MetadataBuilder : Builder<Metadata> {
    var name: String? = null

    override fun build(): Metadata = Metadata(
        name = requireNotNull(name) { "Please provide a name" }
    )
}