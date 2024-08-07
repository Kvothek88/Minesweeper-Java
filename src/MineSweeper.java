import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class MineSweeper {

	public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
			Grid board = new Grid("hard");
			GameHelper helper = new GameHelper();
			MineFrame grid = new MineFrame(board,board.getDifficulty());
			
			board.setBoard();
			//helper.printArray(board.getBoard());
			
	        JMenuBar menuBar = new JMenuBar();
	        
	        JMenu difficultyMenu = new JMenu("Difficulty");
	        menuBar.add(difficultyMenu);

	        JMenuItem easyItem = new JMenuItem("Easy");
	        JMenuItem mediumItem = new JMenuItem("Medium");
	        JMenuItem hardItem = new JMenuItem("Hard");

	        difficultyMenu.add(easyItem);
	        difficultyMenu.add(mediumItem);
	        difficultyMenu.add(hardItem);

	        easyItem.addActionListener(new DifficultyActionListener(grid,board,"easy"));
	        mediumItem.addActionListener(new DifficultyActionListener(grid,board,"medium"));
	        hardItem.addActionListener(new DifficultyActionListener(grid,board,"hard"));

	        grid.setJMenuBar(menuBar);

    	});
    }
}
