plugins {
    application
    kotlin("jvm") version "1.6.21"
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src")
    }
    sourceSets["test"].apply {
        kotlin.srcDir("test/src")
    }
}

application {
    mainClass.set("be.dog.d.steven.RepositoryKt")
}

// To let gradle know where to find Kotlin's JDK 8 stdlib
repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8")) // Kotlin's JDK 8 stdlib
    implementation(files ("lib/log4j-1.2.8.jar", "lib/junit-3.8.1.jar", "lib/jaxb-api-2.3.1.jar"))
}

// Overwrite kotlin compilation tasks to enforce version
tasks {
    compileKotlin {
        kotlinOptions.apply {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions.apply {
            jvmTarget = "1.8"
        }
    }
}