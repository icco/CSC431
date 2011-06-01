#!/bin/bash

failed=""
passed=0
to_time=""

echo "-- Running all Benchmarks..."
for i in `ls benchmarks/`; do
   echo $i | ./build.sh -q #&> /dev/null

   if [ $? = 0 ]; then
      passed=`expr $passed + 1`
      if [ -n $to_test ]; then
         to_test="${to_test}, ${i}"
      else
         to_test="$i"
      fi
   else
      if [ -n $failed ]; then
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

for t in $failed; do
   echo $i | ./timing.sh -q
done
