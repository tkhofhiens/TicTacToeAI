/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class tttgame {
    static class Move {
        int row, col;
    };

    static String ai = "O";
    static String human = "X";
    // initialize player to go first
    static String turn = human;

    static void printBoard(String board[][]) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.print("| " + board[j][i]);
            }
            System.out.println("|");
        }
    }

    static Boolean movesLeft(String board[][]) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == " ") {
                    return true;
                }
            }
        }
        return false;
    }

    // checks to see if a player has won the game
    // rturns + int for ai win or - int for human win
    static int checkWin(String board[][]) {
        // Horizontal: [1, 2, 3] [4, 5, 6] [7, 8, 9]
        for (int j = 0; j < 3; j++) {
            // System.out.println("checking horizontal "+board[j][0] +" "+board[j][1]+"
            // "+board[j][2]);
            if (board[j][0] == board[j][1] && board[j][0] == board[j][2]) {
                // System.out.println("horizontal " + board[j][0] + " " + board[j][1] + " " +
                // board[j][2]);
                // System.out.println("horizontal Win = " + win);
                if (board[j][0] == ai) {
                    return +10;
                    // System.out.println("value " + value + " | " + player + " won");
                } else if (board[j][0] == human) {
                    return -10;
                    // System.out.println("value " + value + " | " + ai + " won");
                }
            }
        }
        // Vertical: [1, 4, 7] [2, 5, 8] [3, 6, 9]
        for (int i = 0; i < 3; i++) {
            // System.out.println("checking vertical "+board[0][i] +" "+board[1][i]+"
            // "+board[2][i]);
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                // System.out.println("virticle win = " + win);
                // System.out.println("vertical " + board[0][i] + " " + board[1][i] + " " +
                // board[2][i]);
                if (board[0][i] == ai) {
                    return +10;
                    // System.out.println("value " + value + " | " + player + " won");
                } else if (board[0][i] == human) {
                    return -10;
                    // System.out.println("value " + value + " | " + ai + " won");
                }
            }
        }
        // Diagonal: [1, 5, 9] [3, 5, 7]
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            // System.out.println("diagonal " + board[0][0] + " " + board[1][1] + " " +
            // board[2][2]);
            if (board[1][1] == ai) {
                return +10;
                // System.out.println("value " + value + " | " + player + " won");
            } else if (board[1][1] == human) {
                return -10;
                // System.out.println("value " + value + " | " + ai + " won");
            }
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            // System.out.println("diagonal " + board[0][2] + " " + board[1][1] + " " +
            // board[2][0]);
            if (board[1][1] == ai) {
                return +10;
                // System.out.println("value " + value + " | " + player + " won");
            } else if (board[1][1] == human) {
                return -10;
                // System.out.println("value " + value + " | " + ai + " won");
            }
        }

        return 0;
    }

    static int minimax(String board[][], int depth, Boolean isMax) {
        int score = checkWin(board);

        // If Max won
        if (score == 10)
            return score;

        // If Min won
        if (score == -10)
            return score;

        // no winner or tie game
        if (movesLeft(board) == false)
            return 0;

        //maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == " ") {
                        // Make the move
                        board[i][j] = ai;
                        // Call minimax recursively and choose the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        // Undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }

        //minimizer's move
        else {
            int best = 1000;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == " ") {
                        // Make the move
                        board[i][j] = human;
                        // Call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        // Undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }
    }

    // runs the minimax function
    // return the best possible move for the ai
    static Move findBestMove(String board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == " ") {
                    // Make the move
                    board[i][j] = turn;
                    // compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false);
                    // Undo the move
                    board[i][j] = " ";
                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("\nThe value of the best Move " + "is : %d\n", bestVal);
        System.out.printf("ROW: %d COL: %d\n", bestMove.row, bestMove.col);

        return bestMove;
    }

    static void nextTurn() {
        if (turn == human) {
            turn = ai;
        } else {
            turn = human;
        }
    }

    static Move intToMove(int user) {
        Move userMove = new Move();
        if (user == 1) {
            userMove.col = 0;
            userMove.row = 0;
        }
        if (user == 2) {
            userMove.col = 1;
            userMove.row = 0;
        }
        if (user == 3) {
            userMove.col = 2;
            userMove.row = 0;
        }
        if (user == 4) {
            userMove.col = 0;
            userMove.row = 1;
        }
        if (user == 5) {
            userMove.col = 1;
            userMove.row = 1;
        }
        if (user == 6) {
            userMove.col = 2;
            userMove.row = 1;
        }
        if (user == 7) {
            userMove.col = 0;
            userMove.row = 2;
        }
        if (user == 8) {
            userMove.col = 1;
            userMove.row = 2;
        }
        if (user == 9) {
            userMove.col = 2;
            userMove.row = 2;
        }
        return userMove;
    }

    public static void main(String[] args) throws java.lang.Exception {

        Scanner in = new Scanner(System.in);
        // initialize empty game board
        System.out.println("TicTacToe \n");
        String[][] board = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };
        // Boolean moves = movesLeft(board);

        // output for playing the game in terminal
        String[][] sample = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
        System.out.println("Ready to play Tic Tac Toe?");
        printBoard(sample);
        System.out.println("X will play first.");

        int winner =0;

        while (winner == 0 && movesLeft(board)==true) {
            int numInput;

            if (turn == human) {
                // Exception handling.
                // numInput will take input from user like from 1 to 9.
                // If it is not in range from 1 to 9.
                // then it will show you an error "Invalid input."
                Move nextMove = new Move();
                System.out.println("Enter a slot number to place X in:");
                try {
                    numInput = in.nextInt();
                    if (!(numInput > 0 && numInput <= 9)) {
                        System.out.println("Invalid input; re-enter slot number:");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
                nextMove = intToMove(numInput);
                board[nextMove.row][nextMove.col] = turn;
            } else {
                // ai's turn and it will use functions to find the best move and take it's turn
                Move nextMove = new Move();
                nextMove = findBestMove(board);
                board[nextMove.row][nextMove.col] = turn;
            }
            winner = checkWin(board);
            nextTurn();
            printBoard(board);
        }

        String result = "";
        if (winner == 10) {
            result = "computer Ai won";
        }
        if (winner == -10) {
            result = "human player won";
        }
        if (winner == 0) {
            result = "no winner it's a tie";
        }

        System.out.println("Game over " + result);
    }
}
