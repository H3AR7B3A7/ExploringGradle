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

#### Java

##### Groovy

plugins { id 'java' }

##### Kotlin
plugins { java }

### Unknown Plugins

#### Flyway

##### Groovy

plugins { id 'org.flywaydb.flyway' version "6.3.2" }

##### Kotlin

plugins { id("org.flywaydb.flyway") version "6.3.2" }

---