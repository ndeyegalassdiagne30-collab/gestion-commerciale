#!/bin/bash

DRIVER="mysql-connector-j-9.1.0.jar"

find src -name "*.class" -delete
rm -rf bin
mkdir bin

find src -name "*.java" > sources.txt
javac -cp "$DRIVER" -d bin @sources.txt

java -cp bin:"$DRIVER" Main