/*
 * author:technomacx
 * */
package Notakto;

import java.util.List;
import java.util.Scanner;

public class Game2 {
	char computer = 'x';
    char human = 'x';
    int row,col;
    Board2 board = new Board2();
    static int level = 3;

    public void setMap(){
        Scanner s = new Scanner(System.in);
        System.out.println("#r:");
        row = s.nextInt();
        
        System.out.println("#c:");        
        col = s.nextInt();
    	}
    
    public void play() {
        System.out.println(board);
        while (true) {

         
/*        	computerPlay();
            System.out.println("_____Computer Turn______");
            System.out.println(board);
            if (board.isWin(computer)) {
                System.out.println("I win :D I'm way smarter than you, you stupid human!");
                break;
            }
            if (board.isWithdrow()) {
                System.out.println("I guess we are even -_-");
                break;
            }
*/
            
             humanPlay();
            System.out.println(board);
            if (board.isLose(human)) {
                System.out.println("You Win I lose .... I don't like you any more :'(");
                break;
            }
            if (board.isWithdrow()) {
                System.out.println("I guess we are even -_-");
                break;
            }
        
 
              computerPlay();
            System.out.println("_____Computer Turn______");
            System.out.println(board);
            if (board.isLose(computer)) {
                System.out.println("I win :D I'm way smarter than you, you stupid human!");
                break;
            }
            if (board.isWithdrow()) {
                System.out.println("I guess we are even -_-");
                break;
            }
      
        }
        
    }

    //         ************** YOUR CODE HERE ************   
   
    private void computerPlay() {
        if(!board.isFinished())
        {
            List<Board2> nextBoards=board.getPussibleNextBoards(computer);
            int max=Integer.MIN_VALUE;
            int alpha=Integer.MIN_VALUE;
            int beta=Integer.MAX_VALUE;
            for(Board2 nextBoard : nextBoards){
            /*	
            	 System.out.println("\n------------------");
            	System.out.println(nextBoard);
            	System.out.println("");
            	*/
            	int minMax = minmax(nextBoard,alpha,beta,20);
            	if(max <= minMax){
            		max = minMax;
                    board=nextBoard;
                    
                }
                
            }
            
        }
    }

    private void humanPlay() {
        Scanner s = new Scanner(System.in);
        int row;
        while (true) {
        while (true) {
            System.out.print("Enter row: ");
            row = s.nextInt();
            System.out.println();
            if ((row > 0) && (row < 5)) {
                break;
            }
        }
        int col;
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            System.out.println();
            if ((col > 0) && (col < 5)) {
                break;
            }
        }
        
        if(board.space(row-1, col-1)==' ') {
        	board.play(human, row - 1, col - 1);
        	break;
        }
    }
        }
    public int maxmin(Board2 b,int alpha,int beta,int lev)
    {
          if(b.isFinished()||lev==0) {
        	/*
        	  System.out.println("\n------------------");
       	 	System.out.println(b);
       	 	System.out.println("levFmax="+lev+"\neval\n"+b.evaluate(computer,lev));
            */
            return b.evaluate(computer,lev);
            
            }
        else
        {
            List<Board2>nextBoards=b.getPussibleNextBoards(computer);
            int max=Integer.MIN_VALUE;
            for(Board2 nextBoard:nextBoards)
            {
            /*	System.out.println("\n------------------");
         	 	System.out.println(nextBoard);
              	 System.out.println("lev="+lev+"\neval\n"+nextBoard.evaluate(computer,lev));
         	 */	
                int minmax=minmax(nextBoard,alpha,beta,lev-1);
                alpha=Math.max(alpha, minmax);
                
         	 
                if(alpha>=beta) {
                	break;
                	}
            }
         return alpha;
        }   
    }
    public int minmax(Board2 b,int alpha,int beta,int lev)
    {
          if(b.isFinished()||lev==0) {
         	 /*	System.out.println("\n------------------");
         	 	System.out.println(b);
              	 System.out.println("levFmin="+lev+"\neval\n"+b.evaluate(computer,lev));
        	  */
        	  return b.evaluate(computer,lev);
            }
        else
        {
            List<Board2>nextBoards=b.getPussibleNextBoards(human);
            int min=Integer.MAX_VALUE;
            for(Board2 nextBoard:nextBoards)
            {
                /*
          	 	System.out.println("\n------------------");
          	 	System.out.println(nextBoard);
          	 	System.out.println("lev="+lev+"\neval\n"+nextBoard.evaluate(computer,lev));
          	 	*/
                int minmax=maxmin(nextBoard,alpha,beta,lev-1);
                 beta=Math.min(beta, minmax);                
	     
                 
                 if(alpha>=beta) {

                	 break;
                    }
            }
               return beta;
        }
    }
    

    public static void main(String[] args) {
        Game2 g = new Game2();
        //g.setMap();
        g.play();
    }
}
