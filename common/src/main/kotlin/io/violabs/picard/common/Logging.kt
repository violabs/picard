package io.violabs.picard.common


private object Colors {
    const val RESET = "\u001B[0m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
    const val BLUE = "\u001B[34m"
    const val PURPLE = "\u001B[35m"
    const val CYAN = "\u001B[36m"
}

object Logging {
    const val LOGO = "${Colors.GREEN}picard${Colors.RESET}"
    const val DELIMITER = "${Colors.PURPLE}*${Colors.CYAN}>>${Colors.RESET}"
    const val ID_TEMPLATE = "${Colors.CYAN}[${Colors.RESET}%s${Colors.CYAN}]${Colors.RESET}"

    const val CMD = "CMD"
    const val TUTORIAL = "TUTORIAL"
}

class Logger(private val logId: String) {

    fun log(message: String) {
        val id = Logging.ID_TEMPLATE.format(logId)
        println("${Logging.LOGO} $id ${Logging.DELIMITER} $message")
    }
}