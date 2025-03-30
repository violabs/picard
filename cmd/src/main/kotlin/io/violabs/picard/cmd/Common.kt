package io.violabs.picard.cmd

object Colors {
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
    const val CMD = "${Colors.CYAN}[${Colors.RESET}CMD${Colors.CYAN}]${Colors.RESET}"
}

object KubectlConstants {
    const val KUBECTL = "kubectl"

    const val APPLY = "apply"
    const val GET = "get"
    const val DELETE = "delete"

    const val FILE_FLAG = "-f"

    const val PODS = "pods"
    const val POD = "pod"
}

fun log(message: String) {
    println("${Logging.LOGO} ${Logging.CMD} ${Logging.DELIMITER} $message")
}