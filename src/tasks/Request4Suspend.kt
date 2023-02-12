package tasks

import contributors.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun loadContributorsSuspend(service: GitHubService, req: RequestData): List<User> {
    val repos = service
        .getOrgRepos(req.org)
        .also { logRepos(req, it) }
        .bodyList()

    return repos.flatMap { repo ->
        service.getRepoContributors(req.org, repo.name)
            .also { logUsers(repo, it) }
            .bodyList()
    }.aggregate()
}

suspend fun foo() {
    GlobalScope.launch {
        while (true) {
            delay(2000L)
            println("VLAD: foo")
        }
    }
    while (true) {
        delay(1000L)
        println("VLAD: bar")
    }
}