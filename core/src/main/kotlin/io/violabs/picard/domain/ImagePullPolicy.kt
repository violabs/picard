package io.violabs.picard.domain

enum class ImagePullPolicy : K8sEnum {
    Always,
    Never,
    IfNotPresent;
}