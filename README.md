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

    closurecompiler.compilation_level

The default level is SIMPLE_OPTIMIZATIONS

See the [Google Closure Compiler Docs] (https://developers.google.com/closure/compiler/docs/compilation_levels) for more information.
