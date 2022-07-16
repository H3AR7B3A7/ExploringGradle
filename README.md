# Gradle

## Minimal Application

[Minimal - Groovy](minimal-groovy/README.md)

[Minimal - Kotlin](minimal-kotlin/README.md)

## Tasks

[Tasks - Groovy](tasks-groovy/README.md)

[Tasks - Kotlin](tasks-kotlin/README.md)

### Default Tasks

#### Build Setup tasks

init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

#### Help tasks

buildEnvironment - Displays all buildscript dependencies declared in root project 'tasks'.
dependencies - Displays all dependencies declared in root project 'tasks'.
dependencyInsight - Displays the insight into a specific dependency in root project 'tasks'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and
conventions.
outgoingVariants - Displays the outgoing variants of root project 'tasks'.
projects - Displays the sub-projects of root project 'tasks'.
properties - Displays the properties of root project 'tasks'.
tasks - Displays the tasks runnable from root project 'tasks'.

### Execute Phase

- doFirst
- doLast

## Plugins

Plugins extend the project's capabilities.

### Plugins Known by Gradle

Some plugins are included in the installation of Groovy and have a very simple syntax to include.

#### Groovy

plugins { id 'java' }

#### Kotlin
plugins { java }

### Unknown Plugins


#### Groovy

plugins { id 'org.flywaydb.flyway' version "6.3.2" }

#### Kotlin

plugins { id("org.flywaydb.flyway") version "6.3.2" }

## Java Application

Steps
- Create build file
- Add the appropriate plugins
- Override tasks and properties

Java source code
- Is expected to be in standard locations
- We can change this using SourceSets

### Groovy

```groovy dsl
sourceSets {
    main {
        java {
            srcDir 'src/java'
        }
        resources {
            srcDir 'src/resources'
        }
    }
}
```

## Java Application

### Groovy

[Java Application - Groovy](java-application-groovy/README.md)

### Kotlin

[Java Application - Kotlin](java-application-kotlin/README.md)

## Dependency Management

- Projects have dependencies
- They can be satisfied from multiple places
  - Other projects
  - File system
  - Maven repositories
  - Ivy repositories
- For multiple configurations
  - Compilation
  - Runtime
  - Test compilation
  - Test runtime
- And can be cached

The project can depend on
- Other projects
- External libraries
- Internal libraries

*Like maven, gradle will also get transitive dependencies.*

### Listing Dependencies

> gradle -q dependencies

> gradle -q dependencies --configuration implementation

### Adding Dependencies

#### Local File System

In Groovy:

```groovy dsl
dependencies {
    implementation files ('lib/log4j-1.2.8.jar', 'lib/jaxb-api-2.3.1.jar')
    implementation files ('lib/junit-3.8.1.jar')
}
```

Or better:

```groovy dsl
repositories {
    flatDir {
        dirs 'lib'
    }
}

dependencies {
    implementation 'log4j:log4j:1.2.8'
    implementation group: 'javax.xml.bind', name 'jaxb-api' version '2.3.1')
    implementation files 'junit:junit:3.8.1'
}
```

And in kotlin:

```kotlin dsl
dependencies {
    implementation("log4j:log4j:1.2.8")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("junit:junit:3.8.1")
}
```

#### Configuration Scope

- implementation
  - compileOnly
  - runtimeOnly
- testImplementation
  - testCompileOnly
  - testRuntimeOnly

#### Constants

In Groovy:

```groovy dsl
buildscript {
    ext {
        log4j_version = '1.2.8'
        jaxb_version = '2.3.1'
        junit_version = '3.8.1'
    }
}

dependencies {
    implementation "log4j:log4j:$log4j_version"
    implementation group: 'javax.xml.bind', name 'jaxb-api' version "$jaxb_version")
    implementation files "junit:junit:$junit_version"
}
```

In Kotlin:

gradle.properties

```properties
log4j_version=1.2.8
jaxb_version=2.3.1
junit_version=3.8.1
```

```kotlin dsl
val log4j_version: String by project
val jaxb_version: String by project
val junit_version: String by project

dependencies {
    implementation("log4j:log4j:$log4j_version")
    implementation("javax.xml.bind:jaxb-api:$jaxb_version")
    implementation("junit:junit:$junit_version")
}
```

#### Remote Repositories

Maven central:

```groovy dsl
repositories {
    mavenCentral()
}
```

Jfrog bintray - Deprecated:

```groovy dsl

```groovy dsl
repositories {
    jcenter()
}
```

Or by url:

```kotlin dsl
repositories {
    url "http://jcenter.bintray.com"
}
```

#### Company Repositories

```groovy dsl
repositories {
    maven {
        url 'http://repo.mycompany.com/maven2'
    }
    
    ivy {
        url 'http://repo.mycompany.com/ivy/'
    }
}
```

#### Local Maven Repositories (Not Recommended)

```groovy dsl
repositories {
    mavenLocal()
}
```

### Gradle Cache

- Modules are cached:
  - File based
  - Metadata and files stored separately
  - Repository caches are independent
  - In user folder: .gradle/caches
- Hashed to see if it needs downloading
- Dependencies can be refreshed

  > gradle build --refresh-dependencies

## Multi Project Build

- Applications generally consist of multiple projects
- Top level settings file to specify the projects
- Top level build to configure the projects
  - And set dependencies
- Project level build files
  - Set project level properties and tasks

## Testing /w JUnit

- By default, Gradle looks for unit tests in src/test/java
- Output is in build/classes/test
- Reports are in build/reports/tests

- We use the 'testImplementation' scope
  testImplementation("junit:junit:4.12")
- We can configure testing in the test block
  - To see the test results

```groovy dsl
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.3.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.3.1'
}

test {
    useJUnitPlatform()
    testLogging {
        events TestLogEvent.FAILED,
               TestLogEvent.PASSED,
               TestLogEvent.SKIPPED
    }
}
```

### Improved Logging

```groovy dsl
id 'com.adarshr.test-logger' version '2.0.0'

testLogger{
    theme 'standard'
    showExceptions true
    showStackTraces true
    showFullStackTraces true
    showCauses true
    slowTreshold 2000
    showSummary true
    showSimpleNames true
    showPassed true
    showSkipped true
    showFailed true
    showStandardStreams true
    showPassedStandardStreams true
    showSkippedStandardStreams true
    showFailedStandardStreams true
}
```

```kotlin dsl
plugins {
    id ("com.adarshr.test-logger") version "2.0.0"
}

tasks {
    testLogger{
        theme 'standard'
        showExceptions true
        showStackTraces true
        ...
    }
}
```

### Filtering Tests

```groovy dsl
test {
    filter {
        includeTestsMatching 'com.foo.shouldCreateASession'
        includeTestsMatching '*shouldCreateASession'
    }
}

task singleTest {
    group = "Verification"
    description = "Run a single test"
    
    dependsOn testClasses
    
    useJUnitPlatform()
    testLogging {
        events TestLogEvent.FAILED,
               TestLogEvent.PASSED,
               TestLogEvent.SKIPPED
    }
    
    filter {
        includeTestsMatching 'com.foo.shouldCreateASession'
    }
}
```

```kotlin dsl
tasks.register<Test>("singleTest") {
    group = "Verification"
    description = "Run a single test"
    dependsOn("testClasses")
    useJUnitPlatform()
    testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
    filter {
        includeTestsMatching("com.foo.shouldCreateASession")
    }
}
```

## Gradle Wrapper

- Gives the project a specific version of Gradle
- Get consistent builds
- gradlew.bat on Windows
- gradlew shell script on Unix environments

Create wrapper
> gradle wrapper

gradle-wrapper.properties

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-7.4-bin.zip
```

> gradle build --console plain

Reasons for using wrapper:
- IDEs need it when loading the project
- Build servers need it to create a build (Teamcity)
  - Continuous integration is very important
  - If not CI then at least nightly builds
  - If not nightly then a clean place to build
  - 'Works on my machine'

---