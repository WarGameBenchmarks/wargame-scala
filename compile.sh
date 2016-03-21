#!/bin/bash

# scalac -sourcepath src/ -d build/ src/App.scala

scalac -deprecation -sourcepath src/ -d build/ src/*.scala
