package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class IPBlock(
    val cidr: String,
    val except: List<String>? = null
) {
    class Builder : DSLBuilder<IPBlock> {
        var cidr: String? = null
        private var except: List<String>? = null

        fun except(vararg except: String) {
            this.except = except.toList()
        }

        override fun build(): IPBlock {
            return IPBlock(
                cidr = vRequireNotNull(this::cidr),
                except = except
            )
        }
    }
}