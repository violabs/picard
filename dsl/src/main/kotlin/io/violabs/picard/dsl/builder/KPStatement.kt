package io.violabs.picard.dsl.builder

@PicardDSLMarker
internal class KPStatement(
    var statement: String,
    var args: MutableList<Any> = mutableListOf()
) {

    @PicardDSLMarker
    internal class Group {
        val items: MutableList<KPStatement> = mutableListOf()

        fun addLine(statement: String, vararg args: Any) {
            items.add(KPStatement(statement, args.toMutableList()))
        }
    }
}