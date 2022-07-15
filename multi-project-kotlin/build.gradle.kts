val hamcrest_version: String by project
val commonslogging_version: String by project
val flyway_version: String by project
val inject_version: String by project

buildscript {
    // This variable needs to be in scope for the buildscript to be able to resolve it.
    val h2_version: String by project
    repositories {
        mavenCentral()
    }
    dependencies {
//        classpath("com.h2database:h2:$h2_version")
    }
}

plugins {
    // We have to hardcode the version of the plugin here,
    // because the plugins block is idempotent
    id("org.flywaydb.flyway") version "6.3.1" apply false
    application
}

application {
    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

allprojects {
//    apply(plugin = "java")
//    apply(plugin = "application")
    version= "1.0.SNAPSHOT"
}

subprojects {
    apply(plugin = "application")
//    apply(plugin = "application")
    version= "0.1-SNAPSHOT"
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
//        testImplementation("org.hamcrest:hamcrest-core:$hamcrest_version")
    }
}

// Add configuration to a specific subproject
project(":Repository") {}

project(":JacketService"){
    dependencies {
        "implementation"(project(":Repository"))
//        "testImplementation"("org.hamcrest:hamcrest-core:$hamcrest_version")
    }
}

// Add configuration for a group of projects
listOf("JacketService", "Repository").forEach { name ->
    project(":$name") {
        version= "0.1-SNAPSHOT"
        repositories {
            mavenCentral()
        }
        dependencies {
//            testImplementation("org.hamcrest:hamcrest-core:$hamcrest_version")
        }
        tasks.register("hello") {
            doFirst {
                println("Hello from $name")
            }
        }
    }
}