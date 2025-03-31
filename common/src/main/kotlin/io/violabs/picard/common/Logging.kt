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

class Logger(private val logId: String) {
    private var isDebugEnabled = false
    var logName: String = logId

    fun enableDebug() {
        isDebugEnabled = true
    }

    fun info(message: Any) {
        val id = Logging.ID_TEMPLATE.format(logName)
        println("${Logging.LOGO} ${Logging.INFO} $id ${Logging.DELIMITER} $message")
    }

    fun debug(message: Any) {
        if (!isDebugEnabled) return
        val id = Logging.ID_TEMPLATE.format(logName)
        println("${Logging.LOGO} ${Logging.DEBUG} $id ${Logging.DELIMITER} $message")
    }

    fun error(message: Any) {
        val id = Logging.ID_TEMPLATE.format(logName)
        println("${Logging.LOGO} ${Logging.ERROR} $id ${Logging.DELIMITER} ${Colors.RED}$message${Colors.RESET}")
    }
}

private val LOG_MAP = mutableMapOf<String, Logger>()

private fun adjustLogNames() {
    val longestKey = LOG_MAP.keys.maxByOrNull { it.length }?.length ?: 0

    LOG_MAP.forEach { (key, logger) ->

        if (key.length < longestKey) {
            val padding = " ".repeat(longestKey - key.length)
            logger.logName = if (FRONT_LOADED_SPACES) "$padding$key" else "$key$padding"
        }
    }
}

private val DEBUG_ENABLED = System.getProperty("debug")?.toBoolean() ?: false
private val FRONT_LOADED_SPACES = System.getProperty("frontLoadedSpaces")?.toBoolean() ?: true

interface VLoggable {
    fun logId(): String? = null
    val log: Logger
        get() {
            val name = logId() ?: this::class.simpleName ?: "DefaultLogger"

            return LOG_MAP.getOrPut(name) {
                val logger = Logger(name)

                if (DEBUG_ENABLED) logger.enableDebug()

                logger
            }.also {
                adjustLogNames()
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

        val name = logId()
        val logger = Logger(name)

        if (DEBUG_ENABLED) logger.enableDebug()

        LOG_MAP[name] = logger

        adjustLogNames()
    }
}