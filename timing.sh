#!/bin/bash

# Tests our output time against gcc's

ECC="gcc -O1 -mcpu=v9"
comp="./ecc -o1 -o2 -i -q"

ulimit -s unlimited

function time_test() {
   passed=1

   dir=$1
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
      if [ ! -a $ev ]; then
         $comp $ev # compiles the .s
      fi

      if [[ `hostname` = sparc* ]]; then
         s=`echo $ev | sed 's/\.ev/\.s/'`
         $ECC $s
         echo -n "EV : "
         /usr/bin/env time  -f "%e" ./a.out < benchmarks/$dir/input > /dev/null

         s=`echo $ev | sed 's/\.ev/\.c/'`
         $ECC $s
         echo -n "C  : "
         /usr/bin/env time -f "%e" ./a.out < benchmarks/$dir/input > /dev/null

         passed=$?
      else
         echo "ssh to sparc03 to actually compile your .s file."
      fi
   done

   exit $passed
}

# Either ask the user, or pass the dir name from stdin on.
if [ "$1" != "-q" ]; then
   echo " --- Select a test case to run."
   select dir in `ls benchmarks`; do
      time_test $dir
   done
else
   read d
   time_test $d
fi
