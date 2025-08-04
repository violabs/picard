package io.violabs.picard.v2.resources.workload.pod.security

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class WindowsSecurityContextOptions(
    /**
     * GMSACredentialSpec is where the GMSA admission webhook
     * (https://github.com/kubernetes-sigs/windows-gmsa) inlines
     * the contents of the GMSA credential spec named by the GMSACredentialUser field.
     */
    val gmsaCredentialSpec: String? = null,
    /**
     * GMSACredentialUser is the name of the GMSA credential user that this pod should run as.
     * This is the GMSA user name (Username@Domain).
     * This name must be in the GMSA credential spec named by the GMSACredentialSpec field,
     * and must be set for the pod to be able to use GMSA. If not specified,
     * the pod will not be able to use GMSA. Note that this name must be in the GMSA credential spec.
     */
    val gmsaCredentialUser: String? = null,
    /**
     * HostProcess determines if a container should be run as a 'Host Process' container.
     * All of a Pod's containers must have the same effective HostProcess value
     * (it is not allowed to have a mix of HostProcess containers and non-HostProcess containers).
     * In addition, if HostProcess is true then HostNetwork must also be set to true.
     */
    val hostProcess: Boolean? = null,
    /**
     * The UserName in Windows to run the entrypoint of the container process.
     * Defaults to the user specified in image metadata if unspecified.
     * May also be set in PodSecurityContext.
     * If set in both SecurityContext and PodSecurityContext,
     * the value specified in SecurityContext takes precedence.
     */
    val runAsUserName: String? = null
)