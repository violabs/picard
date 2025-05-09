package io.violabs.picard

import io.violabs.picard.domain.manifest.Manifest


/**
 * A DSL for building a [Manifest].
 * Current available sections:
 * * [Manifest.Builder.authenticationSection]
 * * [Manifest.Builder.authenticationSection]
 * * [Manifest.Builder.clusterSection]
 * * [Manifest.Builder.configSection]
 * * [Manifest.Builder.extendSection]
 * * [Manifest.Builder.policySection]
 * * [Manifest.Builder.serviceSection]
 * * [Manifest.Builder.storageSection]
 * * [Manifest.Builder.workloadSection]
 */
fun buildManifest(block: Manifest.Builder.() -> Unit): Manifest = Manifest.Builder().apply(block).build()