package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.action.ExecAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.HTTPGetAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.SleepAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.TCPSocketAction

data class LifecycleHandler(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val sleep: SleepAction? = null,
    val tcpSocket: TCPSocketAction? = null
) {
    class Builder : DslBuilder<LifecycleHandler> {
        private var exec: ExecAction? = null
        private var httpGet: HTTPGetAction? = null
        private var sleep: SleepAction? = null
        private var tcpSocket: TCPSocketAction? = null

        fun exec(init: ExecAction.Builder.() -> Unit) {
            exec = ExecAction.Builder().apply(init).build()
        }

        fun httpGet(init: HTTPGetAction.Builder.() -> Unit) {
            httpGet = HTTPGetAction.Builder().apply(init).build()
        }

        fun sleep(seconds: Long) {
            sleep = SleepAction(seconds)
        }

        fun tcpSocket(init: TCPSocketAction.Builder.() -> Unit) {
            tcpSocket = TCPSocketAction.Builder().apply(init).build()
        }

        override fun build(): LifecycleHandler {
            return LifecycleHandler(
                exec,
                httpGet,
                sleep,
                tcpSocket
            )
        }
    }
}