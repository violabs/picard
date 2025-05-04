//package io.violabs.picard
//
//import io.violabs.picard.domain.Kind
//import io.violabs.picard.domain.KubeletConfiguration
//import io.violabs.picard.domain.manifest.Manifest
//import io.violabs.picard.domain.PodResource
//import io.violabs.picard.domain.builder.KubeletConfigurationBuilder
//import io.violabs.picard.domain.builder.ManifestBuilder
//import io.violabs.picard.domain.builder.PodResourceBuilder
//import io.violabs.picard.domain.builder.scopedBuild
//
//fun ManifestBuilder.buildConfigMap(scope: PodResourceBuilder.() -> Unit) {
//    resource {
//        apiVersion = PodResource.Version.V1
//        kind = Kind.CONFIG_MAP
//        scope(this)
//    }
//}
//
//fun buildManifest(scope: ManifestBuilder.() -> Unit): Manifest = scopedBuild(::ManifestBuilder, scope)
//
//fun buildKubeletConfiguration(
//    scope: KubeletConfigurationBuilder.() -> Unit
//): KubeletConfiguration = scopedBuild(::KubeletConfigurationBuilder, scope)