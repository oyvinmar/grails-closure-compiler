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
}
