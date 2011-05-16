#!/bin/bash

ECC="gcc -mcpu=v9"
comp="./ecc"

echo "---- Benchmarks"
for dir in `ls benchmarks`; do
   echo "${dir}: "
   FILES=`ls benchmarks/${dir}/*.ev`
   for ev in $FILES; do
      $comp $ev
      s=`echo $ev | sed 's/\.ev/\.s/'`
      $ECC $s
      benchmarks/${dir}/a.out < benchmarks/${dir}/input >&! benchmarks/${dir}/output.ev
      diff -wb output.ev output >&! /dev/null
      echo "$dir : $?"
   done
done
