import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Math;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Grid extends JPanel {
	

	private final int IMG_NUM = 18;
	private final int HEIGHT_OFFSET = 60;
	private String[] board_type = {"hidden","current"};

	private int COLS;
	private int ROWS;
	private int SQ_SIZE;
    	private Image[] images;
	private int[][] hiddenBoard;
	private int[][] currentBoard;
	private int size;
	private String difficulty;
	private int minesNum;
	private int flags;
	private static boolean gameover = false;
	private static boolean won = false;
	private final int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
		};
	
	
	GameHelper helper = new GameHelper();

    public Grid(String dif) {
    	setBackground(new Color(185,185,185));
        // Load the image
        loadImage();
		setDifficulty(dif);
		
		if (difficulty.equals("easy")) {
			setMines(12);
			setFlags(12);
			setSquareSize(53);
			setSiz(9);
		}
		else if (difficulty.equals("medium")) {
			setMines(45);
			setFlags(45);
			setSquareSize(30);
			setSiz(16);
		}
		else if (difficulty.equals("hard")) {
			setMines(100);
			setFlags(100);
			setSquareSize(16);
			setSiz(30);
		}
		setBoard();
		setCurrentBoard();
		
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	

                int mouseX = e.getX();
                int mouseY = e.getY();

                int clickedRow = (mouseY - HEIGHT_OFFSET) / SQ_SIZE;
                int clickedCol = mouseX / SQ_SIZE;
                    
                int openedSquares = 0;
                    
                if (e.getButton() == MouseEvent.BUTTON1) {
                    	
	                if (mouseX>0 && mouseX<(SQ_SIZE*size) && mouseY>0 && mouseY<60) {
	                    if (mouseX>((SQ_SIZE*size)/2)-25 && mouseX<((SQ_SIZE*size)/2)+25 && mouseY>5 && mouseY<55) {
	                        gameover=false;
	                        won=false;
	                        setBoard();
	                        setCurrentBoard();
	                    }
	                 }
	                 else {
	                	 if (!gameover) {
		                     if (currentBoard[clickedRow][clickedCol] != 11) {
	                            	
		                    	 if (hiddenBoard[clickedRow][clickedCol]==0 && currentBoard[clickedRow][clickedCol]==0 && currentBoard[clickedRow][clickedCol] != 11) {
		                    		 helper.openSquares(Grid.this,clickedRow,clickedCol);
		                         }
		                            	
		                         for (int i = 0; i < currentBoard.length; i++) {
		                        	 for (int j = 0; j < currentBoard[i].length; j++) {
		                            	if (currentBoard[i][j]!=0 && currentBoard[i][j]!=11) {
		                            		openedSquares++;
		                            	}
		                            }
		                         }
		                            	
		                         if (openedSquares==Math.pow(size, 2)-minesNum) {
		                        	 gameover = true;
		                        	 won=true;
		                         }
		
		                                
		                         if (hiddenBoard[clickedRow][clickedCol]==1) {
		                        	 for (int i = 0; i < ROWS; i++) {
		                        		 for (int j = 0; j < COLS; j++) {
		                        			 if (hiddenBoard[i][j] == 1 && hiddenBoard[i][j] != currentBoard[clickedRow][clickedCol]) {
		                        				if (currentBoard[i][j] != 11) {
		                        					 currentBoard[i][j] = 9;
		                                        }
		                                     }
		                        			 else if(hiddenBoard[i][j]==0 && currentBoard[i][j]==11) {
		                        				 currentBoard[i][j] = 13;
		                        			 }
		                                 }
		                              }
		                              currentBoard[clickedRow][clickedCol] = 10;
		                              gameover = true;
		                         }
		                     }
		                  }
	                  }

                  }
                    
                    
                  else if (e.getButton() == MouseEvent.BUTTON3) {
                	  if (!gameover) {
                    	  if (currentBoard[clickedRow][clickedCol] == 0) {
                    		  currentBoard[clickedRow][clickedCol] = 11;
                          }
                          else if (currentBoard[clickedRow][clickedCol] == 11) {
                        	  currentBoard[clickedRow][clickedCol] = 0;
                          }
                	  }
                  }

            	repaint();
            }
    	});
    }

    private void loadImage() {
        // Load the image from file
    	images = new Image[IMG_NUM];
  
    	for (int i = 0; i < IMG_NUM; i++) {
    	        ImageIcon icon = new ImageIcon("images/" + i + ".png");
    	        images[i] = icon.getImage();
    	}
    }
    
	public void setBoard() {
		int[][] brd = helper.fillArray(getSiz(),getMines(),board_type[0]);
		this.hiddenBoard = brd;
	}
	
	public int[][] getBoard(){
		return hiddenBoard;
	}
	
	public void setCurrentBoard() {
		int[][] brd = helper.fillArray(getSiz(),getMines(),board_type[1]);
		this.currentBoard = brd;
	}
	
	public int[][] getCurrentBoard(){
		return currentBoard;
	}
	
	public int[][] getDirections(){
		return directions;
	}
	
	public void setSiz(int s) {
		this.size = s;
		this.COLS = s;
		this.ROWS = s;
	}
	
	public int getSiz() {
		return size;
	}
	
	public void setFlags(int f) {
		this.flags = f;
	}
	
	public int getFlags() {
		return flags;
	}
	
	public void setMines(int mines) {
		minesNum = mines;
	}
	
	public int getMines() {
		return minesNum;
	}
	
	public void setDifficulty(String dif) {

		difficulty = dif;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public void setSquareSize(int size) {
		if (this.getDifficulty().equals("hard")) {
			SQ_SIZE = size+3;
		}
		else {
			SQ_SIZE = size;
		}
		
	}
	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < ROWS; i++) {
        	for (int j = 0; j < COLS; j++) {
        		for ( int k = 0; k < IMG_NUM; k++) {
            		if (currentBoard[i][j] == k) {
            			g.drawImage(images[k], 0 + (j * SQ_SIZE), 0 + (i * SQ_SIZE) + HEIGHT_OFFSET,SQ_SIZE,SQ_SIZE, this);
            		}
        		}

        	}
        }
        
    	if (gameover==false) {
    		g.drawImage(images[14],((SQ_SIZE*size)/2)-25,5,50,50, this);
    	}
    	else {
    		if (won) {
    			g.drawImage(images[16],((SQ_SIZE*size)/2)-25,5,50,50, this);
    		}
    		else {
    			g.drawImage(images[15],((SQ_SIZE*size)/2)-25,5,50,50, this);
    		}
    	}
         
    }
}


