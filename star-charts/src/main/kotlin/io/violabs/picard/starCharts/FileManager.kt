package io.violabs.picard.starCharts

import io.violabs.picard.common.VLoggable
import java.io.File

class FileManager : VLoggable {

    fun addFile(label: String, fileName: String, content: String) {
        val file = File(fileName)

        file.parentFile?.mkdirs()

        file.writeText(content)

        log.info(label)
        content.split("\n").forEach { log.info("  | $it") }
    }
}
