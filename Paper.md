# CSC 431 Paper

Nathaniel Welch
Ben Sweedler

June 8th, 2011

## Requirements

Each group will submit a paper detailing the design and implementation of their compiler project.

This paper must

 * Outline the overall architecture of the solution.
 * Describe the representation of key data (e.g., control flow graphs and instructions).
 * Outline optimizations implemented.
 * Provide a section detailing the performance of the code generated for the benchmarks. This section should contain graphs comparing the run-times of the generated code (with and without optimizations) and the C equivalent code compiled using gcc (with and without optimizations).

## Architecture Overview

Our approach to the Evil Compiler followed an object oriented approach. Our two goals in our design were to make work on the system easily parallelizable, and to make testing automated. 

To allow for us to easily split the architecture up, we separated our code up in to lots of little files. We started off with Dr. Keen's basic layout, with Evil.java creating the user interface to our compiler and using Antlr to generate the back-end. Evil.g was given to us to generate our parser. We wrote TypeCheck.g and CFG.g to type check the input and then build a CFG from the input respectively. Those two files sadly cause a few merge conflicts, because we were just constantly trying to get things to work.

CFG.g basically builds our Node tree. It first creates instances of SymbolTable and GraphTable. Into the SymbolTable it inserts references to all of the variables available at the time. Into the GraphTable it inserts the entry nodes for functions.
