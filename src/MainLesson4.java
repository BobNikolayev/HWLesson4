import java.text.BreakIterator;
import java.util.Random;
        import java.util.Scanner;
public class MainLesson4 {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWinnerHorizontal(DOT_X) || checkWinnerVertical(DOT_X) || checkWinnerDiagonal1(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWinnerHorizontal(DOT_O) || checkWinnerVertical(DOT_O) || checkWinnerDiagonal1(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }


    public static boolean checkWinnerHorizontal(char symb) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == symb){
                    count++;
                }else if(count == DOTS_TO_WIN){
                    return true;
                }else{
                    count = 0;
                    break;
                }
            }
        }
        return false;
    }

    public static boolean checkWinnerVertical(char symb) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[j][i] == symb){
                    count++;
                }else if(count == DOTS_TO_WIN) {
                    return true;
                }else{
                    count = 0;
                    break;
                }
            }
        }
        return false;
    }

   public static boolean checkWinnerDiagonal1(char symb) {
       int count = 0;
       for(int i = 0; i < SIZE; i++) {
           if (map[i][i] == symb) {
               count++;
           }else if(count == 3) {
               return true;
           }else{
               return false;
           }
       }
       return false;
   }


//    public static boolean checkWinner() {
//        return checkWinnerHorizontal() ||
//                checkWinnerVertical() ||
//                checkWinnerDiagonals();
//    }
//
//    public static boolean checkWinnerHorizontal() {
//        for (int i = 0; i < SIZE; i++) {
//            boolean res = true;
//            for (int j = 1; j < SIZE && res; j++)
//                res = map[i][j] == map[i][0];
//            if (res)
//                return false;
//        }
//        return false;
//    }
//
//    public static boolean checkWinnerVertical() {
//        for (int i = 0; i < SIZE; i++) {
//            boolean res = true;
//            for (int j = 1; j < SIZE && res; j++)
//                res = map[j][i] == map[0][i];
//            if (res)
//                return true;
//        }
//        return false;
//    }
//
//    public static boolean checkWinnerDiagonals() {
//        boolean res = true;
//        for (int i = 1; i < SIZE && res; i++)
//            res = map[i][i] == map[0][0];
//        if (res)
//            return true;
//        res = true;
//        for (int i = 1; i < SIZE && res; i++)
//            res = map[SIZE - i - 1][i] == map[SIZE - 1][0];
//        return res;
//    }



//    public static boolean checkWinDiag1(char symb) {
//        int x = 0;
//        int y = 0;
//        for (int i = 0; i < (SIZE -= 1); i++) {
//            if(map[x][y] == symb){
//                x++;
//                y++;
//            }else if(y == DOTS_TO_WIN){
//                return true;
//            }else{
//                break;
//            }
//
//        }
//       return false;
//    }
//
//    public static boolean checkWinDiag2(char symb) {
//        int x = (SIZE -= 1);
//        int y = 0;
//        for (int i = 0; i < (SIZE -= 1); i++) {
//            if(map[x][y] == symb){
//                x--;
//                y++;
//            }else if(y == DOTS_TO_WIN){
//                return true;
//            }else{
//                break;
//            }
//
//        }
//        return false;
//        }


//    public static boolean checkWin(char symb) {
//        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
//        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
//        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
//        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
//        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
//        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
//         return false;
//
//    }


    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }


    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }


    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }


    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }


    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
