#!/bin/bash

ECC="gcc -mcpu=v9"
comp="./ecc"

echo "---- Benchmarks"
select dir in `ls benchmarks`; do
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
      s=`echo $ev | sed 's/\.ev/\.s/'`
      $ECC $s
      ./a.out < benchmarks/$dir/input > benchmarks/$dir/output.ev
      echo " -- returns: $?"
      diff -qwbu benchmarks/$dir/output.ev benchmarks/$dir/output
   done

   exit 0
done
