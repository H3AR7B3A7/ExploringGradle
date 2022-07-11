tasks.register("hello") {
    doFirst {
        print("Hello")
    }
    
    doLast {
        print(" - World")
    }
}