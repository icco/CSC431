# CSC 431

Programming Languages 2: The Compiling.

This repository is the work of Ben Sweedler and Nat Welch.

## Usage

    usage: java Evil [options] filename.ev
     -?,--help         Print this help message.
     -a,--displayAST   Print out a dotty graph of the AST.
     -d,--debug        Print debug messages while running.
     -i,--dumpIL       Dump ILOC to a file.
     -t,--notype       Don't Typecheck.

## Notes

For Milestone 2:

    make tests/compat.il > myout && curl -s http://users.csc.calpoly.edu/~akeen/courses/csc431/handouts/references/compat.out | diff -u - myout

## TODO

Nat

 * Benchmarks running
 * generating Sparc instructions and ILOC to Sparc relations

Ben

 * Gen / Kill stuff
 * Register building.

## License

Copyright (c) 2011 Ben Sweedler and Nathaniel Welch

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
