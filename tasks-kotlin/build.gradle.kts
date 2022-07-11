plugins {
    java
    id("org.flywaydb.flyway") version "6.3.2"
}

tasks.register("hello") {
    doFirst {
        print("Hello")
    }
    
    doLast {
        print(" - World")
    }
}

tasks.register("welcome") {
    
    dependsOn("hello")
    
    doFirst {
        println("Welcome !")
    }
}