
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.stream;

public class Main {

    private static String[][] board = new String[9][1];

    public static void main(String[] args) {
        boardLoader();

        out.println("""
                SELECT GAME TYPE:
                1) 1vs1
                2) Human vs Bot""");
        int gameType = new Scanner(in).nextInt();

        if(gameType == 1) {
            int playerName = 0;
            while (true) {
                if (playerName == 0) {
                    if(playerMove("X", gameType).equals(true)){
                        break;
                    }
                    playerName++;
                } else {
                    if(playerMove("O", gameType).equals(true)){
                        break;
                    }
                    playerName = 0;
                }
            }
        } else if (gameType == 2) {
            int playerName = 0;
            while (true) {
                if (playerName == 0) {
                    if(playerMove("X", gameType).equals(true)){
                        break;
                    }
                    playerName++;
                } else {
                    if(playerMove("O", gameType).equals(true)){
                        break;
                    }
                    playerName = 0;
                }
            }
        }
    }

    private static void boardLoader(){
        board = stream(board)
                .map(fields -> stream(fields)
                        .map(field -> " ")
                        .toArray(String[]::new))
                .toArray(String[][]::new);
        out.println("!!!GAME LOADED!!!" + "\n");
    }

    private static void printBoard(){
        AtomicInteger iteration = new AtomicInteger(0);

        stream(board)
                .forEach(fields -> {
            if(iteration.get() == 2){
                out.println(deepToString(fields));
                iteration.set(-1);
            } else {
                out.print(deepToString(fields));
            }
            iteration.incrementAndGet();
        });
    }

    private static Boolean playerMove(String playerName, Integer gameType){
        if(gameType == 1) {
            out.println("Player's move " + playerName);
            printBoard();

            out.println("Select a cell:");
            int cell = new Scanner(in).nextInt() - 1;
            if (!board[cell][0].equals(" ")) {
                out.println("This cell is already occupied!");
                playerMove(playerName, gameType);
                return false;
            } else {
                board[cell][0] = playerName;
                return checkWin(cell, playerName);
            }
        } else if (gameType == 2) {
            if(playerName.equals("X")) {
                out.println("Player's move " + playerName);
                printBoard();

                out.println("Select a cell:");
                int cell = new Scanner(in).nextInt() - 1;
                if (!board[cell][0].equals(" ")) {
                    out.println("This cell is already occupied!");
                    playerMove(playerName, gameType);
                    return false;
                } else {
                    board[cell][0] = playerName;
                    return checkWin(cell, playerName);
                }
            } else {
                out.println("Player's move " + playerName);
                printBoard();

                out.println("Select a cell:");
                int cell = Bot.cellGenerate();
                if (!board[cell][0].equals(" ")) {
                    out.println("This cell is already occupied!");
                    playerMove(playerName, gameType);
                    return false;
                } else {
                    board[cell][0] = playerName;
                    return checkWin(cell, playerName);
                }
            }
        } else {
            return null;
        }
    }


    private static Boolean checkWin(Integer cell, String playerName){
        boolean winStatus = false;

        if(cell == 2 || cell == 5 || cell == 8){
            for (int i = 1; i <= 2; i++){
                if(!board[cell - i][0].equals(playerName)){
                    winStatus = false;
                    break;
                }
                winStatus = true;
            }
            if(!winStatus && cell == 2){
                if((board[5][0].equals(playerName) && board[8][0].equals(playerName))
                        || (board[4][0].equals(playerName) && board[6][0].equals(playerName))){
                    winStatus = true;
                }
            } else if (!winStatus && cell == 8){
                if((board[5][0].equals(playerName) && board[2][0].equals(playerName))
                        || (board[4][0].equals(playerName) && board[0][0].equals(playerName))){
                    winStatus = true;
                }
            }
            if(winStatus){
                out.println(playerName + " WINS!!");
            }
            return winStatus;
        } else if(cell == 0 || cell == 3 || cell == 6){
            for (int i = 1; i <= 2; i++){
                if(!board[cell + i][0].equals(playerName)){
                    winStatus = false;
                    break;
                }
                winStatus = true;
            }
            if(!winStatus && cell == 0){
                if((board[3][0].equals(playerName) && board[6][0].equals(playerName))
                        || (board[4][0].equals(playerName) && board[8][0].equals(playerName))){
                    winStatus = true;
                }
            } else if (!winStatus && cell == 6){
                if((board[3][0].equals(playerName) && board[0][0].equals(playerName))
                        || (board[4][0].equals(playerName) && board[2][0].equals(playerName))){
                    winStatus = true;
                }
            }
            if(winStatus){
                out.println(playerName + " WINS!!");
            }
            return winStatus;
        } else {
            if(cell == 1
                    && ((board[0][0].equals(playerName) && board[2][0].equals(playerName))
                    || (board[4][0].equals(playerName) && board[7][0].equals(playerName)))){
                winStatus = true;
            } else if (cell == 4 && ((board[1][0].equals(playerName) && board[7][0].equals(playerName))
                    || (board[3][0].equals(playerName) && board[5][0].equals(playerName))
                    || (board[6][0].equals(playerName) && board[2][0].equals(playerName))
                    || (board[0][0].equals(playerName) && board[8][0].equals(playerName)))){
                winStatus = true;
            } else if (cell == 7
                    && ((board[6][0].equals(playerName) && board[8][0].equals(playerName))
                    || (board[4][0].equals(playerName) && board[1][0].equals(playerName)))){
                winStatus = true;
            }
            if(winStatus){
                out.println(playerName + " WINS!!");
            }
            return winStatus;
        }

    }
}
