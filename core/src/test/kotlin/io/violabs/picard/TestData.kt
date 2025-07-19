package io.violabs.picard

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.v2.common.*
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.PersistentVolumeClaimCondition
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.PersistentVolumeClaimConditionDslBuilder
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.PersistentVolumeClaimV2
import io.violabs.picard.v2.resources.configstorage.volume.KeyToPath
import io.violabs.picard.v2.resources.configstorage.volume.Volume
import io.violabs.picard.v2.resources.configstorage.volume.file.DownwardApiVolumeFile
import io.violabs.picard.v2.resources.configstorage.volume.projection.*
import io.violabs.picard.v2.resources.configstorage.volume.source.*
import io.violabs.picard.v2.resources.configstorage.volume.template.PersistentVolumeClaimTemplate


object Common {
    val OBJECT_META = ObjectMeta(
        name = PLACEHOLDER,
        namespace = PLACEHOLDER,
        labels = mapOf(PLACEHOLDER to PLACEHOLDER),
        annotations = mapOf(PLACEHOLDER to PLACEHOLDER)
    )

    fun ObjectMetaDslBuilder.sharedObjectMeta() {
        name = PLACEHOLDER
        generateName = PLACEHOLDER
        namespace = PLACEHOLDER
        labels(PLACEHOLDER to PLACEHOLDER)

        annotations(PLACEHOLDER to PLACEHOLDER)
    }

    val LABEL_SELECTOR = LabelSelector(
        matchExpressions = listOf(
            LabelSelectorRequirement(
                key = PLACEHOLDER,
                operator = PLACEHOLDER,
                values = listOf(PLACEHOLDER)
            )
        ),
        matchLabels = mapOf(PLACEHOLDER to PLACEHOLDER)
    )

    fun LabelSelectorDslBuilder.sharedSelector() {
        matchExpressions {
            labelSelectorRequirement {
                key = PLACEHOLDER
                operator = PLACEHOLDER
                values(PLACEHOLDER)
            }
        }
        matchLabels(PLACEHOLDER to PLACEHOLDER)
    }
}

object Conditions {
    val PERSISTENT_VOLUME_CLAIM_CONDITION = PersistentVolumeClaimCondition(
        type = PLACEHOLDER,
        status = BooleanType.True,
        lastProbeTime = PLACEHOLDER,
        lastTransitionTime = PLACEHOLDER,
        reason = PLACEHOLDER,
        message = PLACEHOLDER
    )

    fun PersistentVolumeClaimConditionDslBuilder.sharedPersistentVolumeClaimCondition() {
        type = PLACEHOLDER
        status = BooleanType.True
        lastProbeTime = PLACEHOLDER
        lastTransitionTime = PLACEHOLDER
        reason = PLACEHOLDER
        message = PLACEHOLDER
    }
}

object Volumes {
    val CLUSTER_TRUST_BUNDLE_PROJECTION = ClusterTrustBundleProjection(
        name = PLACEHOLDER,
        path = PLACEHOLDER,
        labelSelector = Common.LABEL_SELECTOR,
        signerName = PLACEHOLDER,
        optional = true
    )

    val CONFIG_MAP_PROJECTION = ConfigMapProjection(
        name = PLACEHOLDER,
        optional = true,
        items = listOf(KeyToPath(PLACEHOLDER, PLACEHOLDER))
    )

    val CONFIG_MAP_VOLUME_SOURCE = ConfigMapVolumeSource(
        name = PLACEHOLDER,
        items = listOf(KeyToPath(PLACEHOLDER, PLACEHOLDER)),
        defaultMode = 1,
        optional = true
    )

    val CSI_VOLUME_SOURCE = CsiVolumeSource(
        driver = PLACEHOLDER,
        fsType = PLACEHOLDER,
        nodePublishSecretRef = LocalObjectReference(PLACEHOLDER),
        readOnly = true,
        volumeAttributes = mapOf(PLACEHOLDER to PLACEHOLDER)
    )

    val DOWNWARD_API_PROJECTION = DownwardApiProjection(
        items = listOf(
            DownwardApiVolumeFile(
                path = PLACEHOLDER,
                fieldRef = ObjectFieldSelector(
                    fieldPath = PLACEHOLDER,
                    apiVersion = KAPIVersion.V1
                ),
                mode = 1,
                resourceFieldRef = ResourceFieldSelector(
                    resource = PLACEHOLDER,
                    containerName = PLACEHOLDER,
                    divisor = Quantity("1")
                )
            )
        )
    )

    val DOWNWARD_API_VOLUME_SOURCE = DownwardApiVolumeSource(
        defaultMode = 1,
        items = listOf(
            DownwardApiVolumeFile(
                path = PLACEHOLDER,
                fieldRef = ObjectFieldSelector(
                    fieldPath = PLACEHOLDER,
                    apiVersion = KAPIVersion.V1
                ),
                mode = 1,
                resourceFieldRef = ResourceFieldSelector(
                    resource = PLACEHOLDER,
                    containerName = PLACEHOLDER,
                    divisor = Quantity("1")
                )
            )
        )
    )

    val EMPTY_DIR_VOLUME_SOURCE = EmptyDirVolumeSource(
        medium = PLACEHOLDER,
        sizeLimit = Quantity("1")
    )

    val EPHEMERAL_VOLUME_SOURCE = EphemeralVolumeSource(
        volumeClaimTemplate = PersistentVolumeClaimTemplate(
            spec = PersistentVolumeClaimV2.Spec(),
            metadata = ObjectMeta()
        )
    )

    val FC_VOLUME_SOURCE = FcVolumeSource(
        fsType = PLACEHOLDER,
        lun = 1,
        readOnly = true,
        targetWwns = listOf(PLACEHOLDER),
        wwids = listOf(PLACEHOLDER)
    )

    val HOST_PATH_VOLUME_SOURCE = HostPathVolumeSource(
        path = PLACEHOLDER,
        type = PLACEHOLDER
    )

    val IMAGE_VOLUME_SOURCE = ImageVolumeSource(
        imagePullPolicy = PLACEHOLDER,
        reference = PLACEHOLDER
    )

    val ISCSI_VOLUME_SOURCE = IscsiVolumeSource(
        iqn = PLACEHOLDER,
        lun = 1,
        targetPortal = PLACEHOLDER,
        chapAuthDiscovery = true,
        chapAuthSession = true,
        fsType = PLACEHOLDER,
        initiatorName = PLACEHOLDER,
        iscsiInterface = PLACEHOLDER,
        portals = listOf(PLACEHOLDER),
        readOnly = true,
        secretRef = LocalObjectReference(PLACEHOLDER)
    )

    val NFS_VOLUME_SOURCE = NfsVolumeSource(
        server = PLACEHOLDER,
        path = PLACEHOLDER,
        readOnly = true
    )

    val PERSISTENT_VOLUME_CLAIM_VOLUME_SOURCE = PersistentVolumeClaimVolumeSource(
        claimName = PLACEHOLDER,
        readOnly = true
    )

    val SECRET_PROJECTION = SecretProjection(
        name = PLACEHOLDER,
        optional = true,
        items = listOf(KeyToPath(PLACEHOLDER, PLACEHOLDER))
    )

    val SECRET_VOLUME_SOURCE = SecretVolumeSource(
        secretName = PLACEHOLDER,
        optional = true,
        defaultMode = 1,
        items = listOf(KeyToPath(PLACEHOLDER, PLACEHOLDER))
    )

    val SERVICE_ACCOUNT_TOKEN_PROJECTION = ServiceAccountTokenProjection(
        audience = PLACEHOLDER,
        expirationSeconds = 1L,
        path = PLACEHOLDER
    )

    val PROJECTED_VOLUME_SOURCE = ProjectedVolumeSource(
        defaultMode = 1,
        sources = listOf(
            VolumeProjection(
                clusterTrustBundle = CLUSTER_TRUST_BUNDLE_PROJECTION,
                configMap = CONFIG_MAP_PROJECTION,
                downwardApi = DOWNWARD_API_PROJECTION,
                secret = SECRET_PROJECTION,
                serviceAccountToken = SERVICE_ACCOUNT_TOKEN_PROJECTION
            )
        )
    )

    val VOLUME = Volume(
        name = PLACEHOLDER,
        persistentVolumeClaim = PERSISTENT_VOLUME_CLAIM_VOLUME_SOURCE,
        configMap = CONFIG_MAP_VOLUME_SOURCE,
        secret = SECRET_VOLUME_SOURCE,
        downwardApi = DOWNWARD_API_VOLUME_SOURCE,
        projected = PROJECTED_VOLUME_SOURCE,
        emptyDir = EMPTY_DIR_VOLUME_SOURCE,
        hostPath = HOST_PATH_VOLUME_SOURCE,
        csi = CSI_VOLUME_SOURCE,
        ephemeral = EPHEMERAL_VOLUME_SOURCE,
        fc = FC_VOLUME_SOURCE,
        iscsi = ISCSI_VOLUME_SOURCE,
        image = IMAGE_VOLUME_SOURCE,
        nfs = NFS_VOLUME_SOURCE
    )
}