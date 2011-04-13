#!/usr/bin/env bash

# This is a test script for milestone 1.

for file in $(ls tests/*.ev); do
   echo "--- Running test $file"
   java Evil $file
done
