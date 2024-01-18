import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static String[][] gameBoard = new String[3][3];
    private static String[] players = {"X", "O"};
    private static boolean cont = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        clearBoard();

        while (cont) {
            for (int i = 0; i < 2; i++) {
                doPlayerMove(scanner, players[i]);
                displayBoard();

                if (isWin(players[i])) {
                    PrettyHeader.printHeader(String.format("Player %s won!", players[i]));
                    displayBoard();
                    replayOrEnd(scanner);
                } else if (isTie()) {
                    PrettyHeader.printHeader(String.format("It was a tie!", players[i]));
                    displayBoard();
                    replayOrEnd(scanner);
                }
            }
        }
    }

    private static void replayOrEnd(Scanner in) {
        if (!InputHelper.getYNConfirm(in, "Would you like to play again? [Y/N]")) {
            cont = false;
        } else {
            clearBoard();
        }
    }
    private static void doPlayerMove(Scanner in, String player) {
        boolean isValid = false;

        PrettyHeader.printHeader(String.format("Player %s's Turn:", player));
        do {
            int r = InputHelper.getRangedInt(in, "Enter your move row [1 - 3]\n", 1, 3) - 1;
            int c = InputHelper.getRangedInt(in, "Enter your move column [1 - 3]\n", 1, 3) - 1;

            if (isValidMove(r, c)) {
                gameBoard[r][c] = player;
                isValid = true;
            } else {
                System.out.println("That spot is already taken.");
            }
        } while (!isValid);
    }

    private static void clearBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                gameBoard[r][c] = "-";
            }
        }
    }

    private static void displayBoard() {
        for (int r = 0; r < 3; r++) {
            System.out.printf("%s   %s   %s\n", gameBoard[r][0], gameBoard[r][1], gameBoard[r][2]);
        }
    }

    private static boolean isValidMove(int r, int c) {
        return gameBoard[r][c].equals("-");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonolWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r <= 2; r++) {
            if (gameBoard[r][0].equals(player) && gameBoard[r][1].equals(player) && gameBoard[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c <= 2; c++) {
            if (gameBoard[0][c].equals(player) && gameBoard[1][c].equals(player) && gameBoard[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonolWin(String player) {
        boolean diagonolOne = gameBoard[0][0].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][2].equals(player);
        boolean diagonalTwo = gameBoard[0][2].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][0].equals(player);
        return diagonolOne || diagonalTwo;
    }

    private static boolean isTie() {
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                if (gameBoard[r][c].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
}