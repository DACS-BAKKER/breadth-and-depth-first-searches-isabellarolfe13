import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.gui.*;
import javax.swing.*;
//*By: Isabella Rolfe
//5/18/19
public class Mazerunner extends GraphicsProgram {
	public char[][] board;
	public Location start = new Location (0,0);
	public Location currentloc = new Location (0,0);
	public Location forReturnnextspot=new Location(0,0);
	static Stack stack=new Stack();
	static ArrayList <Location> beenthere=new ArrayList <Location>();
	private GCanvas MazeCanvas;
	public static int APPLICATION_WIDTH = 500;
	public static int APPLICATION_HEIGHT = 500;
	public ArrayList <String> fullofstrings;
	public Location begin;
	public Location end;

	public void run(){
		MazeCanvas = this.getGCanvas();
		readin();
		setUpMaze();
		placeonMaze();
		searchforstart();
		//all in while loop while end is not found
		addtostack();
		//if can't go farther remove from stack
		stack.print();
	}
	public void init(){
		JFrame frame = new JFrame();
		APPLICATION_WIDTH= 500;
		APPLICATION_HEIGHT= 500;
	}
	public void setUpMaze(){
		for(int row = 0 ; row<fullofstrings.size(); row++)
			for(int col=0; col<fullofstrings.get(0).length(); col++){
				GRect r = new GRect(row*20, col*20, 20, 20);
				r.setFillColor(Color.WHITE);
				r.setFilled(true);
				MazeCanvas.add(r);
			}
	}

	public void readin() {
		fullofstrings=new ArrayList <String>();
		int ycounter=0;
		int xcounter=0;
		String filename="board2.txt";
		File accountfile = new File(filename); 
		Scanner readerr;
		try {
			readerr = new Scanner(accountfile);
			//stores each of lines as string in array
			while(readerr.hasNextLine()) {
				fullofstrings.add(readerr.nextLine());
				//adds this line to array
			}
			readerr.close();
			//sets board=to however big depending on file
			board=new char[fullofstrings.get(0).length()][fullofstrings.size()];
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//have array list of strings and need to put this into two d array
		for(int x=0;x<fullofstrings.get(0).length();x++) {
			for(int y=0; y<fullofstrings.size();y++) {
				board[x][y]=fullofstrings.get(y).charAt(x);
			}

		}
		for(int i=0;i<board.length;i++) {
			for(int y=0;y<board[0].length;y++) {
				System.out.print(board[i][y]);
			}
			System.out.println();
		}
	}

	public void placeonMaze() {
		for(int x=0; x<board.length;x++) {
			for(int y=0;y<board[0].length;y++)
				if(board[x][y]=='#') {
					GRect r = new GRect (x*20, y*20, 20, 20);
					r.setColor(Color.darkGray);
					r.setFilled(true);
					MazeCanvas.add(r);
				}
				else if(board[x][y]=='S') {
					begin=new Location(x,y);
					GRect s = new GRect (x*20, y*20, 20, 20);
					s.setColor(Color.red);
					s.setFilled(true);
					MazeCanvas.add(s);
				}
				else if(board[x][y]=='G') {
					end=new Location(x,y);
					GRect e = new GRect (x*20, y*20, 20, 20);
					e.setColor(Color.green);
					e.setFilled(true);
					MazeCanvas.add(e);
				}
		}
	}

	private char charAt(int x) {
		return 0;
	}

	public void searchforstart() {
		for(int x=0;x<board.length;x++) {
			for(int y=0; y<board[0].length;y++) {
				if(board[x][y]=='S') {
					start.setX(x);
					start.setY(y);
				}
			}
			beenthere.add(start);
		}
		stack.Push(start.getX(),start.getY());
		System.out.println(stack.Peek());
	}
	public void addtostack() {
		currentloc=start;
		while(board[currentloc.getX()][currentloc.getY()]!='G') {
			Location nextspot=getNextspot();
			//add the next spot we've gotten to the stack
			if(nextspot!=null) {
				System.out.println("pushing a new space" + nextspot.getX() + nextspot.getY());
				stack.Push(nextspot.getX(), nextspot.getY());
				currentloc.setX(nextspot.getX());
				currentloc.setY(nextspot.getY());
				System.out.println("adding to been there" + "(" + nextspot.getX()+nextspot.getY() + ")");
				beenthere.add(nextspot);
				System.out.println("right before if statement");
				System.out.println(nextspot.getX() +", " + nextspot.getY());
				if(nextspot!=begin && nextspot!=end) {
					System.out.println("next spot is: " + nextspot.getX() + " " + nextspot.getY());
					changecolor(nextspot.getX()*20, nextspot.getY()*20, "cyan");
				}
			}
			else {
				System.out.println("I am removing from stack "  + currentloc.getX() + " " + currentloc.getY());
				changecolor(currentloc.getX()*20,currentloc.getY()*20, "blue");
				stack.Pop();
				System.out.println("just popped" + stack.Peek().getLocation().getX() + " " + stack.Peek().getLocation().getY());
				//want to remove from stack so go back and retrace our steps
				stack.Peek().print();
				changecolor(currentloc.getX()*20,currentloc.getY()*20, "blue");
				System.out.println(("test: " + stack.Peek().getLocation()));
				currentloc.setX(stack.Peek().getLocation().getX());
				currentloc.setY(stack.Peek().getLocation().getY());
				System.out.println("have we changed our currentLoc? + " + currentloc.getX() + " " + currentloc.getY());

			}
		}
	}
	//always go to right and down
	public Location getNextspot() {
		Location loc1=new Location(currentloc.getX()+1,currentloc.getY());
		Location loc2=new Location(currentloc.getX(),currentloc.getY()+1);
		Location loc3=new Location(currentloc.getX(),currentloc.getY()-1);
		Location loc4=new Location(currentloc.getX()-1,currentloc.getY());
		if(board[currentloc.getX()+1][currentloc.getY()]!='#' && inlist(loc1)==false) {
			//send this back in location form
			return loc1;
		}
		else if(board[currentloc.getX()][currentloc.getY()+1]!='#' && inlist(loc2)==false) {
			//send this back in location form
			return loc2;
		}
		else if(board[currentloc.getX()][currentloc.getY()-1]!='#' && inlist(loc3)==false) {
			//send this back in location form
			return loc3;
		}		

		else if(board[currentloc.getX()-1][currentloc.getY()]!='#' && inlist(loc4)==false) {
			//send this back in location form
			return loc4;
		}
		//returns null if at has no where to move
		else {
			System.out.println("we are null");
			return null;
		}
	}
	//helper method for getnextspot to check if it's been there
	public boolean inlist(Location loc) {
		System.out.println("we are inlist " + loc.getX() + " " +loc.getY());
		for(int x=0;x<beenthere.size();x++) {
			if(beenthere.get(x).getX()==loc.getX() && beenthere.get(x).getY()==loc.getY()) {
				return true;
			}
		}
		return false;
	}

	public void changecolor(int loc1, int loc2, String color) {
		//cyan: been there and it worked
		if(color.equals("cyan")) {
			GRect e = new GRect(loc1, loc2, 20, 20);
			e.setFillColor(Color.CYAN);
			e.setFilled(true);
			MazeCanvas.add(e);
			System.out.println("printing cyan" + loc1 +loc2);
			pause(40);
		}
		//blue: been there and it didn't work
		else if(color.equals("blue")) {
			GRect e = new GRect(loc1, loc2, 20, 20);
			e.setFillColor(Color.blue);
			e.setFilled(true);
			MazeCanvas.add(e);
			System.out.println("blue "+ loc1+loc2);
			pause(500);
		}
	}
}

