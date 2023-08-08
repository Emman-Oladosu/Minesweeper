import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Grid{

	private boolean [ ][ ] bombGrid;
	private int [ ][ ] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	
	public Grid(int rows, int columns) {
		numRows = rows;
		numColumns = columns;
		countGrid =  new int [numRows][numColumns];
	    bombGrid = new boolean[numRows][numColumns];
	    numBombs = 25;	
	  createBombGrid();
	  createCountGrid();
	}
	
	public Grid() {
		this(10, 10);
		    
		
	}


	
	public Grid(int rows, int columns, int numBombs) {
		numRows = rows;
		numColumns = columns;
		countGrid =  new int [numRows][numColumns];
	    bombGrid = new boolean[numRows][numColumns];
	    this.numBombs = numBombs;	
	  createBombGrid();
	  createCountGrid();
	}
	
public boolean[][] getBombGrid() {
		
		boolean[][] copyBombGrid = new boolean [numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copyBombGrid[i][j] = bombGrid[i][j];
			}
		}
		return copyBombGrid;
	}

	public int[][] getCountGrid() {
		int[][] copyCountGrid = new int [numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copyCountGrid[i][j] = countGrid[i][j];
			}
		}
		return copyCountGrid;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	
	public void createBombGrid() {

		bombGrid = new boolean[numRows][numColumns];
		for (int i = 0; i < bombGrid.length; i++) {
			for (int j = 0; j < bombGrid[i].length; j++) {
				bombGrid[i][j] = false;
			}
		}

		Random random = new Random();

		int bombCount = 0;
		while (bombCount != this.numBombs) {
			int row = random.nextInt(numRows);
			int column = random.nextInt(numColumns);
			boolean bombValue = bombGrid[row][column];
			if (bombValue == false) {
				bombGrid[row][column] = true;
				bombCount += 1;
			}
		}
		
		
	    //BOMB GRID TEST UPDATE: SUCCESS!!
	    for(int i = 0; i < this.bombGrid.length;i++) {
	    	System.out.println();
	    	
	    	for(int j = 0; j < bombGrid[i].length; j++) {
	    		System.out.print(String.format("\t %s",this.bombGrid[i][j] + " "));
	    		
	    	}
	}
	    
	    
	}

	
	private void createCountGrid() {
   
		
		for(int i = 0; i < numRows ; i++) {
			for(int j = 0; j < numColumns; j++) {
				int nearBombCount = 0;
				
				//rocDoesNotExistException
				//if (bombGrid[i][j] == true){}
				try {
				 
				
					if(bombGrid[i][j])countGrid[i][j]++;
					if(i < numRows - 1) {
						if(bombGrid[i+1][j])countGrid[i][j]++;
					}
					if(i > 0) {
					if(bombGrid[i-1][j])countGrid[i][j]++;
					}
					
					if(j < this.numColumns - 1) {
						if(bombGrid[i][j+1])countGrid[i][j]++;
					}
					if(j > 0) {
						if(bombGrid[i][j-1])countGrid[i][j]++;
					}
					if(i < this.numRows - 1 && j < this.numColumns - 1) {
						if(bombGrid[i+1][j+1])countGrid[i][j]++;
					}
					if(i > 0 && j > 0) {
						if(bombGrid[i-1][j-1])countGrid[i][j]++;
					}
					if(i < this.numRows - 1 && j > 0) {
						if(bombGrid[i+1][j-1])countGrid[i][j]++;
					}
					if(i > 0  && j < this.numColumns - 1) {
						if(bombGrid[i-1][j+1])countGrid[i][j]++;
					}
					nearBombCount++;
				  
				} catch(ArrayIndexOutOfBoundsException roc){
					
				}

				
		   }//end of inner loop (j) 
	     }//end of outer loop(i)
		
		//COUNT GIRD TEST UPDATE: SUCCESS!
		  for(int x = 0; x < this.countGrid.length;x++) {
		    	System.out.println();
		    	
		    	for( int j = 0; j < countGrid[x].length; j++) {
		    		System.out.print(String.format("\t %s",this.countGrid[x][j] + " "));
		    		
		    	}
		}
		
	}


  public static void main(String[] args) {
	 // Grid test = new Grid(10,10,3);
	  
  }


}
