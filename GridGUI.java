import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridGUI extends JFrame{
	
	private GridJPanel minesweeperGame;

	

	public GridGUI() {
		
		minesweeperGame = new GridJPanel();
		
		add(minesweeperGame);
		setTitle("Minesweeper");
		setSize(530,530);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	private class GridJPanel extends JPanel implements GridInterface, ActionListener{
		
		Grid grid = new Grid();
		boolean clicked = false;
		
		boolean [][] bombGrid = grid.getBombGrid();
		int [][] countGrid = grid.getCountGrid();
		int bombNum = grid.getNumBombs();

		private JButton [][] buttons; //2d array for buttons and bombs, count should be revealed under each button
		private int numRows = 10; //FIXME, NUMBER OF ROWS MAY CHANGE DEPENDING ON USER
		private int numColumns = 10;//FIXME, NUMBER OF COLUMNS MAY CHANGE DEPENDING ON USER

		
		public GridJPanel() {//FIXME
			
			setLayout(new GridLayout(numRows,numColumns));
			displayGame();

		}
		
		@Override
		public void displayGame() {//FIXME
			// TODO Auto-generated method stub
			buttons = new JButton [numRows][numColumns];
			int [][] countGridGUI = new int [numRows][numColumns];
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numColumns; j++) {
					buttons[i][j] = new JButton();
					buttons[i][j].addActionListener(this);
					buttons[i][j].setEnabled(true);
					this.add(buttons[i][j]);
					
				}
			}
			
			
		}

		@Override
		public boolean isWinner() {//FIX ME, RETRUN TRUE IS THERE ARE ONLY BOMBS LEFT
			// TODO Auto-generated method stub
			
			for(int i = 0; i < buttons.length;i++) {
				for(int j = 0; j < buttons[i].length; j++) {
					
					if ((bombGrid[i][j]==true) && clicked == true) {
						//DO SOMETHING
						return false;
					}
				}
			}
			
			return true;
		}

		@Override
		public boolean isLoser() {//FIX ME, RETURN TRUE IF BOMB IS CLICKED
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void playAgain() {//FIX ME, FIND WAY TO RESTART GAME
			// TODO Auto-generated method stub
			String textToDisplay = "Do you want to play again? ";
			int yesNo = JOptionPane.showConfirmDialog(null, textToDisplay);
			if(yesNo == JOptionPane.YES_OPTION){
				//open up new game
				
				grid = new Grid(); 
		                bombGrid = grid.getBombGrid();
		                countGrid = grid.getCountGrid(); 
		                bombNum = grid.getNumBombs(); 
		        
		               for (int i = 0; i < numRows; i++) {
		                   for (int j = 0; j < numColumns; j++) {
		                       buttons[i][j].setText("");
		                       buttons[i][j].setEnabled(true);
		                   }
		               }
				
			}else {
				System.exit(EXIT_ON_CLOSE);
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btnClicked = (JButton) e.getSource();
			
			//TEST IF BUTTON WORKS, UPDATE: BUTTON WORKS
//			if(e.getSource() == btnClicked) {
//                // do action necessary code 
//				System.out.println("Button Clicked!");
//              }
		
			for(int i = 0; i < buttons.length;i++) {
				for(int j = 0; j < buttons[i].length; j++) {
					if(e.getSource() == buttons[i][j]) { 
						//System.out.println("we have " + i + " and  space " + j);
						clicked = true;
						
						if(bombGrid[i][j] == true) {//LOSING CONDITION, ELSE CONTINUE GAME
							bombNum--;
							buttons[i][j].setText("!");
							displayLoser();
							buttons[i][j].setEnabled(false);
						}else {
							buttons[i][j].setText(""+countGrid[i][j]);
							if(countGrid[i][j] == 0) {
								zeroCount(i,j);
							}
						}
						
						if(isWinner()){
						   displayWinner();
						}
						}
	
					
					
				}
			}
			
			
			//TEST IF PLAYER LOST (BOMB WAS CLICKED)
			if(isLoser()) {
				displayLoser();
				
			}
			
		}
		
		public void zeroCount(int i, int j) {
			
			try {
			
			if(bombGrid[i][j] == false) {
			buttons[i][j].setText(""+countGrid[i][j]);
			
			}
			if(bombGrid[i+1][j] == false) {
			buttons[i+1][j].setText(""+countGrid[i+1][j]);
			   if(countGrid[i+1][j] == 0) {
				   zeroCount(i+1,j);
			   }
			}
			if(bombGrid[i][j+1] == false) {
			buttons[i][j+1].setText(""+countGrid[i][j+1]);
			   if(countGrid[i][j+1] == 0) {
				   zeroCount(i,j+1);
			   }
			}
			if(bombGrid[i+1][j+1] == false) {
			buttons[i+1][j+1].setText(""+countGrid[i+1][j+1]);
			   if(countGrid[i+1][j+1] == 0) {
				   zeroCount(i+1,j+1);
			   }
			}
			if(bombGrid[i-1][j] == false) {
			buttons[i-1][j].setText(""+countGrid[i-1][j]);
			   if(countGrid[i-1][j] == 0) {
				   zeroCount(i-1,j);
			   }
			}
			if(bombGrid[i][j-1] == false) {
			buttons[i][j-1].setText(""+countGrid[i][j-1]);
			   if(countGrid[i][j-1] == 0) {
				   zeroCount(i,j-1);
			   }
			}
			if(bombGrid[i-1][j-1] == false) {
			buttons[i-1][j-1].setText(""+countGrid[i-1][j-1]);
			if(countGrid[i-1][j-1] == 0) {
				zeroCount(i-1,j-1);
			}
			}
			if(bombGrid[i-1][j+1] == false) {
			buttons[i-1][j+1].setText(""+countGrid[i-1][j+1]);
			   if(countGrid[i-1][j+1] == 0) {
				   zeroCount(i-1,j+1);
			   }
			}
			if(bombGrid[i+1][j-1] == false) {
				buttons[i+1][j-1].setText(""+countGrid[i+1][j-1]);
				   if(countGrid[i+1][j-1] == 0) {
					   zeroCount(i+1,j-1);
				   }
			}
			
			} catch(ArrayIndexOutOfBoundsException roc) {}
			
		}

		@Override
		public void displayWinner() {//FIXME, CHECK FOR ANY ERRORS, UPDATE: WORKS
			// TODO Auto-generated method stub
			String textToDisplay = "YOU WIN! :) ";
			JOptionPane.showMessageDialog(null, textToDisplay);
			playAgain();
		}

		@Override
		public void displayLoser() {
			// TODO Auto-generated method stub
			String textToDisplay = "OH NO! YOU PRESSED A BOMB :( ";
			JOptionPane.showMessageDialog(null, textToDisplay);
			playAgain();
			
		}
		
	}
}


