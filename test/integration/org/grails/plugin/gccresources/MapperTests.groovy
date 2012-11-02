package org.grails.plugin.gccresources

import grails.test.GrailsUnitTestCase
import org.grails.plugin.resource.ResourceMeta

class MapperTests extends GrailsUnitTestCase {

    void testJSMinifying() {
        def r = new ResourceMeta()
        def filePath = System.getProperty("user.dir") + '/web-app/js/jquery-1.8.2.js'
        r.processedFile = new File(filePath + '.copy.js') << new File(filePath).text
        def before = r.processedFile.size()

        GoogleClosureCompilerResourceMapper.newInstance().with {
            grailsApplication = [config : new ConfigSlurper().parse("grails.resources.mappers.googleclosurecompiler.compilation_level = 'SIMPLE_OPTIMIZATIONS'")]
            map(r, grailsApplication.config.grails.resources.mappers.googleclosurecompiler)
        }

        assertTrue(r.processedFile.size() < before)

        r.processedFile.delete()
    }

    void testCompilationLevels() {

        def white = preformCompilationLevelTest('WHITESPACE_ONLY')
        def simple = preformCompilationLevelTest('SIMPLE_OPTIMIZATIONS')
        def advanced = preformCompilationLevelTest('ADVANCED_OPTIMIZATIONS')

        assertTrue(advanced < simple && simple < white)
    }

    private def preformCompilationLevelTest(String level) {
        def resourceMeta = new ResourceMeta()
        def filePath = System.getProperty("user.dir") + '/web-app/js/test.js'
        resourceMeta.processedFile = new File(filePath + '.copy.js') << new File(filePath).text
        def conf = "grails.resources.mappers.googleclosurecompiler.compilation_level = '${level}'"
        def configObject = new ConfigSlurper().parse(conf)

        GoogleClosureCompilerResourceMapper.newInstance().with {
            grailsApplication = [config : configObject]
            map(resourceMeta, grailsApplication.config.grails.resources.mappers.googleclosurecompiler)
        }

        def size = resourceMeta.processedFile.size()
        resourceMeta.processedFile.delete()
        return size
    }
}
