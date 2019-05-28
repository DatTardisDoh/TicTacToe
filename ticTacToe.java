import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Name: Michael Erickson
 * Date: May 16, 2019
 * Version: 1.0
 * Program Name: Tic Tac Toe
 *
 * Description: This is my final project. Tic tac toe, the classic game from the Ancient Roman Empire, is now
 * available in full computerized form.
 *
 * What problems did I experience? How did I fix them?: The hardest part of this program, by far, was the creation of a computer character to play against. At first it would 
 * just randomly place using the code found within opponentTurn() (and not opponentSmartMove()). After thinking through the strategy of Tic-Tac-Toe, I realized what setups were needed
 * to make the "logical" moves. After toying around with the code for a week, I found a great way to make the computer opponent feel a bit more "human."
 *
 * What did I learn?: How much I've learned this year. Thank you, Mr. Safford.
 */

public class ticTacToe {
	//Instance Variables:
	public String[] board;
	public String playerLetter;
	private String oppLetter;
	private JFrame setupFrame;
	private JFrame frame;
	private JLabel gameResult;
	private JButton newGameButton;
	private JLabel score;
	private JButton[] button = new JButton[9];
	private int xWins = 0;
	private int oWins = 0;
	private Point frameLocation = new Point(0,0);
	private boolean userFirst;
	
	//Constructors:
	public ticTacToe() {
		board = new String[9]; //creates the board
		gameSetup(); 
	}
	
	
	//Methods:
	//Creates a window that asks what letter the user wants to play as
	public void gameSetup() {
		setupFrame = new JFrame("Tic-Tac-Toe Setup");
		setupFrame.setResizable(false); //makes it so the user can't screw up the proportions that make the window look not terrible
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.setSize(318, 122); //these numbers came with A LOT of guessing and checking
		
		JPanel setupPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel chooseMessage = new JLabel("Who would you like to play as?");
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = .5; //important in making everything be spaced correctly and not super cramped
		c.gridx = 1;
		c.gridy = 0;
		setupPane.add(chooseMessage, c);
		
		JButton chooseX = new JButton("X");
		c.gridx = 0;
		c.gridy = 1;
		setupPane.add(chooseX, c);
		//actionListeners add meaning to the buttons by executing whats inside the actionPerformed section when clicked
		ActionListener xBut = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerLetter = "X";
				oppLetter = "O";
				setupFrame.dispose();
				makeWindow();
			}
	       };
	    chooseX.addActionListener(xBut);
		
		JButton chooseO = new JButton("O");
		c.gridx = 2;
		c.gridy = 1;
		setupPane.add(chooseO, c);
		ActionListener oBut = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerLetter = "O";
				oppLetter = "X";
				setupFrame.dispose();
				makeWindow();
			}
	       };
	    chooseO.addActionListener(oBut);
	    
		setupFrame.getContentPane().add(setupPane);
		setupFrame.setVisible(true);
		
	}
	
	//returns returns true if a letter has been placed, false if otherwise
	//makes the "smart move" by blocking or completing a line
	private boolean opponentSmartMove() { 
		String analyzeLine; 
		for(int i = 0; i < 8; i++) {
			switch(i) {
			case(0): //0, 1, 2
				analyzeLine = ""; //the line starts empty
				if(board[0] != null) {
					analyzeLine += board[0];
				}
				if(board[1] != null) {
					analyzeLine += board[1];
				}
				if(board[2] != null) {
					analyzeLine += board[2];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					//if the line formed contains "XX" or "OO"
					if(board[0] == null) {
						placeLetter(button[0], oppLetter);
					}
					if(board[1] == null) {
						placeLetter(button[1], oppLetter);
					}
					if(board[2] == null) {
						placeLetter(button[2], oppLetter);
					}
					return true; //tells the opponentTurn method that it shouldn't allow another opponent placement
				}
			case(1): // 0, 3, 6
				analyzeLine = "";
				if(board[0] != null) {
					analyzeLine += board[0];
				}
				if(board[3] != null) {
					analyzeLine += board[3];
				}
				if(board[6] != null) {
					analyzeLine += board[6];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[0] == null) {
						placeLetter(button[0], oppLetter);
					}
					if(board[3] == null) {
						placeLetter(button[3], oppLetter);
					}
					if(board[6] == null) {
						placeLetter(button[6], oppLetter);
					}
					return true;
				}
			case(2): //0, 4, 8
				analyzeLine = "";
				if(board[0] != null) {
					analyzeLine += board[0];
				}
				if(board[4] != null) {
					analyzeLine += board[4];
				}
				if(board[8] != null) {
					analyzeLine += board[8];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[0] == null) {
						placeLetter(button[0], oppLetter);
					}
					if(board[4] == null) {
						placeLetter(button[4], oppLetter);
					}
					if(board[8] == null) {
						placeLetter(button[8], oppLetter);
					}
					return true;
				}
			case(3): //1, 4, 7
				analyzeLine = "";
				if(board[1] != null) {
					analyzeLine += board[1];
				}
				if(board[4] != null) {
					analyzeLine += board[4];
				}
				if(board[7] != null) {
					analyzeLine += board[7];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[1] == null) {
						placeLetter(button[1], oppLetter);
					}
					if(board[4] == null) {
						placeLetter(button[4], oppLetter);
					}
					if(board[7] == null) {
						placeLetter(button[7], oppLetter);
					}
					return true;
				}
			case(4): //2, 5, 8
				analyzeLine = "";
				if(board[2] != null) {
					analyzeLine += board[2];
				}
				if(board[5] != null) {
					analyzeLine += board[5];
				}
				if(board[8] != null) {
					analyzeLine += board[8];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[2] == null) {
						placeLetter(button[2], oppLetter);
					}
					if(board[5] == null) {
						placeLetter(button[5], oppLetter);
					}
					if(board[8] == null) {
						placeLetter(button[8], oppLetter);
					}
					return true;
				}
			case(5): //2, 4, 6
				analyzeLine = "";
				if(board[2] != null) {
					analyzeLine += board[2];
				}
				if(board[4] != null) {
					analyzeLine += board[4];
				}
				if(board[6] != null) {
					analyzeLine += board[6];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[2] == null) {
						placeLetter(button[2], oppLetter);
					}
					if(board[4] == null) {
						placeLetter(button[4], oppLetter);
					}
					if(board[6] == null) {
						placeLetter(button[6], oppLetter);
					}
					return true;
				}
			case(6): //3, 4, 5
				analyzeLine = "";
				if(board[3] != null) {
					analyzeLine += board[3];
				}
				if(board[4] != null) {
					analyzeLine += board[4];
				}
				if(board[5] != null) {
					analyzeLine += board[5];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[3] == null) {
						placeLetter(button[3], oppLetter);
					}
					if(board[4] == null) {
						placeLetter(button[4], oppLetter);
					}
					if(board[5] == null) {
						placeLetter(button[5], oppLetter);
					}
					return true;
				}
			case(7): //6, 7, 8
				analyzeLine = "";
				if(board[6] != null) {
					analyzeLine += board[6];
				}
				if(board[7] != null) {
					analyzeLine += board[7];
				}
				if(board[8] != null) {
					analyzeLine += board[8];
				}
				if(analyzeLine.equals(oppLetter + oppLetter) || analyzeLine.equals(playerLetter + playerLetter)) {
					if(board[6] == null) {
						placeLetter(button[6], oppLetter);
					}
					if(board[7] == null) {
						placeLetter(button[7], oppLetter);
					}
					if(board[8] == null) {
						placeLetter(button[8], oppLetter);
					}
					return true;
				}
			}
		}
		return false; //if nothing has been done
	}	

	//plays for the opponent
	private void opponentTurn() {
		if(spacesAvailable() == false) {
			draw(); //first checks to see if the opponent can even play
		} else if(opponentSmartMove() == false) { //if opponentPossibleWinCheck() block or win
			boolean unplaced = true;
			while(unplaced) {
				//attempts to place in random spots until something is empty
				int index = new Random().nextInt(9); //numbers 0-8
				if(board[index] == null) {
					placeLetter(button[index], oppLetter);					
					unplaced = false; //ends the while loop once something has been placed
				}
			}
		}
	}
	
	//goes through the whole process of changing the buttons and board
	public void placeLetter(JButton but, String letter) {
		if(spacesAvailable() == false) {
			draw();
		}
		if(but.isEnabled() == true) { //to prevent duplicate placing
			but.setText(letter);
			but.setEnabled(false); //disables the button
			String buttonName = but.getName();
			int buttonIndex = Integer.parseInt(buttonName); //array index
			board[buttonIndex] = letter; //places letter into the array
		} 
	}

	//checks to see if any space is open by seeing if any cell is "filled with" null
	//true if available, false otherwise
	private boolean spacesAvailable() {
		boolean avail = false; //starts off saying "nothing's available"
		for(int i = 0; i < board.length; i++) {
			if(board[i] == null) {
				avail = true; //once something is available avail is set to true
			}
		}
		return avail;
	}
	
	private void turn(int index) {
		placeLetter(button[index], playerLetter); //places the letter
		if(winCheck() != null) {
			win(winCheck()); //checks to see if user has won
		}
		opponentTurn(); //plays for the opponent
		if(winCheck() != null) {
			win(winCheck()); //checks to see if the opponent won
		}
		if(spacesAvailable() == false) {
			draw();
		}
	}
	//creates the main window that is played
	//a large part of the program is found here
	private void makeWindow() {
	       frame = new JFrame("Tic-Tac-Toe");
	       frame.setResizable(false);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(600, 600);
	       frame.setLocation(frameLocation); //defaults to 0 but updates upon a new game to wherever the frame is
	       
	       JPanel pane = new JPanel(new GridBagLayout());
	       GridBagConstraints c = new GridBagConstraints();
	       
	       //to see if the user or computer goes first:
	       Random r = new Random();
	       if(r.nextInt(2) == 0) {
	    	   userFirst = true;
	       } else {
	    	   userFirst = false;
	       }

	       button[0] = new JButton(board[0]);
	       button[0].setName("0"); //sets the name to the corresponding index
	       //all of this "c" nonsense sets up the nature of the cell for the gui
	       c.fill = GridBagConstraints.BOTH;
	       c.weighty = .5;
	       c.weightx = .5;
	       //these two place the cell in a spot on the grid (0, 0)
	       c.gridx = 0;
	       c.gridy = 0;
	       pane.add(button[0], c);
	       //the ActionListener gives the button something to do when clicked
	       ActionListener but0 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(0);
			}
	       };
	       button[0].addActionListener(but0);
	       
	       button[1] = new JButton(board[1]);
	       button[1].setSize(100, 100);
	       button[1].setName("1");
	       c.gridx = 1;
	       c.gridy = 0;
	       pane.add(button[1], c);
	       ActionListener but1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(1);
			}
	       };
	       button[1].addActionListener(but1);

	       button[2] = new JButton(board[2]);
	       button[2].setSize(100, 100);
	       button[2].setName("2");
	       c.gridx = 2;
	       c.gridy = 0;
	       pane.add(button[2], c);
	       ActionListener but2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				placeLetter(button[2], playerLetter);
				turn(2);
			}
	       };
	       button[2].addActionListener(but2);
	       
	       button[3] = new JButton(board[3]);
	       button[3].setSize(100, 100);
	       button[3].setName("3");
	       c.gridx = 0;
	       c.gridy = 1;
	       pane.add(button[3], c);
	       ActionListener but3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				placeLetter(button[3], playerLetter);
				turn(3);
			}
	       };
	       button[3].addActionListener(but3);
	       
	       button[4] = new JButton(board[4]);
	       button[4].setSize(100, 100);
	       button[4].setName("4");
	       c.gridx = 1;
	       c.gridy = 1;
	       pane.add(button[4], c);
	       ActionListener but4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(4);
			}
	       };
	       button[4].addActionListener(but4);
	       
	       button[5] = new JButton(board[5]);
	       button[5].setSize(100, 100);
	       button[5].setName("5");
	       c.gridx = 2;
	       c.gridy = 1;
	       pane.add(button[5], c);
	       ActionListener but5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(5);
			}
	       };
	       button[5].addActionListener(but5);
	       
	       button[6] = new JButton(board[6]);
	       button[6].setSize(100, 100);
	       button[6].setName("6");
	       c.gridx = 0;
	       c.gridy = 2;
	       pane.add(button[6], c);
	       ActionListener but6 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(6);
			}
	       };
	       button[6].addActionListener(but6);
	       
	       button[7] = new JButton(board[7]);
	       button[7].setSize(100, 100);
	       button[7].setName("7");
	       c.gridx = 1;
	       c.gridy = 2;
	       pane.add(button[7], c);
	       ActionListener but7 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(7);
			}
	       };
	       button[7].addActionListener(but7);
	       
	       button[8] = new JButton(board[8]);
	       button[8].setSize(100, 100);
	       button[8].setName("8");
	       c.gridx = 2;
	       c.gridy = 2;
	       pane.add(button[8], c);
	       ActionListener but8 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn(8);
			}
	       };
	       button[8].addActionListener(but8);
	       
	       //displays win count for each letter
	       score = new JLabel("X: " + xWins + "    O: " +oWins);
	       score.setSize(40, 15);
	       c.weightx = 0;
	       c.weighty = .1;
	       c.gridx = 0;
	       c.gridy = 3;
	       pane.add(score, c);
	       
	       //says "X wins" or "O wins" upon win
	       gameResult = new JLabel("");
	       gameResult.setSize(40, 15);
	       c.gridx = 1;
	       c.gridy = 3;
	       pane.add(gameResult, c);
	       
	       //creates a new game when clicked
	       newGameButton = new JButton("New Game?");
	       newGameButton.setSize(40, 15);
	       c.weightx = 0;
	       c.weighty = 0;
	       c.anchor = GridBagConstraints.CENTER;
	       c.gridx = 2;
	       c.gridy = 3;
	       ActionListener ngb = new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   newGame();
	    	   }
	       };
	       newGameButton.addActionListener(ngb);
	       pane.add(newGameButton, c);
	       newGameButton.setVisible(false);
	       
	       pane.setOpaque(true);
	       frame.getContentPane().add(pane);
	       frame.setVisible(true);
	       
	       if(userFirst == false) {
	    	   opponentTurn(); //starts off by having computer play, then everything goes as normal
	       }
	}
	
	//checks to see if there is a win on the board, executed after every opponent and human move
	private String winCheck() {
		//return either x, o, or null
		for(int i = 0; i < 8; i++) {
			String winLine = null;
			switch(i) {
			case(0): 
				winLine = board[0] + board[1] + board[2];
				break;
			case(1): 
				winLine = board[0] + board[3] + board[6];
				break;
			case(2): 
				winLine = board[0] + board[4] + board[8];
				break;
			case(3): 
				winLine = board[1] + board[4] + board[7];
				break;
			case(4): 
				winLine = board[2] + board[5] + board[8];
				break;
			case(5): 
				winLine = board[2] + board[4] + board[6];
				break;
			case(6): 
				winLine = board[3] + board[4] + board[5];
				break;
			case(7): 
				winLine = board[6] + board[7] + board[8];
				break;
			}
			if(winLine.equals("XXX")) {
				return "X"; //X wins
			} else if(winLine.equals("OOO")) {
				return "O"; //O wins
			} 
		}
		return null; //no winners
	}
	
	//if there is a win a whole series of things have to be done
	private void win(String letter) {
		for(int i = 0; i < button.length; i++) {
			button[i].setEnabled(false); //disable all buttons
		}
		if(letter.equals("X")) {
			xWins++;
			
		} else {
			oWins++;
		}
		score.setText("X: " + xWins + "    O: " + oWins);
		gameResult.setText(letter + " wins!");
		newGameButton.setVisible(true);
	}
	
	//if there is a draw a similar series of things to win have to be done
	private void draw() {
		gameResult.setText("No one wins!");
		newGameButton.setVisible(true);
	}
	
	//destroy the old board and make a new one (reset)
	private void newGame() {
		frameLocation = frame.getLocation(); //sets the location for when the frame respawns
		frame.dispose(); //deletes the frame
		for(int i = 0; i < board.length; i++) {
			board[i] = null; //resets the array
		}
		makeWindow(); //makes the new window
	}
	
}




/* Example output: 
 * I can't really make an example output using text...
*/