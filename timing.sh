#!/bin/tcsh
# note: I have ecc aliased to gcc -mcpu=v9
unlimit
echo "*********** TIMING **********"
foreach dir (*)
   if (${dir} == "timingscript") continue
   printf "%s " ${dir}
   cd ${dir}
      setenv FILES `ls *.s | grep -v .print.s`
      ecc ${FILES}
      /usr/bin/time -p a.out < input |& grep real | awk '{ print $2 }'
   cd ..
end

