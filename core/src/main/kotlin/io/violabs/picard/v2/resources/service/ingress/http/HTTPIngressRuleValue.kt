package io.violabs.picard.v2.resources.service.ingress.http

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * HTTPIngressRuleValue is a list of http selectors pointing to backends. In the example: 
 * http://<host>/<path>? -> backend where where parts of the url correspond to RFC 3986, this 
 * resource will be used to match against everything after the last '/' and before the first '?' or '#'.
 */
@GeneratedDsl(withListGroup = true)
data class HTTPIngressRuleValue(
    /**
     * paths is a collection of paths that map requests to backends.
     */
    val paths: List<HTTPIngressPath>
)