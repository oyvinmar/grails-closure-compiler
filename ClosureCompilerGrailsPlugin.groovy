import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ClosureCompilerGrailsPlugin {
    // the plugin version
    def version = "0.4"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3 > *"
    // the other plugins this plugin depends on
    def dependsOn = [resources:'1.0 > *']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Google Closure Compiler Plugin" // Headline display name of the plugin
    def author = "Øyvind Marthinsen"
    def authorEmail = "oyvinmar@gmail.com"
    def description = '''\
This plugin compiles/optimizes your javascript resources with the Google Closure Compiler.

It provides three compilation levels. WHITESPACE_ONLY, SIMPLE_OPTIMIZATIONS and ADVANCED_OPTIMIZATIONS.

N.B. It builds/depends on the Grails Resources Plugin.
    '''

    // URL to the plugin's documentation
    def documentation = "https://github.com/oyvinmar/grails-closure-compiler/blob/master/README.md"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    //    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
    //    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Github", url: "https://github.com/oyvinmar/grails-closure-compiler/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/oyvinmar/grails-closure-compiler" ]

    def doWithSpring = {
        def config = ConfigurationHolder.getConfig()
        if (!config.closurecompiler.compilation_level) {
            config.closurecompiler.compilation_level= 'SIMPLE_OPTIMIZATIONS'
        }
    }
}
