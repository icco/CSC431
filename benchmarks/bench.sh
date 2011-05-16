#!/usr/bin/env tcsh
# note: I have ecc aliased to gcc -mcpu=v9
unlimit
foreach dir (*)
   echo "**** ${dir} ****"
   cd ${dir}
#   gcc *.c
#   a.out < input >&! output.c
   gcc -mcpu=v9 *.s
   a.out < input >&! output.ev
   diff -wb output.ev output >&! /dev/null
#   diff -wb output.ev output.c >&! /dev/null
   echo ${status}
   cd ..
end
