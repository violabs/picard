package io.violabs.picard.metaDsl

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.violabs.picard.metaDsl.annotation.GeneratedDsl

fun KSClassDeclaration?.isGroupDsl(): Boolean = this?.annotations
    ?.filter { it.shortName.asString() == GeneratedDsl::class.simpleName }
    ?.any { annotation ->
        annotation
            .arguments
            .firstOrNull { it.name?.asString() == GeneratedDsl::withListGroup.name }
            ?.value == true
    }
    ?: false

fun KSClassDeclaration?.mapGroupType(): GeneratedDsl.MapGroupType? = this?.annotations
    ?.filter { it.shortName.asString() == GeneratedDsl::class.simpleName }
    ?.flatMap(KSAnnotation::arguments)
    ?.firstOrNull { it.name?.asString() == GeneratedDsl::withMapGroup.name }
    ?.let { GeneratedDsl.MapGroupType.valueOf(it.value.toString().uppercase()) }