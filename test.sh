#!/bin/bash

c=`ls -l benchmarks/ | wc -l`
c=`expr $c - 1` # fixes off by one error.
failed=""
passed=0

echo "-- Running all Benchmarks..."
for i in `seq 1 $c`; do
   echo $i | ./build.sh -q

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

echo ""
echo "-- Passed $passed tests."
if [ "$failed" != "" ]; then
   echo "-- Failed these tests: ${failed}"
fi
