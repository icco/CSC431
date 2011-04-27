#!/bin/bash

$ECC=gcc -mcpu=v9
echo "---- Benchmarks"
for dir in `ls benchmarks`; do
   echo "${dir}: "
   FILES=`ls benchmarks/${dir}/*.ev`
   for ev in $FILES; do
      il=`echo $ev | sed 's/\.ev/\.il/'`
      make $il
      rm $il
   done
done
