import java.util.*;

public class TicTacToe {
    Random random;
    Scanner scanner;
    boolean humanFirstMove;
    ArrayList<Integer> humanPositions = new ArrayList<Integer>();
    ArrayList<Integer> computerPositions = new ArrayList<Integer>();
    char[][] board = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };
    public TicTacToe() {
        scanner = new Scanner(System.in);
        random = new Random();
        int randomNum = random.nextInt(2);
        if(randomNum == 0) {
            humanFirstMove = true;
        } else {
            humanFirstMove = false;
        }
//        System.out.println(randomNum);

        gamePlay();

    }

    void gamePlay() {
        boolean quit = true;
        while (quit) {
            printBoard();
            if(humanFirstMove) {
                System.out.println("Enter a number btw (1 - 9) to place piece");
                int humanInput = scanner.nextInt();
                while(humanPositions.contains(humanInput) || computerPositions.contains(humanInput)) {
                    System.out.println("Enter a number that has not been taken!");
                    humanInput = scanner.nextInt();
                }
                placePiece("human", humanInput);
                boolean humanIsWinner = checkWinner("human");
                humanFirstMove = false;
                System.out.println(humanFirstMove);
                if(humanIsWinner) {
                    quit = false;
                    System.out.println("You have won this round, Try again ? (Y / N)");

                }
            } else {
                System.out.println("computer turn");
                int computerInput = random.nextInt(9) + 1;
                while(humanPositions.contains(computerInput) || computerPositions.contains(computerInput)) {
                   computerInput = random.nextInt(9) + 1;
                }
                placePiece("computer", computerInput);
                boolean computerIsWinner = checkWinner("computer");
                if(computerIsWinner) {
                    quit = false;
                    System.out.println("Oops! Computer won this round, try again?");
                }

                humanFirstMove = true;
            }
        }
    }

    private boolean checkWinner(String player) {

        List<Integer> firstRow = Arrays.asList(1, 2, 3);
        List<Integer> secondRow = Arrays.asList(4,5, 6);
        List<Integer> thirdRow = Arrays.asList(7, 8, 9);
        List<Integer> firstCol = Arrays.asList(1, 4, 7);
        List<Integer> secondCol = Arrays.asList(2, 5, 8);
        List<Integer> thirdCol = Arrays.asList(3,6, 9);
        List<Integer> firstDiag = Arrays.asList(1, 5, 9);
        List<Integer> secondDiag = Arrays.asList(3, 5, 7);

        ArrayList<List<Integer>> allResult = new ArrayList<List<Integer>>();
        allResult.add(firstRow);
        allResult.add(secondRow);
        allResult.add(thirdRow);
        allResult.add(firstCol);
        allResult.add(secondCol);
        allResult.add(thirdCol);
        allResult.add(firstDiag);
        allResult.add(secondDiag);

        if(player == "human") {
            for(List<Integer> arr : allResult) {
                if(humanPositions.containsAll(arr)) {
                    return true;
                }
            }
        } else if(player == "computer") {
            for(List<Integer> arr : allResult) {
                if(computerPositions.containsAll(arr)) {
                    return true;
                }
            }
        }


     return false;
    }

    private void placePiece(String player, int pos) {
        char symbol = ' ';
        if(player == "human") {
            symbol = 'X';
            humanPositions.add(pos);
        } else if(player == "computer") {
            symbol = 'O';
            computerPositions.add(pos);
        }

        switch(pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                System.out.println("Invalid input");
        }
        /**
         * selects who starts at random and a symbol is assigned to each
         * if human is playing a prompts in the command line collects human input and board is updated
         * if it is the computer turn, the computer position is selected ar random and board is updated
         * the input to the game will be a number 1 - 9
         * depending on the number, it will map to a position on the board and the board is updated to the players symbol
         * after each side plays, a check is carried out to determine if there is a winner
         * all the piece plays by each side is kept together and all the winning combinations is checked against it
         *
         * */
    }

    private void printBoard() {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
