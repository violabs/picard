package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.UNIT

@PicardDSLMarker
internal class KPLambdaTypeNameBuilder : DefaultParamSpecEnabled() {
    var receiver: TypeName? = null
    private var parameters: MutableList<ParameterSpec> = mutableListOf()
    var returnType: TypeName = UNIT

    fun build(): TypeName {
        return LambdaTypeName.Companion.get(
            receiver = receiver,
            parameters = parameters,
            returnType = returnType
        )
    }
}