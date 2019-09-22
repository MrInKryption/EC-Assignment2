# Evolutionary Computation: Assignment 2

### Group Members

Brett Shelley - a1645904 - brett.shelley@student.adelaide.edu.au             part 1\
David Harley - a1688375 - david.harley@student.adelaide.edu.au               part 2\
Jayden Boskell - a1705111 - jayden.boskell@student.adelaide.edu.au           part 2\
Raymond Habis - a1631834 - raymond.habis@student.adelaide.edu.au             part 1\
Matthew Durflinger - a1669837 - matthew.durflinger@student.adelaide.edu.au   part 3\
Patrick Ellway - a1717790 - patrick.ellway@student.adelaide.edu.au           part 3\

### Running the code

compile - javac *.java

### Running Part 1

java Main a b

a = 1
b = integer for number of cities

Example : java Main 1 50  - will run part 1 for 50 points

### Running Part 2

java Main a b c 

a = 2

b : 1 = fastNova mutator and Mean crossover
    2 = superNova mutator and Mean crossover
    3 = Offset mutator and schoolyard crossover

c = integer for number of cities

Example : java main 2 2 50 - will run superNova and Mean crossover for instance size of 50 points


### Running Part 3

java Main a b c d e f

a = 3

b: 1 = Inver - TwoOp
   2 = Inver / TwoOp
   3 = Inver - Genetic Algorithm
   4 = Inver / Genetic Algorithm
   5 = TwoOpt - Genetic Algorithm
   6 = TwoOpt / Genetic Algorithm

c: 1 = Mean Crossover
   2 = schoolyard Teams Crossover

d: 1 = SuperNova Mutator
   2 = FastNova Mutator
   3 = Offset Mutator

f: enter a positive integer - this will set the number of generations

Example: java Main 3 3 1 1 1000

This will run Inver-Genetic Algorithm with the Mean Crossover and SuperNova Mutator for 1000 generations.

###ExtremistEA Bonus

java Main a b

a = 4
b = integer number for number of cities

Example : java Main 4 50 - will run extremistEA for intances of 50 points


