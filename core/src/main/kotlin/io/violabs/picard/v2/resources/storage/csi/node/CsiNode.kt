package io.violabs.picard.v2.resources.storage.csi.node

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriver

/**
 * CSINode holds information about all CSI drivers installed on a node.
 * apiVersion: storage.k8s.io/v1
 *
 * import "k8s.io/api/storage/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/csi-node-v1/
 *
 * CSINode holds information about all CSI drivers installed on a node. CSI drivers do not need to create
 * the CSINode object directly. As long as they use the node-driver-registrar sidecar container, the kubelet
 * will automatically populate the CSINode object for the CSI driver as part of kubelet plugin registration.
 * CSINode has the same name as a node. If the object is missing, it means either there are no CSI Drivers
 * available on the node, or the Kubelet version is low enough that it doesn't create this object. CSINode has
 * an OwnerReference that points to the corresponding node object.
 */
@GeneratedDsl(withListGroup = true)
data class CsiNode(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: CsiDriver.Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the specification of CSINode
     */
    val spec: CsiNodeSpec
) : ConfigResource<CsiDriver.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSINode"
    }
}