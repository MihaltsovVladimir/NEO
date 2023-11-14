pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NEO"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:datastore")
include(":core:database")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":feature:mainQueue")
