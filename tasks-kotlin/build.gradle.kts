tasks.register("hello") {
    doFirst {
        print("Hello")
    }
    
    doLast {
        prinln(" - World")
    }
}