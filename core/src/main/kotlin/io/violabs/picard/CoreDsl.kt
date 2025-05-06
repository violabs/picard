package io.violabs.picard

import io.violabs.picard.domain.manifest.Manifest


fun buildManifest(block: Manifest.Builder.() -> Unit): Manifest = Manifest.Builder().apply(block).build()