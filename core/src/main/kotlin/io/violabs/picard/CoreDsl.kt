package io.violabs.picard

import io.violabs.picard.domain.KubeletConfiguration
import io.violabs.picard.domain.Manifest
import io.violabs.picard.domain.builder.KubeletConfigurationBuilder
import io.violabs.picard.domain.builder.ManifestBuilder
import io.violabs.picard.domain.builder.scopedBuild

fun buildManifest(scope: ManifestBuilder.() -> Unit): Manifest = scopedBuild(::ManifestBuilder, scope)

fun buildKubeletConfiguration(
    scope: KubeletConfigurationBuilder.() -> Unit
): KubeletConfiguration = scopedBuild(::KubeletConfigurationBuilder, scope)