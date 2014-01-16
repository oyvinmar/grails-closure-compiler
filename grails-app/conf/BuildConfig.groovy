grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits("global")

    log "warn"
    repositories {
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        compile 'com.google.javascript:closure-compiler:r1918'
    }

    plugins {
        compile(":resources:1.2.1")
        build(":release:3.0.1",
                ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}
