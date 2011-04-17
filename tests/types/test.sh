#!/usr/bin/env bash

# This is a test script for milestone 1.
ERROR=""
for file in $(ls ./*.ev); do
   ERROR1=$( { java Evil $file; } 2>&1 )
   if [ $? -eq 0 ]; then
      echo -n "."
   else
      echo -n "F"
      ERROR="$ERROR\n$ERROR1"
   fi
done

echo -e $ERROR
