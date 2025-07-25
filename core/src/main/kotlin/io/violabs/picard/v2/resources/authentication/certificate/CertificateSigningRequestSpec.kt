package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class CertificateSigningRequestSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * request contains an x509 certificate signing request encoded
     * in a "CERTIFICATE REQUEST" PEM block. When serialized as JSON
     * or YAML, the data is additionally base64-encoded.
     */
    val request: List<Byte>? = null,
    /**
     * signerName indicates the requested signer, and is a qualified name.
     *
     * List/watch requests for CertificateSigningRequests can filter on this
     * field using a "spec.signerName=NAME" fieldSelector.
     *
     * Well-known Kubernetes signers are:
     *
     * 1. "kubernetes.io/kube-apiserver-client": issues client certificates that
     *    can be used to authenticate to kube-apiserver. Requests for this signer are
     *    never auto-approved by kube-controller-manager, can be issued by the
     *    "csrsigning" controller in kube-controller-manager.
     * 2. "kubernetes.io/kube-apiserver-client-kubelet": issues client certificates
     *    that kubelets use to authenticate to kube-apiserver. Requests for this signer can
     *    be auto-approved by the "csrapproving" controller in kube-controller-manager, and
     *    can be issued by the "csrsigning" controller in kube-controller-manager.
     * 3. "kubernetes.io/kubelet-serving" issues serving certificates that kubelets use to
     *    serve TLS endpoints, which kube-apiserver can connect to securely. Requests for
     *    this signer are never auto-approved by kube-controller-manager, and can be issued
     *    by the "csrsigning" controller in kube-controller-manager.
     *
     * More details are available at
     * https://k8s.io/docs/reference/access-authn-authz/certificate-signing-requests/#kubernetes-signers
     *
     * Custom signerNames can also be specified. The signer defines:
     *
     * 1. Trust distribution: how trust (CA bundles) are distributed.
     * 2. Permitted subjects: and behavior when a disallowed subject is requested.
     * 3. Required, permitted, or forbidden x509 extensions in the request (including
     *    whether subjectAltNames are allowed, which types, restrictions on allowed values)
     *    and behavior when a disallowed extension is requested.
     * 4. Required, permitted, or forbidden key usages / extended key usages.
     * 5. Expiration/certificate lifetime: whether it is fixed by the signer, configurable by the admin.
     * 6. Whether or not requests for CA certificates are allowed.
     */
    val signerName: String? = null,
    /**
     * expirationSeconds is the requested duration of validity of the issued certificate.
     * The certificate signer may issue a certificate with a different validity duration
     * so a client must check the delta between the notBefore and and notAfter fields in
     * the issued certificate to determine the actual duration.
     *
     * The v1.22+ in-tree implementations of the well-known Kubernetes signers will honor
     * this field as long as the requested duration is not greater than the maximum
     * duration they will honor per the --cluster-signing-duration CLI flag to the
     * Kubernetes controller manager.
     *
     * Certificate signers may not honor this field for various reasons:
     *
     * 1. Old signer that is unaware of the field (such as the in-tree implementations prior to v1.22)
     * 2. Signer whose configured maximum is shorter than the requested duration
     * 3. Signer whose configured minimum is longer than the requested duration
     *
     * The minimum valid value for expirationSeconds is 600, i.e. 10 minutes.
     */
    val expirationSeconds: Int? = null,
    /**
     * extra contains extra attributes of the user that created the CertificateSigningRequest.
     * Populated by the API server on creation and immutable.
     */
    val extra: Map<String, List<String>>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * groups contains group membership of the user that created the CertificateSigningRequest.
     * Populated by the API server on creation and immutable.
     */
    val groups: List<String>? = null,
    /**
     * uid contains the uid of the user that created the CertificateSigningRequest.
     * Populated by the API server on creation and immutable.
     */
    val uid: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * usages specifies a set of key usages requested in the issued certificate.
     *
     * Requests for TLS client certificates typically request: "digital signature", "key encipherment", "client auth".
     *
     * Requests for TLS serving certificates typically request: "key encipherment", "digital signature", "server auth".
     *
     * Valid values are:
     *      "signing",
     *      "digital signature",
     *      "content commitment",
     *      "key encipherment",
     *      "key agreement",
     *      "data encipherment",
     *      "cert sign",
     *      "crl sign",
     *      "encipher only",
     *      "decipher only",
     *      "any",
     *      "server auth",
     *      "client auth",
     *      "code signing",
     *      "email protection",
     *      "s/mime",
     *      "ipsec end system",
     *      "ipsec tunnel",
     *      "ipsec user",
     *      "timestamping",
     *      "ocsp signing",
     *      "microsoft sgc",
     *      "netscape sgc"
     */
    val usages: List<UsageType>? = null,
    /**
     * username contains the name of the user that created the CertificateSigningRequest.
     * Populated by the API server on creation and immutable.
     */
    val username: String? = null
)

