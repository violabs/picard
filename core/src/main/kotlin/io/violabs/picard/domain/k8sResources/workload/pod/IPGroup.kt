package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.domain.BaseIP
import io.violabs.picard.domain.Group

class IPGroup<IP : BaseIP>(private val builder: (String) -> IP) : Group<IP>() {
    fun ips(): MutableList<IP>? = items()

    fun ip(content: String) {
        add(builder(content))
    }
}