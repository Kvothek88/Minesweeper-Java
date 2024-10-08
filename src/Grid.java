import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Math;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Grid extends JPanel {
	

	private static final int IMG_NUM = 18;
	private static final int HEIGHT_OFFSET = 60;
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
	private static final int UNOPENED = 0;
	private static final int MINE = 9;
	private static final int MINE_HIT = 10;
	private static final int FLAG = 11;
	private static final int NO_MINE = 13;
	private static final int PLAY = 14;
	private static final int LOST = 15;
	private static final int WON = 16;
	private boolean gameover = false;
	private boolean won = false;
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
		
		setGame(dif);
		
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
	                        setGame(getDifficulty());
	                    }
	                 }
	                 else {
	                	 if (!gameover) {
		                     if (currentBoard[clickedRow][clickedCol] != FLAG) {
	                            	
		                    	 if (hiddenBoard[clickedRow][clickedCol]==0 && currentBoard[clickedRow][clickedCol]==UNOPENED) {
		                    		 helper.openSquares(Grid.this,clickedRow,clickedCol);
		                         }
		                            	
		                         for (int i = 0; i < currentBoard.length; i++) {
		                        	 for (int j = 0; j < currentBoard[i].length; j++) {
		                            	if (currentBoard[i][j]!=UNOPENED && currentBoard[i][j]!=FLAG) {
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
		                        				if (currentBoard[i][j] != FLAG) {
		                        					 currentBoard[i][j] = MINE;
		                                        }
		                                     }
		                        			 else if(hiddenBoard[i][j]==0 && currentBoard[i][j]==FLAG) {
		                        				 currentBoard[i][j] = NO_MINE;
		                        			 }
		                                 }
		                              }
		                              currentBoard[clickedRow][clickedCol] = MINE_HIT;
		                              gameover = true;
		                         }
		                     }
		                  }
	                  }

                  }
                    
                    
                  else if (e.getButton() == MouseEvent.BUTTON3) {
                	  if (!gameover) {
                    	  if (currentBoard[clickedRow][clickedCol] == UNOPENED) {
                    		  currentBoard[clickedRow][clickedCol] = FLAG;
                          }
                          else if (currentBoard[clickedRow][clickedCol] == FLAG) {
                        	  currentBoard[clickedRow][clickedCol] = UNOPENED;
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
			SQ_SIZE = size + 3;
		}
		else {
			SQ_SIZE = size;
		}
		
	}
	
	public int getSquareSize() {
		return SQ_SIZE;
	}
	
	public int getHeightOffset() {
		return HEIGHT_OFFSET;
	}

	public void setGameover(boolean gameover) {
		this.gameover=gameover;
	}

	public void setWon(boolean won) {
		this.won=won;
	}
	
	public void setGame(String difficulty) {
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
			setMines(113);
			setFlags(113);
			setSquareSize(16);
			setSiz(30);
		}
		setBoard();
		setCurrentBoard();
		
		helper.printArray(hiddenBoard);
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
    		g.drawImage(images[PLAY],((SQ_SIZE*size)/2)-25,5,50,50, this);
    	}
    	else {
    		if (won) {
    			g.drawImage(images[WON],((SQ_SIZE*size)/2)-25,5,50,50, this);
    		}
    		else {
    			g.drawImage(images[LOST],((SQ_SIZE*size)/2)-25,5,50,50, this);
    		}
    	}
         
    }
}
