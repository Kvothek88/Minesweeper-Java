import javax.swing.JFrame;


public class MineFrame extends JFrame {
	
	MineFrame(Grid board,String dif){
		
	    setTitle("Minesweeper");
	    if(dif=="easy") {
	    	setSize(493, 576);
	    }
	    else if(dif=="medium") {
	    	setSize(496, 578);
	    }
	    else {
	    	setSize(586, 668);
	    }
	    
	    add(board);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
}
