import java.util.Scanner;

import javax.swing.SwingUtilities;

public class MineSweeper {

	public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
			Grid board = new Grid("medium");
			GameHelper helper = new GameHelper();
			Scanner scan = new Scanner(System.in);
			MineFrame grid = new MineFrame(board);
			
			
			board.setBoard();
			//helper.printArray(board.getBoard());

    	});
    }
}