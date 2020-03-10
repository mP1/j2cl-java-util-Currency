[![Build Status](https://travis-ci.com/mP1/java-util-Currency-j2cl.svg?branch=master)](https://travis-ci.com/mP1/java-util-Currency-j2cl.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/java-util-Currency-j2cl/badge.svg?branch=master)](https://coveralls.io/github/mP1/java-util-Currency-j2cl?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/java-util-Currency-j2cl.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/java-util-Currency-j2cl/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/java-util-Currency-j2cl.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/java-util-Currency-j2cl/alerts/)



# java.util.Locale j2cl

This project aims to provide a more complete `java.util.Currency` sourcing locale sensitive data from a tool that queries the JRE.



### Missing functionality

Many methods are missing, most of the main getters are available.



### Transpiling

The `j2cl-maven-plugin` will repackage the source during the transpile phase, so `walkingkooka.javautilcurrencyj2cl.java.util.Currency`
is available in javascript as `java.util.Currency`. 



### Serialization

Serialization is not supported, and all support classes and forms including magic methods such as `writeReplace` are removed.



## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/java-util-Currency-j2cl.git
```
