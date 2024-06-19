import java.util.Scanner;

import javax.swing.SwingUtilities;

public class MineSweeper {

	public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
			Grid board = new Grid("medium");
			GameHelper helper = new GameHelper();
			MineFrame grid = new MineFrame(board);
			
			board.setBoard();
			//helper.printArray(board.getBoard());

    	});
    }
}
