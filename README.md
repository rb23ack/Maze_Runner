# A* Pathfinding Game

## Overview

The A* Pathfinding Game is a simple Java-based graphical application that demonstrates the A* pathfinding algorithm. Players can set a target on a grid, visualize the shortest path to that target while avoiding obstacles, and understand how the algorithm navigates through a 2D space.

## Features

- **Path Visualization**: Click to set a target, and see the calculated path represented on the grid.
- **Dynamic Obstacles**: Visualize how obstacles affect pathfinding (obstacles are represented as red squares).
- **Customizable**: Easily modify the number of obstacles and other parameters in the code.

## How to Play

1. **Starting the Game**: Run the Java program, and a game window titled "A* Pathfinding Game" will open.

2. **Setting the Target**: Click anywhere in the game window to set a target point. The target is represented by a green square.

3. **Viewing the Path**: After clicking, the game calculates the shortest path from the blue player square (starting position) to the green target square. The path will be displayed as a series of black squares.

4. **Understanding Obstacles**: Red squares represent obstacles that block the path. The algorithm avoids these obstacles while calculating the path.

### Additional Features (Future Enhancements)

- **Player Movement**: Implement a feature that allows the player to move along the path towards the target.
- **Dynamic Obstacles**: Introduce moving obstacles that change positions during gameplay, adding a challenge.
- **Path Animations**: Add animations to visually represent the player moving along the path from the starting position to the target.

## Requirements

- Java Development Kit (JDK) installed on your system (version 8 or higher recommended).
- An IDE (such as IntelliJ IDEA or Eclipse) or a simple text editor to compile and run the Java code.

## Installation

1. Clone or download the repository.
2. Open the project in your preferred IDE or text editor.
3. Compile and run the `AStarPathfindingGame.java` file.

## Usage

Once the game is running, interact with the grid by clicking to set targets and visualizing the A* pathfinding process. Adjust the obstacle count or other parameters in the code to see how it affects the pathfinding.

## License

This project is open-source and available under the MIT License. Feel free to modify and enhance the game as you see fit!

## Acknowledgements

- Inspired by the A* pathfinding algorithm, this game aims to provide a visual understanding of how pathfinding works in grid-based systems.
