package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.domain.BaseIP
import io.violabs.picard.domain.DefaultGroup

class IPGroup<IP : BaseIP>(private val builder: (String) -> IP) : DefaultGroup<IP>() {
    fun ips(): MutableList<IP>? = items()

    fun addIp(content: String) {
        add(builder(content))
    }
}