#include <stdio.h>
int global1;
int global2;

int sum(int EV_a,int EV_b) { return (EV_a+EV_b); }
int main() { 
   global1 = 10000;
   global2 = 2;

   if ( 1 < 2 ) {
      printf("%d\n",sum(global1, global2)); 
   } else {
      printf("%d\n",sum(2, 2)); 
   }
   
   return 0; 
}
