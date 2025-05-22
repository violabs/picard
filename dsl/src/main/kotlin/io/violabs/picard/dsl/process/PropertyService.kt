package io.violabs.picard.dsl.process

import io.violabs.picard.dsl.config.DomainConfig
import io.violabs.picard.dsl.props.DslProp

interface PropertyService<FACTORY_ADAPTER : ParameterFactoryAdapter, PROP_ADAPTER : PropertyAdapter> {
    fun getParamsFromDomain(domainConfig: DomainConfig): List<DslProp>
}

class DefaultPropertyService(
    private val parameterFactory: DefaultPropertyFactory = DefaultPropertyFactory()
) : PropertyService<DefaultParameterFactoryAdapter, DefaultPropertyAdapter> {
    override fun getParamsFromDomain(domainConfig: DomainConfig): List<DslProp> {
        val domain = domainConfig.domain
        val lastIndex = domain.getAllProperties().count() - 1

        return domain
            .getAllProperties()
            .mapIndexed { i, prop ->
                DefaultPropertyAdapter(
                    i, lastIndex,
                    prop,
                    domainConfig.singleEntryTransformByClassName
                )
            }
            .map(parameterFactory::createPropertyFactoryAdapter)
            .map(parameterFactory::determineProperty)
            .toList()
    }
}