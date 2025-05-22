package io.violabs.picard.common

import java.io.File

class FileManager(logId: String) : DefaultLogger(logId) {
    fun addFile(label: String, fileName: String, content: String) {
        val file = File(fileName)

        file.parentFile?.mkdirs()

        file.writeText(content)

        logger.info(label)
        content.split("\n").forEach { logger.info("  | $it") }
    }
}
