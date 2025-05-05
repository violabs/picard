package io.violabs.picard.domain.label

import io.violabs.picard.common.YAMLMap

data class Label(
    override val key: String,
    override val value: String
) : YAMLMap