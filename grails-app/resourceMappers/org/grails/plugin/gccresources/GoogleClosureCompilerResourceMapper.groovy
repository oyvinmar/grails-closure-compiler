package org.grails.plugin.gccresources

import org.grails.plugin.resource.mapper.MapperPhase
import com.google.javascript.jscomp.Compiler
import com.google.javascript.jscomp.CompilerOptions
import com.google.javascript.jscomp.JSSourceFile
import com.google.javascript.jscomp.CompilationLevel
import com.google.javascript.jscomp.Result
import org.grails.plugin.resource.JavaScriptBundleResourceMeta

class GoogleClosureCompilerResourceMapper {
    def phase = MapperPhase.COMPRESSION

    static defaultExcludes = ['**/*.min.js']
    static defaultIncludes = ['**/*.js']

    def map(resource, config) {
        if (resource instanceof JavaScriptBundleResourceMeta) {
            return false
        }

        JSSourceFile input = JSSourceFile.fromFile(resource.processedFile)
        JSSourceFile extern = JSSourceFile.fromCode("/dev/null", "")

        CompilerOptions options = new CompilerOptions();
        CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);

        try {
            Compiler compiler = new Compiler();
            Result result = compiler.compile(extern,input,options);
            proccessResult(result)
            resource.processedFile.write(compiler.toSource())
        } catch (e) {
            log.error "Exception when compiling ${resource.processedFile.name} with Google Closure: $e.message"
        }
    }

   def proccessResult(Result result) {
       //Log and return false on errors
       if (result.errors) {
           result.errors.each {log.error(it.toString())}
           return false
       }

       //Log warnings
       if (result.warnings) {
           result.warnings.each {log.warn(it.toString())}
       }
    }
}
