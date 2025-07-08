package io.violabs.picard.cmd.dsl

import io.violabs.picard.common.Logger
import io.violabs.picard.common.Logging
import kotlin.reflect.KClass

abstract class CommandLineProcessor<T : CommandLineTask>(val type: KClass<T>) {
    protected val logger = Logger(Logging.CMD)

    fun processTask(taskSupplier: () -> T) = processTask(taskSupplier())

    fun <S : T> processTask(task: S, block: S.() -> Unit = {}) = processTask(task.apply(block))

    fun processTask(task: T) {
        if (!task.enabled) {
            logger.info("${type.simpleName} is disabled, skipping execution. command: ${task.cmd().joinToString(" ")}")
            return
        }

        logger.info("Executing command: ${task.cmd().joinToString(" ")}")
        try {
            val process = ProcessBuilder(*task.cmd())
                .redirectErrorStream(true)  // Merge stderr with stdout
                .start()

            val lines = process.inputReader().readLines().toMutableList()

            // Check process exit code
            val exitCode = process.waitFor()
            if (exitCode != 0) {
                lines.forEach { logger.error(it) }
            } else {
                lines.forEach { logger.info(it) }
            }
        } catch (e: Exception) {
            logger.info("Error executing command: ${task.cmd().joinToString(" ")}")
            logger.info("Exception: ${e.message}")
            throw e
        }
    }
}