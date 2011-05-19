#!/bin/bash

c=`ls -l benchamarks/ | wc -l`

echo "-- Running all Benchmarks..."
for i in `seq 1 $c`; do
   echo $i | ./build.sh
done
