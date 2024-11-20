## Intro
The task is to implement a very simple Github repository viewer according to the given requirements.

### Requirements
Build an Android app written in Kotlin that will use GraphQL Github API to download and show list of repos for an arbitrary user/organization.  
For simplicity:
- user/organization can be hardcoded (for example to `toptal`)
- pagination is not a necessary feature for this application

The items on the list should display:
- title of the repository
- url of the repository

Tapping a list item should navigate to another screen with the details of the tapped item:
- title of the repository
- counts of open issues, closed issues, open PRs and closed PRs
- titles of the open issues and PRs

### Hints
- `./gradlew createModuleGraph` will generate a Mermaid graph with modules dependencies. This can help answer questions like which modules get recompiled
when a specific layer implementation is changed, or whether external libraries are isolated from the business logic.
- `buildSrc` plugins will help you add new modules quickly
- Please add a `github.token=` to `local.properties` to use an API
