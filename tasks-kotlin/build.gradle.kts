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