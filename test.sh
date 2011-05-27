#!/bin/bash

failed=""
passed=0

echo "-- Running all Benchmarks..."
for i in `ls benchmarks/`; do
   echo $i | ./build.sh -q &> /dev/null

   if [ $? = 0 ]; then
      passed=`expr $passed + 1`
   else
      if [ "$failed" != "" ]; then
         failed="${failed}, ${i}"
      else
         failed="$i"
      fi
   fi
done

echo -e "\n-- Passed $passed tests."

if [ "$failed" != "" ]; then
   echo "-- Failed these tests: ${failed}"
fi
