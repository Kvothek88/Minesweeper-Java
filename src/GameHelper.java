
public class GameHelper {

	public int[][] fillArray(int size, int num_of_mines, String type)  {
		
		int[][] arr = new int[size][size];
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				arr[i][j] = 0;
			}
		}
		
		if (type=="hidden") {
			while (num_of_mines>0) {
				int x = (int) (Math.random() * size);
				int y = (int) (Math.random() * size);
				
				if (arr[x][y]==0) {
					arr[x][y]=1;
					num_of_mines--;
				}
			}			
		}

		return arr;
	}
	

	// This method is used for debugging
	public void printArray(int arr[][]) {

		int size = arr.length;
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.print(arr[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int searchAdjacentMines(Grid grid,int row, int col) {
		
		int r,c,mines_num = 0;
		
		for (int[] dir : grid.getDirections()) {
			r = row + dir[0];
			c = col + dir[1];
			if (r>=0 && r<grid.getSiz() && c>=0 && c<grid.getSiz() && grid.getBoard()[r][c]==1) {
				mines_num++;
			}
		}
		return mines_num;
	}
	
	public void openSquares(Grid grid,int row,int col) {
		
		if (row<0 || row>=grid.getSiz() || col<0 || col>=grid.getSiz() || grid.getCurrentBoard()[row][col]!=0) {
			return;
		}
		
		int adjacent_mines = searchAdjacentMines(grid,row,col);
		if (adjacent_mines==0) {
			grid.getCurrentBoard()[row][col] = 12;
		}
		else {
			grid.getCurrentBoard()[row][col] = adjacent_mines;
		}
		
		
		if (adjacent_mines == 0) {
			for (int[] dir : grid.getDirections()) {
				openSquares(grid,row+dir[0],col+dir[1]);
			}
		}
		
	}
	
}



