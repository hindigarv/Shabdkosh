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

## Versions
### 0.1.0
- First version without any feature.
### 0.1.1
- Added WordFinder which loads dictionary on constructor.
### 0.2.0
- Added a feature to auto refresh the dictionary every 5 minutes.
- New dependency added: api("io.github.microutils:kotlin-logging-jvm:2.0.11")
- Removed dependency: implementation("com.google.guava:guava:30.1.1-jre")
### nextVersion
- Handle three dots character `…` to split the word. 
