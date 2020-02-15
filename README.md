# breadth-and-depth-first-searches-isabellarolfe13
breadth-and-depth-first-searches-isabellarolfe13 created by GitHub Classroom

# Projects
  # Sudoku 
   Depth First Search
   1) Run Soduku.java (files needed Soduku.java, StackSod.java, NodeSod.java, Location.java)
   
   Breadth First Search
   1) Run SodukuBreath.java (files needed SodukuBreath.java, QueueSod.java, NodeSod.java, Location.java)
   # SUDOKU GAME FILES
   -boardFileEasy.txt
   -boardFileHard.txt
   -boardFileMedium.txt
   
  # Maze 
  (fixed my Breadth from last year and improved GUI errors)
  
  Depth First Search
  1) Run MazeRunner.java (files needed Location.java, Node.java, Stack.java)
  
  Breadth First Search
  1) Run MazeRunnerBreath.java (files needed Location.java, NodeBreath.java, Queue.java)
  # MAZE BOARD FILES
  -board.txt
  -board2.txt
  -board3.txt
  
  # OVERVIEW OF SUDOKU:
I first started Sudoku with a depth first search. My algorithm goes through the board starting in the top left corner and moving across to the right and then down. I started by creating my main methods: Solve(), FindOpen(), GetOption(), and AddMakeBoard(). The main data structure that I used was a stack. I decided to store various boards in objects of the class NodeSod which just contains the 2d array board[][]. I also have a simple Location class for x and y coordinates and a classic Stack class. Solve continues to run in a while loop while the board is not solved. It finds an open zero on the board and then finds available options for this space and stores these options in an arraylist called option. Since it is depth first search, it is going to go down one of these options and push it onto the stack using the addMakeBoard() method. Once it explores a path, and if options==null it pops off the stack and then continues down a different route until the board is filled and solved. Overall, my algorithm itself was straightforward. I had some problems with my getOption() method which was very complicated originally. I ended up splitting it up into helper methods and simplifying it. I also dealt with a pointer bug, in my addMakeBoard() method that I found and fixed when I realized I needed to create an new array and edit that one. In the console of my program I print out the most recent board and the stack size so the user can see the stack change, and the different paths it is taking. 

My breadth first search is very similar to my depth. After coding depth, I found it straightforward to code breadth. A major difference was I used a queue I made called QueueSod instead of a stack. My method names and uses are similar. However in breath, it enqueues all the viable options onto the queue and explores the paths and sees which one is able to solve the board. Changing from a queue to a stack changed the way boards were handled. Similar to my depth, the user can see the queue says changing and the NodeSods being enqueued onto the queue. 

There are three boards files I’ve created to test Soduku for breadth and depth first searches. It’s fascinating to watch the stack and queue change and the paths they take to solve the board.

