import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyActionListener implements ActionListener {
	
    private String difficulty;
    private Grid board;
    private MineFrame frame;

    public DifficultyActionListener(MineFrame frame, Grid board,String difficulty) {
        this.difficulty = difficulty;
        this.board=board;
        this.frame=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	board.setDifficulty(difficulty);
    	board.setGame(difficulty);
    	frame.resetSize(difficulty);
        board.setGameover(false);
	board.setWon(false);
    }
}
