package io.violabs.picard.common

import kotlin.reflect.KClass

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
    const val INFO = "${Colors.CYAN}INFO ${Colors.RESET}"
    const val DEBUG = "${Colors.BLUE}DEBUG${Colors.RESET}"
    const val ERROR = "${Colors.RED}ERROR${Colors.RESET}"

    const val CMD = "CMD"
    const val TUTORIAL = "TUTORIAL"
}

// Fixed padding length to ensure consistent log formatting
private const val FIXED_PADDING_LENGTH = 15
private val FRONT_LOADED_SPACES = System.getProperty("frontLoadedSpaces")?.toBoolean() ?: true
private const val PADDING_CHAR = '·' // Simple middle dot for padding

class Logger(private val logId: String) {
    private var isDebugEnabled = false

    // Apply fixed padding to ensure consistent log formatting
    private val formattedName: String = if (logId.length < FIXED_PADDING_LENGTH) {
        var padding = PADDING_CHAR.toString().repeat(FIXED_PADDING_LENGTH - logId.length)
        padding = "${Colors.PURPLE}$padding${Colors.RESET}"
        if (FRONT_LOADED_SPACES) "$padding$logId" else "$logId$padding"
    } else {
        logId
    }

    fun enableDebug() {
        isDebugEnabled = true
    }

    fun info(message: Any) {
        val id = Logging.ID_TEMPLATE.format(formattedName)
        println("${Logging.LOGO} ${Logging.INFO} $id ${Logging.DELIMITER} $message")
    }

    fun debug(message: Any) {
        if (!isDebugEnabled) return
        val id = Logging.ID_TEMPLATE.format(formattedName)
        println("${Logging.LOGO} ${Logging.DEBUG} $id ${Logging.DELIMITER} $message")
    }

    fun error(message: Any) {
        val id = Logging.ID_TEMPLATE.format(formattedName)
        println("${Logging.LOGO} ${Logging.ERROR} $id ${Logging.DELIMITER} ${Colors.RED}$message${Colors.RESET}")
    }

    // For multi-line logging with consistent indentation
    fun infoMultiline(message: String) {
        val lines = message.split("\n")

        if (lines.isEmpty()) return

        // Log the first line normally
        info(lines.first())

        // For subsequent lines, maintain proper indentation
        val id = Logging.ID_TEMPLATE.format(formattedName)
        val prefix = "${Logging.LOGO} ${Logging.INFO} $id ${Logging.DELIMITER}   | "

        lines.drop(1).forEach { line ->
            println("$prefix$line")
        }
    }
}

private val LOG_MAP = mutableMapOf<String, Logger>()
private val DEBUG_ENABLED = System.getProperty("debug")?.toBoolean() ?: false

interface VLoggable {
    fun logId(): String? = null
    val log: Logger
        get() {
            val name = logId() ?: this::class.simpleName ?: "DefaultLogger"

            return LOG_MAP.getOrPut(name) {
                val logger = Logger(name)
                if (DEBUG_ENABLED) logger.enableDebug()
                logger
            }
        }
}

abstract class DefaultLogger(private val logId: String) : VLoggable {
    constructor(klass: KClass<*>) : this(klass.simpleName ?: "DefaultLogger")

    override fun logId(): String = logId

    init {
        register()
    }

    private fun register() {
        if (LOG_MAP.containsKey(logId())) return

        val logger = Logger(logId())
        if (DEBUG_ENABLED) logger.enableDebug()
        LOG_MAP[logId()] = logger
    }
}