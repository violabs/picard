package io.violabs.picard.cmd.dsl

interface CommandLineTask {
    var enabled: Boolean
    fun cmd(): Array<String>

    fun disable() {
        enabled = false
    }
}