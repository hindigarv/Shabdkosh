# HindiGarv शब्दकोश

## How to use

### Install dependency (gradle)
```kotlin
implementation("io.github.hindigarv:shabdkosh:1.0.3")
```

### Use WordFinder to find foreign words from a String
```kotlin
val wordFinder = WordFinder()
val text = "बाबा बैद्यनाथ की धरती पर आज विकास के इतने सारे काम हो रहे हैं, लेकिन मेरा आपसे एक सवाल भी है…"
val words = wordFinder.find(text)
words.forEach { println("${it.shabd} (${it.mool}) -> ${it.paryays.joinToString(", ") }}") }
// console:
// बाबा (अरबी) -> पितामह, साधु
// लेकिन (अरबी) -> परन्तु, किन्तु
// सवाल (अरबी) -> प्रश्न
```

`WordFinder` uses [शब्दकोश](https://docs.google.com/spreadsheets/d/e/2PACX-1vTnYyZxqwSjM3IPG9TchbZcAUDNM_Y4zbZCFjimzQKVjQpNNinNRj4CeWzXaHDNcDEJ_EPOrtBLycRD/pub?gid=0&single=true&output=tsv) file as source.

Use `autoRefresh` option like this `val wordFinder = WordFinder(autoRefresh = true)` to automatically load new words from the शब्दकोश file.

[How to build java library from gradle](https://docs.gradle.org/7.4.2/samples/sample_building_java_libraries.html)

## Local env setup

create a file `./lib/gradle.properties` with following content
```properties
# ossrh JIRA creds used at https://issues.sonatype.org/secure/Dashboard.jspa
ossrh_username=xxx
ossrh_password=xxx
signing.keyId=xxx
signing.password=xxx
signing.secretKeyRingFile=/Users/xxx/secring.gpg
```

run `./gradlew clean build`

## Release new version

1. change version in `build.gradle.kts`
2. Publish to maven local and verify
    `./gradlew publishToMavenLocal`
3. Publish to maven central
    `./gradlew publish`
4. Go to <https://s01.oss.sonatype.org/#stagingRepositories>
   1. close and release the new version
   2. Verify in maven repos:
       - <https://repo1.maven.org/maven2/io/github/hindigarv/shabdkosh/>
       - <https://mvnrepository.com/artifact/io.github.hindigarv/shabdkosh>
5. Create a new release on github
6. Update the readme file example code with new version 

## Versions
### Next Release
- TBA
### 1.0.3
- Minor: update regex to split using any non devanagari character
- Minor: Regex is enabled by default
### 1.0.2
- Minor: Tokenize text using non-word symbols: `+`, `^`, `<`, `>`, `|`, `&`, `=`, numbers and roman letters
### 1.0.1
- Minor: Regex allows optional OptionSet. e.g. `x(a)?x` -> `"xx", "xax"`
### 1.0.0
- Major: Enable Regex to generate ShabdaRoop list
- Minor: Handle three dots character `…` to split the word.
### 0.2.0
- Added a feature to auto refresh the dictionary every 5 minutes.
- New dependency added: api("io.github.microutils:kotlin-logging-jvm:2.0.11")
- Removed dependency: implementation("com.google.guava:guava:30.1.1-jre")
### 0.1.1
- Added WordFinder which loads dictionary on constructor.
### 0.1.0
- First version without any feature.
