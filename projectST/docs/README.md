# LDTS_0408 - SEARCHING FOR KEY-TY
Welcome to “Searching for Key-ty”, a game centered around the kidnapping of our protagonist cat. Embark on this amazing journey where Mari has to find her beloved kitten, finding the keys and moving through several rooms in a haunted mansion while trying to survive the monsters in her way.
This project for LDTS in 2022/2023 was developed by Teresa Mascarenhas and Sofia Gonçalves.


## IMPLEMENTED FEATURES
**- Moving right and left**: The character moves to the right when the right arrow key is pressed and to the left when the left arrow key is pressed
![image]

**- Jumping**: The character jumps when the up arrow key is pressed.
![image]

## PLANNED FEATURES
**- Double jumping**: The character can jump twice in a row if it takes a potion.
![image]

**- Menu**: The initial menu that offers options to start or exit the game, as well as access the instructions.
![image]

**- Instruction Menu**: An informational menu that provides details on how to play the game.
![image]

**- Pause Menu**: A menu accessible during the game that allows the players to temporarily pause it and resume the gameplay when they want.
![image]

**- Enemies**: The enemies will have different strategies to chase and try to kill the character.
![image]

**- Character lives**: If the character is touched by an enemy, she loses a life and remains in the game. When all her lives are lost, the game over screen appears and gives the option to restart or return to the menu.
![image]

**- Collecting the key**: If the character collects the key of the level, when she comes near the door it goes to the next level.
![image]

**- Platforms**: Structures where the character can jump on top of.
![image]

## DESIGN PROBLEMS

### What appears on the terminal differs depending on the game state

**Problem**:
In our current terminal-based game, the management of game state lacks a centralized structure. Unlike traditional game architectures that typically have a single class to oversee the entire game's state, our design lacks this unified management system. This absence results in scattered logic and dispersed responsibility throughout the codebase.

**Pattern**:
We've used the State Pattern to represent various game states as separate classes, each containing specific behavior. This approach enables smooth transitions between states by switching to their respective implementations.

**Implementation**:
![image]

**Consequences**:
- Defined state representation: Game states are now distinct classes, replacing scattered logic for a clear structure.
- Efficient transitions of state: Using the State Pattern simplifies switching between states, enhancing the code readability.
- Organized state management: Despite more classes, the structured design ensures maintainability and scalability.


### A sequence of events is constantly getting updated
**Problem**:
In our beginning game's structure, the absence of a centralized game loop disturbs the organized management of critical functions.Our design lacks this unified system, which results in scattered rendering logic and distributed event management responsibilities across the codebase.

**Pattern**:
To address this challenge, we implemented a structured game loop. This loop efficiently manages event processing, rendering updates and other crucial game functions, arranging a sequence of events in a simplified way.

**Implementation**:
![image]

**Consequences**:
Introducing a structured game loop owns impressive advantages:
- Systematic updating: The dedicated loop ensures systematic updates for rendering and event processing, minimizing scattered logic and improving overall performance.
- Organization: The structured loop fosters an organized codebase, simplifying comprehension and facilitating more effective modification of specific game phases.
- Streamlined game flow: With an organized loop, game events flow more smoothly, enhancing user experience and enabling easier expansion or updates to the game.


### Jump action changes according to character’s state
**Problem**:
Initially, our code contained scattered conditional statements to manage Mari's jumping behavior. Depending on the items collected during gameplay (e.g., a potion granting double jumping ability), the jumping logic varied significantly. This approach violated the Single Responsibility Principle, as one method was overloaded with various conditional checks.

**Pattern**:
We applied the State pattern to address this issue. By employing this pattern, we restructured Mari's jumping behavior into distinct state classes, each representing a different jumping state. For instance:
- Normal State: Mari jumps with default behavior.
- Potion State: Upon collecting a potion, Mari can perform double jumps.

**Implementation**:
![image]

**Consequences**:
The adoption of the State Pattern in our design has resulted in several positive outcomes:
- Explicit state representation: Our code now explicitly defines the various states that control the character's jumping abilities. This clarity replaces the flag-based systems, offering a straightforward and understandable representation of different jumping behaviors.
- Simplified control flow: The several conditional statements have been replaced by a more streamlined approach. By using polymorphism, we've simplified the activation of specific behaviors associated with each state. This approach reduces code complexity and enhances code readability.
- Manageable structure: While introducing more classes and instances to manage different states, the design maintains a manageable number. This ensures that the codebase remains organized without overwhelming complexity, allowing for effective maintenance and extension.


### Enemies should have different strategies to attack the main character
**Problem**:
The game faced issues with disorganized enemy behavior management. The code lacked structure, resulting in tangled logic that made it hard to maintain and grow the game. Different enemy behaviors were entangled, lacking clear distinctions between enemy types and their attack strategies, causing a lack of code organization.

**Pattern**:
To address this issue, we implemented the Strategy Pattern for enemy behavior. This pattern allowed us to create distinct classes for each enemy type, such as GhostEnemy and BatEnemy, encapsulating their unique attack strategies. Through this pattern, we achieved modular and interchangeable enemy behaviors.

**Implementation**:
![image]

**Consequences**:
- Encapsulation: Utilizing the Strategy Pattern optimized enemy behavior management. Each enemy type now encapsulates its attack strategy, reducing convoluted logic and enhancing code organization.
- Dynamic enemy variation: Distinct classes for each enemy type enable effortless switching of attack strategies mid-game, diversifying enemy behaviors without complicating the game's structure.
- Modularity: By isolating enemy behaviors into separate classes, our codebase gained modularity, improving readability, scalability and supporting future expansions.


### The bat enemy should attack if the character passes through a certain place
**Problem**:
In our game, a specific enemy (Bat) should be triggered to attack based on a specific event, in this case, the character passing through a particular place on the map.

**Pattern**:
For this problem, we implemented the Observer Pattern. This pattern allows a subject to notify its observers about any state changes. In this context, the character is the subject and the enemies are the observers waiting for the signal to attack. When the character passes through a specific place on the map, it triggers a state change. The enemies, acting as observers, receive this notification and initiate their attack based on that updated state.
This approach provides a flexible solution, as the character's movement and the enemies' reactions are independent.

**Implementation**:
![image]

**Consequences**:
- Decoupling: The Observer Pattern promotes a loose coupling between the subject and the observers, allowing them to interact without having detailed knowledge of each other.
- Scalability: It also facilitates the addition of new observers without modifying the subject, making the system more scalable and adaptable to some changing requirements.
- Event Handling: Effectively handles relationships one to many, where a single event can notify multiple observers.


### Multiple classes for objects in the game with the same functions, but different implementations
**Problem**:
In the beginning of this project, we thought about how we had to organize various game elements in classes that shared similar functions, but where their implementations diverge. The creation of a different class for every object results in a code characterized by redundancy and repeated code.

**Pattern**:
We decided to use the Factory Method Pattern, where a base class (GameObject) is the foundation for all the game objects, such as mari, enemies and walls. Each specific element type now extends this class, implementing the pattern to create instances of it. That promotes code reuse and encapsulates shared functionalities.

**Implementation**:
![image]

**Consequences**:
- Encapsulation:  By encapsulating the object creation process in a separate method, it allows the subclasses to provide their own implementations and variations while maintaining the rest of the code.
- Consistent Creation: This pattern ensures a unified and consistent approach for creating diverse game objects, by enabling many classes to share a common interface.
- Extensibility: Adding new elements to the game becomes more easier and straightforward, as they adhere to the common creation interface. A centralized creation logic simplifies additions without modifying the existing code.


## KNOWN CODE SMELLS

**Jump forward**:

- Tangled Input Handling: The code might have intertwined logic for handling multiple key inputs simultaneously. Handling both the arrow-up and arrow-right keys for jumping forward seems to be resulting in infinite flight, suggesting a conflict in input processing.

- Lack of Collision Handling: There might be a missing check for collision or obstruction during the character's forward jump. Without collision detection, the character can move infinitely, ignoring any obstacles or boundaries.


## TESTING
![image]

## SELF-EVALUATION
The tasks were distributed equally and everyone contributed to the development of the project.
- Teresa Mascarenhas: 50%
- Sofia Gonçalves: 50%
