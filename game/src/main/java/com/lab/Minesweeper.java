package com.lab;

import java.util.Scanner;
import java.io.InputStream;

public class Minesweeper {
    private char SAFE_CELL = '.', MINE_CELL = 'X', REVEALED_CELL = 'O';
    private int IS_SAFE = 0, IS_MINE = 1, IS_REVEALED = 2;
    private int isStarted = 0;
    private int fieldX, fieldY;
    private int[][] cells;
    private String fieldFileName;

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

    void setRandomMine(int size) {
        int count = 0, round = 0;
        while (true) {
            round++;
            if (round > 100) {
                throw new RuntimeException("Cannot set mine");
            }

            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);

            if (cells[x][y] == IS_MINE) {
                continue;
            }

            setMineCell(x, y);

            count++;
            if (count >= size) {
                break;
            }
        }
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
        fieldX = Integer.parseInt(scanner.nextLine().trim());
        fieldY = Integer.parseInt(scanner.nextLine().trim());

        cells = new int[fieldX][fieldY];
        for (int i = 0; i < fieldX; i++) {
            String line = scanner.nextLine().trim();
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
            String line = scanner.nextLine().trim();

            String[] args = line.split(" ");
            int x = Integer.parseInt(args[0]) - 1;
            int y = Integer.parseInt(args[1]) - 1;

            if (x < 0 || x >= fieldX || y < 0 || y >= fieldY) {
                System.out.println("Invalid cell");
                continue;
            }

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