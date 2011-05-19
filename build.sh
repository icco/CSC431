#!/bin/bash

ECC="gcc -mcpu=v9"
comp="./ecc"

echo " --- Select a test case to run."
select dir in `ls benchmarks`; do
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
      $comp $ev

      if [[ `hostname` = sparc* ]]; then
         s=`echo $ev | sed 's/\.ev/\.s/'`
         $ECC $s
         ./a.out < benchmarks/$dir/input > benchmarks/$dir/output.ev
         echo " -- returns: $?"
         diff -qwbu benchmarks/$dir/output.ev benchmarks/$dir/output
      else
         echo "ssh to sparc03 to actually compile your .s file."
      fi
   done

   exit 0
done
