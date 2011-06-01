#!/bin/bash

ECC="gcc -g -mcpu=v9"
comp="./ecc -i"

ulimit -s unlimited

# Actually does our work.
function build() {
   passed=1
   dir=$1
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
      $comp $ev # compiles the .s

      if [[ `hostname` = sparc* ]]; then
         s=`echo $ev | sed 's/\.ev/\.s/'`
         $ECC $s

         # I wish we had timeout....
         ./a.out < benchmarks/$dir/input > benchmarks/$dir/output.ev

         echo " -- returns: $?"
         diff -wbu benchmarks/$dir/output benchmarks/$dir/output.ev

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
      build $dir
   done
else
   read d
   build $d
fi

