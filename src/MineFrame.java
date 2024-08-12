import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MineFrame extends JFrame {
	
	private Grid board;
	
	MineFrame(Grid board,String dif){
		
	    this.board=board;
		
	    setTitle("Minesweeper");
	    resetSize(dif);
	    add(board);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	public void resetSize(String dif) {
	    if(dif=="easy") {
	    	setSize(493, 597);
	    }
	    else if(dif=="medium") {
	    	setSize(496, 601);
	    }
	    else {
	    	setSize(586, 692);
	    }
	}
}


