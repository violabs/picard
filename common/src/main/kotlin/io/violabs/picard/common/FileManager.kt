package io.violabs.picard.common

import java.io.File

class FileManager(logId: String) : DefaultLogger(logId) {
    fun addFile(label: String, fileName: String, content: String) {
        val file = File(fileName)
        file.writeText(content)

        log.info(label)
        content.split("\n").forEach { log.info("  | $it") }
    }
}
