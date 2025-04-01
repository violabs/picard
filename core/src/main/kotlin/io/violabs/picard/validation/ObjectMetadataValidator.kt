package io.violabs.picard.validation

import io.violabs.picard.domain.ObjectMetadata

object ObjectMetadataValidator {
    fun hasValidName(metadata: ObjectMetadata) {
        val hasAssignedName = metadata.name != null
        val hasGeneratedName = metadata.generatedName != null
        val hasOnlyAssignedName = hasAssignedName && !hasGeneratedName
        val hasOnlyGeneratedName = !hasAssignedName && hasGeneratedName
        if (hasOnlyAssignedName || hasOnlyGeneratedName) {
            return
        }

        throw IllegalArgumentException("Please provide either a name or a generated name")
    }
}