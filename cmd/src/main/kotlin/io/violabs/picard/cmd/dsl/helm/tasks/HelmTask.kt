package io.violabs.picard.cmd.dsl.helm.tasks

import io.violabs.picard.cmd.dsl.CommandLineTask

abstract class HelmTask : CommandLineTask {
    override var enabled: Boolean = true
}