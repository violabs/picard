package io.violabs.picard

import io.violabs.picard.domain.KubeletConfiguration
import io.violabs.picard.domain.Pod
import io.violabs.picard.domain.builder.KubeletConfigurationBuilder
import io.violabs.picard.domain.builder.PodBuilder
import io.violabs.picard.domain.builder.scopedBuild

fun buildPod(scope: PodBuilder.() -> Unit): Pod = scopedBuild(::PodBuilder, scope)

fun buildKubeletConfiguration(
    scope: KubeletConfigurationBuilder.() -> Unit
): KubeletConfiguration = scopedBuild(::KubeletConfigurationBuilder, scope)
