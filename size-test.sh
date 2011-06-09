#!/bin/bash

# Count the LOC for each optimization.

#comp="./ecc -i -q"
#comp="./ecc -o1 -i -q"
#comp="./ecc -o2 -i -q"
compnone="./ecc -q"
comp1="./ecc -o1 -q"
comp2="./ecc -o2 -q"
compboth="./ecc -o1 -o2 -q"

ulimit -s unlimited

function LOC_test() {
   dir=$1
   echo -e "\n${dir}: "
   FILES=`ls benchmarks/${dir}/${dir}.ev`
   for ev in $FILES; do
         s=`echo $ev | sed 's/\.ev/\.s/'`

         $compnone $ev 
         echo -n "ecc :"
         wc -l < $s
         echo -n

         $comp1 $ev    
         echo -n "ecc -o1 :"
         wc -l < $s
         echo -n

         $comp2 $ev    
         echo -n "ecc -o2 :"
         wc -l < $s
         echo -n

         $compboth $ev    
         echo -n "ecc -o1 -o2:"
         wc -l < $s
         echo -n
   done

   exit  
}

# Either ask the user, or pass the dir name from stdin on.
if [ "$1" != "-q" ]; then
   echo " --- Select a test case to run."

   select dir in `ls benchmarks`; do
      LOC_test $dir
   done
else
   read d
   LOC_test $d
fi

