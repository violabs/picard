
There are classes that extend various interfaces like `ListResource`. I want 
to copy the setup over to v2/resources and use `GeneratedDsl` on it to auto generate
the DSL builder for it.

You then also have to move the tests to the appropriate location and update the test content.
You may need to go into each class that extends `Resource` and modify the `GeneratedDsl` to 
set `withListGroup = true`.

Finally, add deprecation similar to `@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestV2"))`