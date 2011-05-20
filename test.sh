#!/bin/bash

c=`ls -l benchmarks/ | wc -l`

echo "-- Running all Benchmarks..."
for i in `seq 1 $c`; do
   echo $i | ./build.sh
done
