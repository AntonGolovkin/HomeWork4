package ru.geekbrains.hw4;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static char [][] map;

    public static final int Size = 3;


    public static final char Map_Element_Empty = '*';
    public static final char Map_Element_X = 'X';
    public static final char Map_Element_0 = '0';

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(checkWin(Map_Element_X)){
                System.out.println("Победил Игрок!");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(Map_Element_0)){
                System.out.println("Победил AI!");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья!");
                break;
            }
        }
    }
    public static boolean checkWin(char element){
        int countV;
        int countH;
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i <= Size - 1; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j <= Size - 1; j++) {
                if (map[i][j] == element) {
                    countH++;
                    if (countH == Size) return true;
                }
                if (map[j][i] == element) {
                    countV++;
                    if (countV == Size) return true;
                }
            }
            if (map[i][i] == element) {
                countDiagonalA++;
                if (countDiagonalA == Size) return true;
            } else countDiagonalA = 0;

            if (map[i][Size - 1 - i] == element) {
                countDiagonalB++;
                if (countDiagonalB == Size) return true;
            } else countDiagonalB = 0;
        }
        return false;
    }



    public static boolean isMapFull(){
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if(map[i][j] == Map_Element_Empty){
                    return false;
                }

            }

        }
        return true;
    }


    public static void aiTurn(){
        int x, y;
        do {
            x = random.nextInt(Size);
            y = random.nextInt(Size);
        }while (!isCellEmpty(x, y));
        map[x][y] = Map_Element_0;
        System.out.println("Ход AI [" + (x + 1) + ", " + (y + 1) + "]");



    }

    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты хода");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellEmpty(x, y));
            map[x][y] = Map_Element_X;



    }
    public static boolean isCellEmpty(int x, int y){
        if(x < 0 || y < 0 || x >= Size || y >= Size){
            return false;
        }
        if(map[x][y] != Map_Element_Empty){
            return false;
        }
        return true;
    }

    public static void printMap() {
        for (int i = 0; i <= Size; i++) {
            System.out.print(i + " ");
            
        }
        System.out.println("");
        for (int i = 0; i < Size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < Size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    public static void initMap() {
        map = new char[Size][Size];
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                map[i][j] = Map_Element_Empty;

            }

        }

    }
}
