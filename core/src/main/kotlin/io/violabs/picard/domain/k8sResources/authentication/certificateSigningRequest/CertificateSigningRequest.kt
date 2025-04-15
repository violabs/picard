package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class CertificateSigningRequest(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<CertificateSigningRequest.Version> {
    interface Version : APIVersion

    data class Spec(
        val request: List<Byte>,
        val signerName: String,
        val expirationSeconds: Int? = null,
        val extra: Map<String, List<String>>? = null,
        val groups: List<String>? = null,
        val uid: String? = null,
        val usages: List<String>? = null,
        val username: String? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var _request: List<Byte>? = null
            var signerName: String? = null
            var expirationSeconds: Int? = null
            private var extra: Map<String, List<String>>? = null
            private var groups: List<String>? = null
            var uid: String? = null
            private var usages: List<String>? = null
            var username: String? = null

            fun request(): List<Byte>? = _request

            fun request(vararg bytes: Byte) {
                _request = bytes.toList()
            }

            fun extra(vararg pairs: Pair<String, List<String>>) {
                extra = pairs.toMap()
            }

            fun groups(vararg groups: String) {
                this.groups = groups.toList()
            }

            fun usages(vararg usages: String) {
                this.usages = usages.toList()
            }

            override fun build(): Spec {
                return Spec(
                    request = vRequireNotEmpty(this::request),
                    signerName = vRequireNotNull(this::signerName),
                    expirationSeconds = expirationSeconds,
                    extra = extra,
                    groups = groups,
                    uid = uid,
                    usages = usages,
                    username = username
                )
            }
        }
    }

    data class Status(
        val certificate: List<Byte>? = null,
        val conditions: List<SigningRequestCondition>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var certificate: List<Byte>? = null
            private var conditions: List<SigningRequestCondition>? = null

            fun certificate(vararg certificate: Byte) {
                this.certificate = certificate.toList()
            }

            fun conditions(scope: ConditionGroup<SigningRequestCondition, SigningRequestCondition.Builder>.() -> Unit) {
                conditions = ConditionGroup(SigningRequestCondition.Builder())
                    .apply(scope)
                    .conditions()
            }

            override fun build(): Status {
                return Status(
                    certificate = certificate,
                    conditions = conditions
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        CertificateSigningRequest,
        Spec, Spec.Builder,
        Status, Status.Builder
    >(Spec.Builder(), Status.Builder()) {

        override fun build(): CertificateSigningRequest {
            return CertificateSigningRequest(
                spec = vRequireNotNull(this::spec),
                metadata = metadata,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CertificateSigningRequest, Builder>(Builder()) {
        fun request(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}