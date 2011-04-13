#!/usr/bin/env bash

# This is a test script for milestone 1.
ERROR=""
for file in $(ls tests/*.ev); do
   ERROR="$ERROR\n$( { java Evil $file; } 2>&1 )"
   if [ $? -eq 0 ]; then
      echo -n "."
   else
      echo -n "F"
   fi
done

echo -e $ERROR
