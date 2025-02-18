package com.lab;

import java.util.Scanner;
import java.io.InputStream;

public class Minesweeper {
    static char SAFE_CELL = '.';
    static char MINE_CELL = 'X';
    static char REVEALED_CELL = 'O';
    static int IS_SAFE = 0;
    static int IS_MINE = 1;
    static int IS_REVEALED = 2;
    static int isStarted = 0;
    int fieldX, fieldY;
    int[][] cells;
    String fieldFileName;

    public Minesweeper(String fieldFile) {
        this.fieldFileName = fieldFile;
        initFromFile(fieldFileName);
    }

    public Minesweeper(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.cells = new int[fieldX][fieldY];
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                cells[i][j] = IS_SAFE;
            }
        }
    }

    void displayField() {
        // Task 1: Display the mine field to terminal

        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                if (cells[i][j] == IS_MINE && isStarted == 0) {
                    System.out.print(MINE_CELL);
                } else if (cells[i][j] == IS_REVEALED) {
                    System.out.print(REVEALED_CELL);
                } else {
                    System.out.print(SAFE_CELL);
                }
            }
            System.out.println();
        }
    }

    void setMineCell(int x, int y) {
        cells[x][y] = IS_MINE;
    }

    void initFromFile(String mineFieldFile) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(mineFieldFile);
        if (is == null) {
            System.out.println("File not found");
            return;
        }

        // Task 2: Using `java.util.Scanner` to load mine field from the input stream
        // named, `is`

        Scanner scanner = new Scanner(is);
        fieldX = Integer.parseInt(scanner.nextLine());
        fieldY = Integer.parseInt(scanner.nextLine());

        cells = new int[fieldX][fieldY];
        for (int i = 0; i < fieldX; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < fieldY; j++) {
                char cell = line.charAt(j);
                if (cell == SAFE_CELL) {
                    cells[i][j] = IS_SAFE;
                } else {
                    cells[i][j] = IS_MINE;
                }
            }
        }

        scanner.close();
    }

    public void loadGame(Scanner scanner) {
        isStarted = 1;
        while (true) {
            System.out.print("Enter cell to reveal (x y): ");
            String[] args = scanner.nextLine().split(" ");
            int x = Integer.parseInt(args[0]) - 1;
            int y = Integer.parseInt(args[1]) - 1;

            if (cells[x][y] == IS_MINE) {
                System.out.println("Game over!");
                isStarted = 0;
                displayField();
                break;
            } else {
                cells[x][y] = IS_REVEALED;
                displayField();
            }
        }
    }
}