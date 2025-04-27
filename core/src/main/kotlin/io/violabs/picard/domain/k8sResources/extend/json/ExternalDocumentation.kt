package io.violabs.picard.domain.k8sResources.extend.json

data class ExternalDocumentation(
    val description: String? = null,
    val url: String? = null
) {
    class Builder {
        var description: String? = null
        var url: String? = null

        fun build(): ExternalDocumentation {
            return ExternalDocumentation(
                description = description,
                url = url
            )
        }
    }
}
