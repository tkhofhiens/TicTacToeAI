/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	static String player = "X";
	static String ai = "O"; 
	
	static void printBoard(String board[][]){
			for (int j=0; j<3; j++){
				for (int i = 0; i<3; i++){
		           System.out.print("| "+board[j][i]);
				}
				System.out.println("|");
			}
	}
	
	static Boolean movesLeft(String board[][]) 
	{ 
	    for (int j=0; j<3; j++){
            for (int i = 0; i<3; i++){
	            if (board[i][j] == " "){
	                return true; 
	            }
            }
        }
	    return false; 
	} 
	
	static Boolean checkWin(String board[][])
	{
        Boolean win = false;
        int value = 0;
    	// Horizontal: [1, 2, 3] [4, 5, 6] [7, 8, 9]
        for (int j=0; j<3; j++){ 
            // int i=0;
            // System.out.println("checking horizontal "+board[j][0] +" "+board[j][1]+" "+board[j][2]);
            if (board[j][0]==board[j][1] && board[j][0]==board[j][2]){
                System.out.println("horizontal "+board[j][0] +" "+board[j][1]+" "+board[j][2]);
                win = true;
                // System.out.println("horizontal Win = " + win);
                if(board[j][0]==player){
                    value = 10;
                    System.out.println("value "+value+" | "+player+" won");
                }else if(board[j][0]==ai){
                    value = -10;
                    System.out.println("value "+value+" | "+ai+" won");
                }
            }	
    	}
        // Vertical: [1, 4, 7] [2, 5, 8] [3, 6, 9]
            for (int i=0; i<3; i++){
                // System.out.println("checking vertical "+board[0][i] +" "+board[1][i]+" "+board[2][i]);
                if (board[0][i]==board[1][i] && board[0][i]==board[2][i]){
                    win = true;
                    // System.out.println("virticle win = " + win);
                    System.out.println("vertical "+board[0][i] +" "+board[1][i]+" "+board[2][i]);                    win = true;
                    if(board[0][i]==player){
                        value = 10;
                        System.out.println("value "+value+" | "+player+" won");
                    }else if(board[0][i]==ai){
                        value = -10;
                        System.out.println("value "+value+" | "+ai+" won");
                    }
                    
                }	
            }
        // Diagonal: [1, 5, 9] [3, 5, 7]
            if(board[0][0] == board[1][1]  && board[0][0] == board[2][2]){
                System.out.println("diagonal "+board[0][0] +" "+board[1][1]+" "+board[2][2]);
                win = true;
                if(board[1][1]==player){
                    value = 10;
                    System.out.println("value "+value+" | "+player+" won");
                }else if(board[1][1]==ai){
                    value = -10;
                    System.out.println("value "+value+" | "+ai+" won");
                }
            }
            if(board[0][2] == board[1][1]  && board[0][2] == board[2][0]){
                System.out.println("diagonal "+board[0][2] +" "+board[1][1]+" "+board[2][0]);
                win = true;
                if(board[1][1]==player){
                    value = 10;
                    System.out.println("value "+value+" | "+player+" won");
                }else if(board[1][1]==ai){
                    value = -10;
                    System.out.println("value "+value+" | "+ai+" won");
                }
            }

        System.out.println(" status = " + value);
    	return win;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
        System.out.println("TicTacToe \n");
        // String[][] board = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};

        String[][] board = {{"O","X","X"},{"O","O","O"},{"X"," ","X"}};
		// String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
		// System.out.println(board[4]);
        printBoard(board);
        // checkWin(board);
        Boolean moves = movesLeft(board);
        Boolean winner = checkWin(board);
        System.out.println("\nMoves Left? "+ moves);
        System.out.println("Winner? "+ winner);
	}
}