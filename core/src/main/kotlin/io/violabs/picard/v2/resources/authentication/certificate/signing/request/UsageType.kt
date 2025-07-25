package io.violabs.picard.v2.resources.authentication.certificate.signing.request

enum class UsageType(val reference: String) {
    Signing("signing"),
    DigitalSignature("digital signature"),
    ContentCommitment("content commitment"),
    KeyEncipherment("key encipherment"),
    KeyAgreement("key agreement"),
    DataEncipherment("data encipherment"),
    CertSign("cert sign"),
    CrlSign("crl sign"),
    EncipherOnly("encipher only"),
    DecipherOnly("decipher only"),
    Any("any"),
    ServerAuth("server auth"),
    ClientAuth("client auth"),
    CodeSigning("code signing"),
    EmailProtection("email protection"),
    SMime("s/mime"),
    IpsecEndSystem("ipsec end system"),
    IpsecTunnel("ipsec tunnel"),
    IpsecUser("ipsec user"),
    Timestamping("timestamping"),
    OcspSigning("ocsp signing"),
    MicrosoftSgc("microsoft sgc"),
    NetscapeSgc("netscape sgc");

    override fun toString(): String {
        return reference
    }
}