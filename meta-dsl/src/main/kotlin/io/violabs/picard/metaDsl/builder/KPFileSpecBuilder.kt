package io.violabs.picard.metaDsl.builder

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

class KPFileSpecBuilder {
    var className: ClassName? = null
    private var types = mutableListOf<TypeSpec>()
    private var imports = mutableListOf<Pair<String, String>>()
    private var functions = mutableListOf<FunSpec>()

    fun types(block: KPTypeSpecBuilder.Group.() -> Unit) {
        types = KPTypeSpecBuilder.Group().apply(block).items
    }

    fun functions(block: KPFunSpecBuilder.Group.() -> Unit) {
        functions = KPFunSpecBuilder.Group().apply(block).items
    }

    fun functions(funs: MutableList<FunSpec>) { this.functions = funs }

    fun types(vararg specs: TypeSpec) {
        this.types = specs.toMutableList()
    }

    fun addImport(packageName: String, methodName: String) {
        imports.add(packageName to methodName)
    }

    fun addImportIf(condition: Boolean, packageName: String, simpleName: String) =
        if (condition) addImport(packageName, simpleName) else Unit

    fun build(): FileSpec {
        val className = requireNotNull(className) { "File - Class name must be set" }
        var spec = FileSpec
            .builder(className)
            .addTypes(types)
            .indent("    ")
            .addFunctions(functions)

        for (import in imports) {
            spec = spec.addImport(import.first, import.second)
        }

        return spec.build()
    }
}