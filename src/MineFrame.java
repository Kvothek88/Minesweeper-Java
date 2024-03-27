import javax.swing.JFrame;


public class MineFrame extends JFrame {
	
	MineFrame(Grid board){
		
	    setTitle("Minesweeper");
	    setSize(496, 578);
	    
	    add(board);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
}