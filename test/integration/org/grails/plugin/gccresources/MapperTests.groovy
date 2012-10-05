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
            map(r, new ConfigObject())
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
        def conf = "closurecompiler.compilation_level = '${level}'"
        mockConfig(conf)

        GoogleClosureCompilerResourceMapper.newInstance().with {
            map(resourceMeta, new ConfigObject())
        }

        def size = resourceMeta.processedFile.size()
        resourceMeta.processedFile.delete()
        return size
    }
}
