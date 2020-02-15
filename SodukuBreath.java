/*
 * Isabella Rolfe
 * Sudoku using Breadth First Search
 * 02/15/20
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class SodukuBreath {
	public int row=9;
	public int col=9;
	public int board[][]=new int[row][col];
	//a queue class called QueueSod
	public QueueSod queuee; 
	private Scanner BoardScanner;
	public static File boardFile=new File("boardFileMedium.txt");
	
	public SodukuBreath() throws FileNotFoundException {
		for(int x=0;x<row;x++) {
			for(int y=0;y<col;y++) {
				board[x][y]=0;
			}
		}
		readBoard();
		//Queue class called QueueSod
		queuee=new QueueSod();
		NodeSod myboard=new NodeSod(board);
		queuee.enqueue(myboard);
	}
	
	private void readBoard() throws FileNotFoundException{
		BoardScanner = new Scanner(boardFile);
		for(int row=0;row<9;row++) {
			String line=BoardScanner.nextLine();
			for(int col=0;col<9;col++) {
				board[row][col]=Character.getNumericValue(line.charAt(col*2));  
			}
		}
	}
	//main solving method
	public void solve() {
		while(!isBoardFilled(queuee.Peek().getBoard())) { 
			System.out.println("Queue size: " + queuee.size());
			NodeSod myBoard=queuee.dequeue();
			printBoard(myBoard.getBoard());
			//start top left, go across row and then down column
			Location open=findOpen(myBoard.getBoard());
			ArrayList<Integer> option=getOption(myBoard.getBoard(), open);
			if(option!=null) {
				for(int x=0;x<option.size();x++) {
					NodeSod n=addMakeBoard(myBoard.getBoard(), option.get(x), open);
					queuee.enqueue(n);
				}
			}
		}
		board=queuee.dequeue().getBoard();
	}
	
	//finds open 0 in board 
	public Location findOpen(int arr[][]) {
		for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr.length;col++) {
				if(arr[row][col]==0) {
					Location loc=new Location(row,col);
					return loc;
				}
			}
		}
		return null;
	}
	
	//gets available options for given 0
	public ArrayList<Integer> getOption(int[][]board, Location x) {
		ArrayList<Integer> moves=new ArrayList<Integer>();
		
		for(int z=1;z<10;z++) {
			moves.add(z);
		}
		int row=x.getX();
		int col=x.getY();
		//checks row
		moves=checkRow(board,moves, row);
		//checks col
		moves=checkColumn(board, moves, col);
		//checks in square
		moves=checkBox(board,moves, row, col);
		return moves;
	}
	
	//3 helper methods below
	public ArrayList<Integer> checkRow(int[][]board, ArrayList<Integer> moves, int row) {
		for(int i=0; i<board.length;i++) {
			int locationof=moves.indexOf(board[row][i]);
				if(moves.contains(board[row][i])) {
					moves.remove(locationof);
				}
		}
		return moves;
	}
	public ArrayList<Integer> checkColumn(int[][]board, ArrayList<Integer> moves, int col) {
		for(int l=0; l<board.length;l++) {
			int locationof=moves.indexOf(board[l][col]);
			if(locationof!=-1) {
				if(moves.contains(board[l][col])) {
					moves.remove(locationof);
				}
			}
		}
		return moves;
	}
	public ArrayList<Integer> checkBox(int[][]board, ArrayList<Integer> moves, int row, int col) {
		if(col/3==0) {
			if(row/3==0) {
				for(int roww=0;roww<3;roww++) {
					for(int coll=0;coll<3;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==1) {
				for(int roww=3;roww<6;roww++) {
					for(int coll=0;coll<3;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==2) {
				for(int roww=6;roww<9;roww++) {
					for(int coll=0;coll<3;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
		}
		if(col/3==1) {
			if(row/3==0) {
				for(int roww=0;roww<3;roww++) {
					for(int coll=3;coll<6;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==1) {
				for(int roww=3;roww<6;roww++) {
					for(int coll=3;coll<6;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==2) {
				for(int roww=6;roww<9;roww++) {
					for(int coll=3;coll<6;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
		}
		if(col/3==2) {
			if(row/3==0) {
				for(int roww=0;roww<3;roww++) {
					for(int coll=6;coll<9;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==1) {
				for(int roww=3;roww<6;roww++) {
					for(int coll=6;coll<9;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
			else if(row/3==2) {
				for(int roww=6;roww<9;roww++) {
					for(int coll=6;coll<9;coll++) {
						int locationof=moves.indexOf(board[roww][coll]);
						if(moves.contains(board[roww][coll])) {
							moves.remove(locationof);
						}
					}
				}
			}
		}
		return moves;
	}
	
	//creates new NodeSod and adds number to its board
	public NodeSod addMakeBoard(int[][] arr, int number, Location loc) {
		int arr2[][]=new int[9][9];
		for(int row=0;row<9;row++) {
			for(int col=0;col<9;col++) {
				arr2[row][col]=arr[row][col];
			}
		}
		arr2[loc.getX()][loc.getY()]=number;
		NodeSod n=new NodeSod(arr2);
		return n;
	}
	
	//checks if board is filled
	public boolean isBoardFilled(int[][]board) {
		for(int x=0;x<board.length;x++) {
			for(int y=0;y<board.length;y++) {
				if(board[x][y]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	//prints board
	public void printBoard(int[][] arr) {
		for(int x=0;x<arr.length;x++) {
			for(int y=0;y<arr.length;y++) {
				System.out.print(arr[x][y] + " ");
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	//prints queue
	public void printQueue(){
        if(queuee==null){
            return;
        }
        NodeSod current = queuee.Peek();
        printBoard(current.getBoard());
        while(current.getNext()!=null){
            current = current.getNext();
            printBoard(current.getBoard());
        }
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to Sudoku!");
		Scanner scan=new Scanner(System.in);
		SodukuBreath game=new SodukuBreath();
		game.solve();
		System.out.println("final board:");
		game.printBoard(game.board);
	}
}
