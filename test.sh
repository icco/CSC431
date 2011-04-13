#!/usr/bin/env bash

# This is a test script for milestone 1.

for file in $(ls tests/*.ev); do
   java Evil $file
   if [ $? -eq 0 ]; then
      echo "."
   else
      echo "F"
   fi
done
