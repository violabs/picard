package io.violabs.picard.v2.resources.cluster.service.api

import com.fasterxml.jackson.annotation.JsonTypeName
import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * APIService represents a server for a particular GroupVersion.
 * 
 * apiVersion: apiregistration.k8s.io/v1
 */
@GeneratedDsl(withListGroup = true)
data class ApiServiceV2(
    @DefaultValue(
        "KAPIVersion.APIRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Spec contains information for locating and communicating with a server
     */
    val spec: ApiServiceSpec? = null,
    /**
     * Status contains derived information about an API server
     */
    val status: ApiServiceStatus? = null
) : ClusterResource<ApiServiceV2.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "APIService"
    }
}