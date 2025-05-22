package io.violabs.picard.dsl.process

import io.violabs.picard.dsl.config.DomainConfig
import io.violabs.picard.dsl.props.DslPropSchema

interface PropertySchemaService<FACTORY_ADAPTER : PropertySchemaFactoryAdapter, PROP_ADAPTER : DomainProperty> {
    fun getParamsFromDomain(domainConfig: DomainConfig): List<DslPropSchema>
}

class DefaultPropertySchemaService(
    private val propertySchemaFactory: DefaultPropertySchemaFactory = DefaultPropertySchemaFactory()
) : PropertySchemaService<DefaultPropertySchemaFactoryAdapter, DefaultDomainProperty> {
    override fun getParamsFromDomain(domainConfig: DomainConfig): List<DslPropSchema> {
        val domain = domainConfig.domain
        val lastIndex = domain.getAllProperties().count() - 1

        return domain
            .getAllProperties()
            .mapIndexed { i, prop ->
                DefaultDomainProperty(
                    i, lastIndex,
                    prop,
                    domainConfig.singleEntryTransformByClassName
                )
            }
            .map(propertySchemaFactory::createPropertySchemaFactoryAdapter)
            .map(propertySchemaFactory::determinePropertySchema)
            .toList()
    }
}