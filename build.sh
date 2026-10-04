#!/bin/bash
MOD_NAME="MC-SnowRealMagic" # replace with mod name
tar -czf - . | ssh gojoe "mkdir -p ~/dev/$MOD_NAME && docker run -i -u 0 --rm -v ~/dev/$MOD_NAME:/work -w /work eclipse-temurin:25-jdk rm -rf src < /dev/null && cd ~/dev/$MOD_NAME && tar -xzf - && sed -i 's/\r$//' gradlew && docker run -i --rm -v ~/.gradle-ez:/root/.gradle -v \"\$PWD\":/work -w /work eclipse-temurin:25-jdk ./gradlew build --no-daemon < /dev/null"
