# Google Closure Compiler plugin for Grails

## Description

This plugin compiles/optimizes your javascript resources with the [Google Closure Compiler](https://developers.google.com/closure/compiler/).

It provides three compilation levels. WHITESPACE_ONLY, SIMPLE_OPTIMIZATIONS and ADVANCED_OPTIMIZATIONS.

N.B. It builds/depends on the [Grails Resources Plugin](http://www.grails.org/plugin/resources).

## Installation

    grails install-plugin closure-compiler

## Usage

If you use the Grails Resources Plugin correctly the plugin will detect your js files and compile them automatically.

Files ending with .min.js is excluded.

## Configuration

The Google Closure compiler have three different compilation levels:

* WHITESPACE_ONLY
* SIMPLE_OPTIMIZATIONS
* ADVANCED_OPTIMIZATIONS

These can be configured with the variable:

    grails.resources.mappers.googleclosurecompiler.compilation_level

The default level is SIMPLE_OPTIMIZATIONS
See the [Google Closure Compiler Docs] (https://developers.google.com/closure/compiler/docs/compilation_levels) for more information.

You can also add custom compiler configuration by adding them to the configuration:

    grails.resources.mappers.googleclosurecompiler.compilation_level = 'SIMPLE_OPTIMIZATIONS'
    grails.resources.mappers.googleclosurecompiler.compilerOptions = [
        languageIn: CompilerOptions.LanguageMode.ECMASCRIPT5
    ]

If you want specify compiler options for a single file, it can be done using the attrs map in the ApplicationResource file:

    resource url: 'js/ember.js', attrs: [googlecompiler: [languageIn: CompilerOptions.LanguageMode.ECMASCRIPT5]]

Disable the plugin for certain enviroments: 

    environments {
        development {
            grails.resources.mappers.googleclosurecompiler.disable = true
        }
    }
## 

