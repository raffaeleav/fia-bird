<p align="center">
  <img src="https://github.com/raffaeleav/fia-bird/assets/114619463/2e8ea9ad-739f-4e58-98cb-70539385edf2" width="512" heigth="120">
</p>

<p align="center">
  A remake of the Flappy Bird game with Artificial Intelligence developed as a project for the Fondamenti di Intelligenza Artificiale (Fundamentals of Artificial Intelligence) course, part of the Computer Science Bachelor's Degree program at the University of Salerno
</p>


## Table of Contents
- [Authors](#Authors)
- [About](#About)
- [Preview](#Preview)
- [Features](#Features)
- [How to replicate](How-to-replicate)
- [Dependencies](#Dependencies)
- [Built with](#Built-with)


## Authors
| Name | Github profile |
| ---- | --------- |
| Aviello Raffaele | [raffaeleav](https://github.com/raffaeleav) |
| Menzione Michele | [Michibit](https://github.com/Michibit) |


## About 
  FIA Bird was developed with the goal of experimenting 
how artificial intelligence can be applied to video games. 
The underlying game that this project uses is an implementation that is quite similar to the original game (which this repository has forked).
The purpose of 
the project is to apply the studied local search algorithms to the game's 
framework in order to make the game function automatically. 
The game 
consists of guiding the intelligent agent (represented by a robot sprite) 
through a series of obstacles represented by pipes, with the goal of making 
the agent fly as far as possible.


## Preview
<p>
  <img src="https://github.com/raffaeleav/fia-bird/assets/114619463/14afa6ef-8956-4ce0-b435-8c5011dc7c6a" width="400" heigth="400">
</p>


## Features
1) Hill Climbing algortithm
2) Stochastic Hill Climbing algortithm


## How to replicate
1) Clone the repository
```bash
git clone https://github.com/raffaeleav/fia-bird.git
```
2) Switch to the project directory
```bash
cd fia-bird
```
3) Compile the project
```bash
javac $(find . -name "*.java")
```
4) Run the game with the Hill Climbing algorithm 
```bash
java -cp ./src/main/java iamodule.HillClimbing
```
5) Alternatively, run the game with the Stochastic Hill Climbing algorithm 
```bash
java -cp ./src/main/java iamodule.StochasticHillClimbing
```


## Dependencies
- [JDK 19](https://www.oracle.com/java/technologies/downloads/#java19 "JDK 19")


## Built with 
- [Java](https://www.oracle.com/java/technologies/downloads/#java19 "JDK 19") - used for the implementation of the local search algorithms
- [Piskel](https://www.piskelapp.com/) - used for the sprites
