allprojects {
//    apply(plugin = "java")
    apply(plugin = "application")
    version= "1.0.SNAPSHOT"
}

project(":Repository") {}

project(":JacketService"){
    dependencies {
        "implementation"(project(":Repository"))
    }
}