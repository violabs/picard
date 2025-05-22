package io.violabs.picard.dsl.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class GeneratedDsl(
    val withGroup: Boolean = false,
    val withMapGroup: String = MapGroupTypes.NONE
) {
    object MapGroupTypes {
        const val NONE = "NONE"
        const val SINGLE = "SINGLE"
        const val LIST = "LIST"
        const val ALL = "ALL"
    }

    enum class MapGroupType {
        NONE, SINGLE, LIST, ALL;

        companion object {
            val ACTIVE_TYPES = listOf(SINGLE, LIST, ALL)
        }
    }
}