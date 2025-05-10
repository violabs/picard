package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.dsl.CommandLineTask

abstract class KubeTask : CommandLineTask {
    override var enabled: Boolean = true
}