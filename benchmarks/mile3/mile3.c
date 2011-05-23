#include<stdio.h>
int sum(int EV_a,int EV_b) { return (EV_a+EV_b); }
int main() { 
   if ( 1 < 2 ) {
      printf("%d\n",sum(10000, 2)); 
   } else {
      printf("%d\n",sum(2, 2)); 
   }
   
   return 0; 
}
