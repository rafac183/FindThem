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
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {

                val MAPBOX_USERNAME: String by lazy { System.getenv("MAPBOX_USERNAME") ?: "default_value" }
                username = MAPBOX_USERNAME

                val MAPBOX_DOWNLOADS_TOKEN: String by lazy { System.getenv("MAPBOX_DOWNLOADS_TOKEN") ?: "default_value" }
                password = MAPBOX_DOWNLOADS_TOKEN


            }
        }
    }
}

rootProject.name = "FindThem"
include(":app")
 