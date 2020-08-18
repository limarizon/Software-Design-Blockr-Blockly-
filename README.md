# Blockr_group24

##### To run this application: certain libraries are required:

	- pipelinr-0.5.jar: https://mvnrepository.com/artifact/an.awesome/pipelinr
	- opentest4j-1.2.0.jar
	- apiguardian-api-1.1.0.jar
	- junit
	- Java 11.0.1

#### The mentioned underneath are just some small/minor details which can be outdated. Please, if you want to understand our design, check out all the documentation given in that subfolder. UML, Sequence diagrams, as well as a document regarding our design choices and a guide on how to read the documentation.

#### First Introduction to the application:

- Read the assignments to get a clearer understanding of what is required for this assignment.

- Ui sends commands to the mediator(is an instance of the pipelinr library), which in turn corresponds each command with their designated handlers in the domain. Handler will return the command when finished, the state will have been updated. Ui will then be updated by redrawing every element in the Ui using ViewContext.repaint(). 

## domain

- **Level** is a class used to define different gameworld variations. *Arguments: GameWorld and maxBlocks*(=maximum amount of allowed blocks in the solution).

- **Palette** contains each type of block of which the user can choose from to add to its program in the ProgramArea.

- **State** is a class which is defined as the game in general. A state can have multiple levels as well as contains the gameworld and its blockprogram for each of those levels.

  For now we also store the mouseposition in the UI into the state, in that manner we can track what is and isn't clicked in the UI. This can be found at the bottom of the class.

#### domain.block

- **BlockCreator** is class that we introduced to the domain to create a class that follows the creator design pattern. It's used to create instances of the blocktypes into the program Area. 

- **BlockProgram** is the class used to be able to manipulate the created program, consisting out of blocks, in the program area. *ProcessInsertBlock()* is responsible to link the correct blocks together (as of now can only connect onto each block and not have any form of insertion between already connected blocks).

  2 datastructures to keep track of all the blocks:

  - blocks: is a hashset containing all blocks in the ProgramArea, to not allow duplicates (defensive programming).
  - components: holds the first block of each blockprogram in the Program Area (at execution only the longest blockprogram will be executed).

  The program will be executed by calling *blockprogram.executeNext()*. This will call the first component and will execute this block. As of now, <u>each block is executed independently</u> and holds a <u>*next* and *previous*</u> referring to the blocks connected to this block.

#### domain.gameworld

- **GameWorld** (com.blockr.domain.gameworld) is the world in which the robot can move. It also contains the goalTile. It stashes the robot's position as well as its orientation.

  <u>Its UI</u> components can be found in *ui.components.gameworld.GameWorldComponent*

- **Orientation** is a class used to define the direction to which the robot will look to and provide offsets for making the robot move  forward in the correct direction. Contains ENUM: NORTH, EAST, SOUTH, WEST each having their own Offset objects which contains the X and Y-offsets needed to make move forward run perfectly in the correct direction. *moveForward() can be found in GameWorld.moveForward().*
- **Position** is a class needed to define positions in the gameworld. Has *translate()* which takes *Orientation* as an argument and an *equals()* function to detect equal positions.
- **TileType** is just an *ENUM* used in the logic to detect passable and impassable tiles. *ENUM: Free, Blocked*





