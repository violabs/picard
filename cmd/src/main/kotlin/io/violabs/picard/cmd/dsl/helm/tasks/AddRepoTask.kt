package io.violabs.picard.cmd.dsl.helm.tasks

class AddRepoTask : HelmTask() {
    var repoName: String? = null
    var repoUrl: String? = null

    override fun cmd(): Array<String> {
        val repoName = requireNotNull(repoName) { "repo name must be provided" }
        val repoUrl = requireNotNull(repoUrl) { "repo url must be provided" }

        return arrayOf(
            "helm", "repo", "add", repoName, repoUrl
        )
    }
}