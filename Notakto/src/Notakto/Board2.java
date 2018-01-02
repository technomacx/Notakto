/*
 * author:technomacx
 * */
package Notakto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board2 {
	 private char[][] grid  =  {{' ', ' ', ' ',' '}, {' ', ' ', ' ',' '}, {' ', ' ', ' ',' '}};
	    private int fills,row,col;
	    
	    public Board2() {
	    	System.out.println("board2");            
	        fills = 0;
	        this.row=3;
	        this.col=3;
	    }
	    
	    public Board2(int row,int col) {
	    	fills = 0;
	    	this.row=row;
	        this.col=col;
	    	grid = new char[row][col];
	    	for (char[] rows: grid)
		        Arrays.fill(rows, ' ');
	    }
	    
	    public Board2(Board2 board) {
	    	System.out.println("copy board2");
	    	
	        for (int i = 0; i < 3; i++) {
	            System.arraycopy(board.grid[i], 0, grid[i], 0, 3);
	        }
	        this.fills = board.fills;
	    }
	    
	    public void printBoard() {
	    	for(int i=0;i<row;i++) {
	    		for(int j=0;j<col;j++) {
	    			System.out.print(grid[i][j]);
	    		}
	    		System.out.println();
	    	}
	    }

	    public List<Board2> getPussibleNextBoards(char nextPlayer) {
	        List<Board2> nextBoards = new LinkedList<>();
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                if (grid[i][j] == ' ') {
	                    Board2 nextBoard = new Board2(this);
	                    nextBoard.play(nextPlayer, i, j);
	                    nextBoards.add(nextBoard);
	                }
	            }
	        }
	        return nextBoards;
	    }

	    public void play(char player, int x, int y) {
	        if (grid[x][y] == ' '){
	            grid[x][y] = player;
	            fills++;
	        }
	    }

	   
	    public int evaluate(char player,int lev) {
	        int eval = 0;
	        if (isLose(player)) {
	        	if((lev%2)==0) {
	        		return Integer.MIN_VALUE;
	        	}if((lev%2)==1) {
					return Integer.MAX_VALUE;
					}
	        }
	        	/*
	           if (isWin(otherPlayer(player)))
	           return Integer.MAX_VALUE;
	            */
	        //check columns
	        for (int i = 0; i < 3; i++) {
	            boolean completeCol = false;
	            for (int j = 0; j < 3; j++) {
	                char val = grid[i][j];
	                //if all row is for other player
	                if (!((val == 'x') || (val == ' '))) {
	                    completeCol = true;
	                    break;
	                }
	            }
	            if (!completeCol) {
	                eval++;
	            }
	        }

	        //check rows
	        for (int i = 0; i < 3; i++) {
	            boolean completeRow = false;
	            for (int j = 0; j < 3; j++) {
	                char val = grid[j][i];
	                if (!((val == 'x') || (val == ' '))) {
	                    completeRow = true;
	                    break;
	                }
	            }
	            if (!completeRow) {
	                eval++;
	            }
	        }

	        //check diagonal 1
	        boolean completeD1 = false;
	        for (int i = 0; i < 3; i++) {
	            char val = grid[i][i];
	            if (!((val == 'x') || (val == ' '))) {
	                completeD1 = true;
	                break;
	            }
	        }
	        if (!completeD1) {
	            eval++;
	        }

	        //check diagonal 1
	        boolean completeD2 = false;
	        for (int i = 0; i < 3; i++) {
	            char val = grid[i][3 - i];
	            if (!((val == 'x') || (val == ' '))) {
	                completeD2 = true;
	                break;
	            }
	        }
	        if (!completeD2) {
	            eval++;
	        }

	        return eval;
	    }
	    
	    public char space(int row,int col) {
	    	return grid[row][col];
	    }
	    
	    public boolean isWithdrow(){
	        return (fills == 9);
	    }

	    public boolean isLose(char player) {
	        //check columns
	        for (int i = 0; i < 3; i++) {
	            boolean completeCol = true;
	            for (int j = 0; j < 3; j++) {
	                char val = grid[i][j];
	                if (!(val == player)) {
	                    completeCol = false;
	                    break;
	                }
	            }
	            if (completeCol) {
	                return true;
	            }
	        }

	        //check rows
	        for (int i = 0; i < 3; i++) {
	            boolean completeRow = true;
	            for (int j = 0; j < 3; j++) {
	                char val = grid[j][i];
	                if (!(val == player)) {
	                    completeRow = false;
	                    break;
	                }
	            }
	            if (completeRow) {
	                return true;
	            }
	        }

	        //check diagonal 1
	        boolean completeD1 = true;
	        for (int i = 0; i < 3; i++) {
	            char val = grid[i][i];
	            if (!(val == player)) {
	                completeD1 = false;
	                break;
	            }
	        }
	        if (completeD1) {
	            return true;
	        }

	        //check diagonal 1
	        boolean completeD2 = true;
	        for (int i = 0; i < 3; i++) {
	            char val = grid[i][2 - i];
	            if (!(val == player)) {
	                completeD2 = false;
	                break;
	            }
	        }
	        if (completeD2) {
	            return true;
	        }

	        return false;
	    }

	    public boolean isFinished() {
	        return (isLose('x'));
	    }
	    

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                sb.append(grid[i][j]);
	                sb.append(" | ");
	            }
	            sb.delete(sb.length() - 2, sb.length() - 1);
	            sb.append('\n');
	        }
	        return sb.toString();
	    }
}
