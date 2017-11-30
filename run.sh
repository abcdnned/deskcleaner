#!/bin/sh

mvn compile
sudo mvn exec:java -Dexec.mainClass="tom.yang.dc.App" -Dexec.args="-T30 -D/home/tom/Downloads"
sudo mvn exec:java -Dexec.mainClass="tom.yang.dc.App" -Dexec.args="-T30 -D/home/tom/tmp"
