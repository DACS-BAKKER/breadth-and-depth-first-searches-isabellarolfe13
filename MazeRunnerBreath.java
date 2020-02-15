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

public class MazeRunnerBreath extends GraphicsProgram {
	public char[][] board;
	public NodeBreath start;
	public NodeBreath currentnode;
	public Location forReturnnextspot=new Location(0,0);
	public static Queue queue=new Queue();
	static ArrayList <NodeBreath> beenthere=new ArrayList <NodeBreath>();
	private GCanvas MazeCanvas;
	public static int APPLICATION_WIDTH = 500;
	public static int APPLICATION_HEIGHT = 500;
	public ArrayList <String> fullofstrings;
	public Location begin;
	public Location end;
	public Boolean found=false;
	public ArrayList <NodeBreath> path=new ArrayList <NodeBreath>();

	public void run(){
		MazeCanvas = this.getGCanvas();
		readin();
		setUpMaze();
		placeonMaze();
		//search for start, and create that as first node
		searchforstart();
		//all in while loop while end is not found
		addtoqueue();
		//if can't go farther remove from stack
		System.out.println("the path:");
		for(int x=0;x<path.size();x++) {
			System.out.println(path.get(x).getLocation().toString());
		}
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
					System.out.println("found start");
					start=new NodeBreath(x,y,null);
				}
			}
		}
		beenthere.add(start);
		System.out.println("added " + start +"to been there");

		if(start==null) {
			System.out.println("start is null!");
		}
		else {
			queue.enqueue(start);
			System.out.println(queue.Peek());
		}
	}
	public void addtoqueue() {
		currentnode=start;
		//different to depth, have to do one more step and get location then get x and y
		//which means we haven't found end for below when found =false
		while(found!=true) {
			System.out.println(currentnode.getLocation());
			//while its not at end
			addNextspots();
			//need to leave the loop if found equals true
			if(found==true) {
				break;
			}
			//after found all neighbors, remove them from queue
			queue.dequeue();
			System.out.println("have removed from queue");
			if(queue.Peek()!=null) {
				currentnode=queue.Peek();
			}
		}

		//loop through and add to this arraylist nodes to create official path
		while(currentnode.getParent()!=null) {
			path.add(currentnode);
			changecolor(currentnode.getLocation().getX()*20,currentnode.getLocation().getY()*20,"blue");
			currentnode=currentnode.getParent();
		}
	}
	//always go to right and down
	public void addNextspots() {
		NodeBreath node1=new NodeBreath(currentnode.getLocation().getX()+1,currentnode.getLocation().getY(),currentnode);
		if(board[node1.getLocation().getX()][node1.getLocation().getY()]=='G') {
			//if found is true it means currentnode is one before end
			found=true;
		}

		else if(board[currentnode.getLocation().getX()+1][currentnode.getLocation().getY()]!='#' && inlist(node1)==false) {
			//send this back in location form
			queue.enqueue(node1);
			changecolor(node1.getLocation().getX()*20,node1.getLocation().getY()*20, "cyan");
			System.out.println("added " +node1.getLocation().toString()+ "to queue");
			//adds to beenthere because otherwise it will keep repeating neighbors
			beenthere.add(node1);
		}
		NodeBreath node2=new NodeBreath(currentnode.getLocation().getX(),currentnode.getLocation().getY()+1, currentnode);
		if(board[node2.getLocation().getX()][node2.getLocation().getY()]=='G') {
			found=true;
		}
		else if(board[currentnode.getLocation().getX()][currentnode.getLocation().getY()+1]!='#' && inlist(node2)==false) {
			queue.enqueue(node2);
			changecolor(node2.getLocation().getX()*20,node2.getLocation().getY()*20, "cyan");
			System.out.println("added " +node2.getLocation().toString()+ "to queue");
			beenthere.add(node2);

		}
		NodeBreath node3=new NodeBreath(currentnode.getLocation().getX(),currentnode.getLocation().getY()-1, currentnode);
		if(board[node3.getLocation().getX()][node3.getLocation().getY()]=='G') {
			found=true;
		}
		else if(board[currentnode.getLocation().getX()][currentnode.getLocation().getY()-1]!='#' && inlist(node3)==false) {
			queue.enqueue(node3);
			changecolor(node3.getLocation().getX()*20,node3.getLocation().getY()*20, "cyan");
			System.out.println("added " +node3.getLocation().toString()+ "to queue");
			beenthere.add(node3);

		}		
		NodeBreath node4=new NodeBreath(currentnode.getLocation().getX()-1,currentnode.getLocation().getY(), currentnode);
		if(board[node4.getLocation().getX()][node4.getLocation().getY()]=='G') {
			found=true;
		}
		else if(board[currentnode.getLocation().getX()-1][currentnode.getLocation().getY()]!='#' && inlist(node4)==false) {
			queue.enqueue(node4);
			changecolor(node4.getLocation().getX()*20,node4.getLocation().getY()*20, "cyan");
			System.out.println("added " +node4.getLocation().toString()+ "to queue");
			beenthere.add(node4);

		}
	}

	//helper method for getnextspot to check if it's been there
	public boolean inlist(NodeBreath n) {
		for(int x=0;x<beenthere.size();x++) {
			if(beenthere.get(x).getLocation().getX()==n.getLocation().getX() && beenthere.get(x).getLocation().getY()==n.getLocation().getY() ) {
				return true;
			}
		}
		return false;
	}

	public void changecolor(int loc1, int loc2, String color) {
		//cyan: been there and checked
		if(color.equals("cyan")) {
			GRect e = new GRect(loc1, loc2, 20, 20);
			e.setFillColor(Color.CYAN);
			e.setFilled(true);
			MazeCanvas.add(e);
			System.out.println("printing cyan" + loc1 +loc2);
			pause(200);
		}
		//blue: been there and correct path
		else if(color.equals("blue")) {
			GRect e = new GRect(loc1, loc2, 20, 20);
			e.setFillColor(Color.blue);
			e.setFilled(true);
			MazeCanvas.add(e);
			System.out.println("blue "+ loc1+loc2);
		}
	}
}