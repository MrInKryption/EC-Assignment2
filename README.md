# Evolutionary Computation: Assignment 2

### Group Members

Brett Shelley - a1645904 - brett.shelley@student.adelaide.edu.au  
David Harley - a1688375 - david.harley@student.adelaide.edu.au  
Jayden Boskell - a1705111 - jayden.boskell@student.adelaide.edu.au  
Raymond Habis - a1631834 - raymond.habis@student.adelaide.edu.au  
Matthew Durflinger - a1669837 - matthew.durflinger@student.adelaide.edu.au  
Patrick Ellway - a1717790 - patrick.ellway@student.adelaide.edu.au


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


