package com.lab;

import java.util.Scanner;

public class App {
    static Minesweeper initMineField() {
        Minesweeper game = new Minesweeper(9, 9);
        game.setMineCell(0, 1);
        game.setMineCell(1, 5);
        game.setMineCell(1, 8);
        game.setMineCell(2, 4);
        game.setMineCell(3, 6);
        game.setMineCell(4, 2);
        game.setMineCell(5, 4);
        game.setMineCell(6, 2);
        game.setMineCell(7, 2);
        game.setMineCell(8, 6);
        return game;
    }

    static Minesweeper initMineFieldFromFile(String minefieldFile) {
        return new Minesweeper(minefieldFile);
    }

    public static void main(String[] args) {
        // Task 3: Implement a menu to select the mine field template
        // Design the menu by yourself.

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select mine field template:");
            System.out.println("1. Load default mine field");
            System.out.println("2. Load mine field from file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            Minesweeper game = null;

            if (choice.equals("1")) {
                game = initMineField();
            } else if (choice.equals("2")) {
                game = initMineFieldFromFile("minefield/minefield01.txt");
            } else if (choice.equals("3")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice\n");
                continue;
            }

            game.displayField();
            game.loadGame(scanner);

            System.out.println("");
        }

        scanner.close();
    }
}
