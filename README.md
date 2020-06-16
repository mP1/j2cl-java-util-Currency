[![Build Status](https://travis-ci.com/mP1/j2cl-java-util-Currency.svg?branch=master)](https://travis-ci.com/mP1/j2cl-java-util-Currency.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-util-Currency/badge.svg?branch=master)](https://coveralls.io/github/mP1/j2cl-java-util-Currency?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-util-Currency.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Currency/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-util-Currency.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Currency/alerts/)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



#  j2cl java.util.Currency

This project aims to provide a more complete `java.util.Currency` sourcing locale sensitive data at build time using
an [annotation processor](https://github.com/mP1/j2cl-java-util-currency-annotation-processor).

- Serialization is not supported, and all support classes and forms including magic methods such as `writeReplace` are removed.



## Annotation processor arguments

- See [j2cl-java-util-currency-annotation-processor](https://github.com/mP1/j2cl-java-util-locale-annotation-processor) for more info about selecting which currencies get bundled.
- See [j2cl-java-util-locale-annotation-processor](https://github.com/mP1/j2cl-java-util-locale-annotation-processor) for more info about selecting which locales get bundled.
- See [j2cl-locale](https://github.com/mP1/j2cl-locale) for more info about logging.


## Missing/Unsupported APIs

`java.util.Currency`

- getDisplayName (all overloads) are not supported. This would require considerable data due to localization.




### Transpiling

The `j2cl-maven-plugin` will shade the source during the transpile phase, so `Currency`
is available in javascript as `java.util.Currency`. 



## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/j2cl-java-util-Currency.git
```



### IDE build setup

Annotation processors from class path must be enabled as some tests test the output of generated classes.

The star/wildcard may need to be escaped with a backslash.

![Intellij -> System Preferences -> Annotation Processors](intellij-enable-annotation-processors.png)