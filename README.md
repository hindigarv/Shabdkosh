# HindiGarv शब्दकोश

## How to use

### Install dependency (gradle)
```kotlin
implementation("io.github.hindigarv:shabdkosh:0.1.1")
```

### Use WordFinder to find foreign words from a String
```kotlin
val wordFinder = WordFinder()
val words: List<Word> = wordFinder.find(text) // TODO improve example
```


[शब्दकोश](https://docs.google.com/spreadsheets/d/e/2PACX-1vTnYyZxqwSjM3IPG9TchbZcAUDNM_Y4zbZCFjimzQKVjQpNNinNRj4CeWzXaHDNcDEJ_EPOrtBLycRD/pub?gid=0&single=true&output=tsv) as tsv file

[How to build java library from gradle](https://docs.gradle.org/7.4.2/samples/sample_building_java_libraries.html)

### Publish to maven local
`./gradlew publishToMavenLocal`

