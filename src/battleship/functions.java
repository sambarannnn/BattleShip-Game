package battleship;

import java.util.Scanner;

class Ship {
    protected int size;
    protected String symbol;

    protected Ship(int size, String symbol) {
        this.size = size;
        this.symbol = symbol;
    }
}

class BattleShip{

    private final int rows = 11;
    private final int cols = 11;

    private String[][] P1_GameBoard;
    private String[][] P1_FogBoard;
    private String[][] P2_GameBoard;
    private String[][] P2_FogBoard;

    private static Ship[] Aircraft_Carrier = new Ship[2];
    private static Ship[] Battleship = new Ship[2];
    private static Ship[] Cruiser = new Ship[2];
    private static Ship[] Submarine = new Ship[2];
    private static Ship[] Destroyer = new Ship[2];

    private static boolean game_over = false;

    protected BattleShip () {
        P1_GameBoard = new String[rows][cols];
        P1_FogBoard = new String[rows][cols];
        P2_GameBoard = new String[rows][cols];
        P2_FogBoard = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    P1_GameBoard[i][j] = " ";
                    P1_FogBoard[i][j] = " ";
                    P2_GameBoard[i][j] = " ";
                    P2_FogBoard[i][j] = " ";
                }
                //~
                else if (i == 0) {
                    P1_GameBoard[i][j] = String.valueOf(j);
                    P1_FogBoard[i][j] = String.valueOf(j);
                    P2_GameBoard[i][j] = String.valueOf(j);
                    P2_FogBoard[i][j] = String.valueOf(j);
                }
                else if (j == 0) {
                    P1_GameBoard[i][j] = String.valueOf((char) ('A' + i-1));
                    P1_FogBoard[i][j] = String.valueOf((char) ('A' + i-1));
                    P2_GameBoard[i][j] = String.valueOf((char) ('A' + i-1));
                    P2_FogBoard[i][j] = String.valueOf((char) ('A' + i-1));
                }
                else {
                    P1_GameBoard[i][j] = "~";
                    P1_FogBoard[i][j] = "~";
                    P2_GameBoard[i][j] = "~";
                    P2_FogBoard[i][j] = "~";
                }
            }
        }
    }

    protected void display(String[][] GameBoard) {
        System.out.println();
        for (int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                if(i!= 0 && j!= 0 && !GameBoard[i][j].equals("~") && !GameBoard[i][j].equals("X") && !GameBoard[i][j].equals("M")) {
                    System.out.print("O ");
                } else {
                    System.out.print(GameBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    protected void input(String[][] GameBoard, int player) {
        player -= 1;
        display(GameBoard);
        Scanner s = new Scanner(System.in);
        int count = 0;
        int size;
        String input;
        boolean mistake;
        while (count < 5) {
            String ship;
            if (count == 0) {
                ship = "Aircraft Carrier";
                size = 5;
            } else if (count == 1) {
                ship = "Battleship";
                size = 4;
            } else if (count == 2) {
                ship = "Submarine";
                size = 3;
            } else if (count == 3) {
                ship = "Cruiser";
                size = 3;
            } else {
                ship = "Destroyer";
                size = 2;
            }
            System.out.println("Enter the coordinates of the " + ship + "(" + size + " cells):");
            //System.out.println(player);
            input = s.nextLine();
            String[] pos = input.split("\\s");
            int r1 = pos[0].charAt(0) - 64;
            int r2 = pos[1].charAt(0) - 64;
            int c1 = Integer.parseInt(pos[0].substring(1));
            int c2 = Integer.parseInt(pos[1].substring(1));
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String symbol;
            switch (count) {
                case 0:
                    Aircraft_Carrier[player] = new Ship(5, "A");
                    symbol = Aircraft_Carrier[player].symbol;
                    break;
                case 1:
                    Battleship[player] = new Ship(4, "B");
                    symbol = Battleship[player].symbol;
                    break;
                case 2:
                    Submarine[player] = new Ship(3, "S");
                    symbol = Submarine[player].symbol;
                    break;
                case 3:
                    Cruiser[player] = new Ship(3, "C");
                    symbol = Cruiser[player].symbol;
                    break;
                default:
                    Destroyer[player] = new Ship(2, "D");
                    symbol = Destroyer[player].symbol;
                    break;
            }

            //System.out.printf("(%d, %d) ; (%d, %d)\n", r1, c1, r2, c2);
            if (Math.abs(c2 - c1)+1 != size && Math.abs(r2 - r1)+1 != size) {
                System.out.println("Error! Wrong length of the " + ship + "! Try again:\n");
                count--;
                mistake = true;
            } else if (r1 != 10 && r1!= 1 && r2 != 1 && c1 != 1 && c2 != 1 && (!GameBoard[r1 - 1][c1].equals("~") || !GameBoard[r1 + 1][c1].equals("~"))){
                System.out.println("Error! You placed it too close to another one. Try again:\n");
                count--;
                mistake = true;
            } else if (c1 != 10 && r1!= 1 && r2 != 1 && c1 != 1 && c2 != 1 && (!GameBoard[r1][c1 - 1].equals("~") || !GameBoard[r1][c1 + 1].equals("~"))){
                System.out.println("Error! You placed it too close to another one. Try again:\n");
                count--;
                mistake = true;
            } else if (r2 != 10 && r1!= 1 && r2 != 1 && c1 != 1 && c2 != 1 && (!GameBoard[r2 - 1][c2].equals("~") || !GameBoard[r2 + 1][c2].equals("~"))){
                System.out.println("Error! You placed it too close to another one. Try again:\n");
                count--;
                mistake = true;
            } else if (c2 != 10 && r1!= 1 && r2 != 1 && c1 != 1 && c2 != 1 && (!GameBoard[r2][c2 - 1].equals("~") || !GameBoard[r2][c2 + 1].equals("~"))){
                System.out.println("Error! You placed it too close to another one. Try again:\n");
                count--;
                mistake = true;
            }else if (c2 != c1 && r2 != r1){
                System.out.println("Error! Wrong ship location! Try again:\n");
                count--;
                mistake = true;
            } else if (c1 == c2){
                mistake = false;
                do {
                    if (r1 < r2) {
                        GameBoard[r1][c1] = symbol;
                        r1++;
                    } else {
                        GameBoard[r2][c1] = symbol;
                        r2++;
                    }
                    size--;
                } while (size != 0);
            } else {
                mistake = false;
                do {
                    if (c1 < c2) {
                        GameBoard[r1][c1] = symbol;
                        c1++;
                    } else {
                        GameBoard[r1][c2] = symbol;
                        c2++;
                    }
                    size--;
                } while (size != 0);
            }
            count++;
            if(!mistake)
                display(GameBoard);
        }
    }

    protected int Battle(String[][] GameBoard, String[][] FogBoard, int player) {
        player -= 1;
        Scanner s = new Scanner(System.in);
        int p = 0;
        String input;
        //System.out.println("The game starts!");
        //display(FogBoard);
        boolean won = false;
        boolean sunk;
        //do {
            sunk = false;
            //System.out.println("Take a shot!");
            input = s.nextLine();
            int r = input.charAt(0) - 64;
            int c = Integer.parseInt(input.substring(1));
            //System.out.printf("(%d, %d)\n", r, c);
            if (r < 1 || r > 10 || c < 1 || c > 10) {
                System.out.println("Error! You entered the wrong coordinates!");
                p = 0;
            } else if (!GameBoard[r][c].equals("~")) {
                //System.out.println(GameBoard[r][c]);
                switch (GameBoard[r][c]) {
                    case "A":
                        Aircraft_Carrier[player].size--;
                        if (Aircraft_Carrier[player].size == 0) {
                            sunk = true;
                        }
                        break;
                    case "B":
                        Battleship[player].size--;
                        if (Battleship[player].size == 0) {
                            sunk = true;
                        }
                        break;
                    case "S":
                        Submarine[player].size--;
                        if (Submarine[player].size == 0) {
                            sunk = true;
                        }
                        break;
                    case "C":
                        Cruiser[player].size--;
                        if (Cruiser[player].size == 0) {
                            sunk = true;
                        }
                        break;
                    default:  // "D"
                        Destroyer[player].size--;
                        if (Destroyer[player].size == 0) {
                            sunk = true;
                        }
                        break;
                }
                FogBoard[r][c] = "X";
                GameBoard[r][c] = "X";
                //display(FogBoard);
                if (Aircraft_Carrier[player].size <= 0 && Battleship[player].size <= 0 && Submarine[player].size <= 0 && Cruiser[player].size <= 0 && Destroyer[player].size <= 0) {
                    won = true;
                }
                //display(GameBoard);
                //System.out.println("Remaining : \n" + Aircraft_Carrier.size +"\n"+ Battleship.size +"\n"+ Submarine.size +"\n"+ Cruiser.size +"\n"+ Destroyer.size);
                if(sunk && !won) {
                    System.out.println("You sank a ship!");
                    p = 0;
                } else if(won) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    p = 1;
                } else {
                    System.out.println("You hit a ship!");
                    p = 0;
                }
            }
            else {
                FogBoard[r][c] = "M";
                GameBoard[r][c] = "M";
                display(FogBoard);
                System.out.println("You missed.");
                //display(GameBoard);
            }
            return p;
        //} while(p == 0);
    }

    protected void Driver(){
        Scanner s = new Scanner(System.in);
        //display(P1_GameBoard);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Player 1, place your ships on the game field");
        input(P1_GameBoard, 1);
        System.out.println("Press Enter and pass the move to another player");
        s.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Player 2, place your ships to the game field");
        input(P2_GameBoard, 2);

        int result = -1;
        int player = 1;

        do {
            System.out.println("Press Enter and pass the move to another player");
            s.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if(player == 1) {
                display(P2_FogBoard);
                System.out.println("---------------------");
                display(P1_GameBoard);
                System.out.printf("Player %d, it's your turn:\n", player);
                result = Battle(P2_GameBoard, P2_FogBoard, 2);
                player = 2;
            }
            else {
                display(P1_FogBoard);
                System.out.println("---------------------");
                display(P2_GameBoard);
                System.out.printf("Player %d, it's your turn:\n", player);
                result = Battle(P1_GameBoard, P1_FogBoard, 1);
                player = 1;
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } while(result != 1);
    }
}
