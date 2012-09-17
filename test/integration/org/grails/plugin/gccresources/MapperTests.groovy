package org.grails.plugin.gccresources

import grails.test.GrailsUnitTestCase
import org.grails.plugin.resource.ResourceMeta

class MapperTests extends GrailsUnitTestCase {

    void testJSMinifying() {
        def r = new ResourceMeta()
        r.processedFile =  new File(System.getProperty("user.dir") + '/web-app/js/jquery-1.8.1.js')
        def before = r.processedFile.size()
        GoogleClosureCompilerResourceMapper.newInstance().with {
            map(r, new ConfigObject())
        }
        assertTrue(r.processedFile.size() < before)
    }
}
