#!/bin/bash

ECC="gcc -mcpu=v9"
comp="./ecc"

echo "---- building..."
select dir in `ls benchmarks`; do
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
      $comp $ev
   done

   exit 0
done
