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
      diff -wb output.ev output >& /dev/null
      echo " -- returns: $?"
   done

   exit 0
done
