package com.lab;

import java.util.Scanner;

public class App {
    static Minesweeper initMineField(int size) {
        Minesweeper game = new Minesweeper(size, size);

        game.setRandomMine(size);

        return game;
    }

    static Minesweeper initMineFieldFromFile(String minefieldFile) {
        Minesweeper game = new Minesweeper(minefieldFile);

        return game;
    }

    public static void main(String[] args) {
        // Task 3: Implement a menu to select the mine field template
        // Design the menu by yourself.

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select mine field template:");
            System.out.println("1. Custom mine field");
            System.out.println("2. Load mine field from file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            Minesweeper game = null;

            if (choice.equals("1")) {
                System.out.print("Enter mine field size: ");
                int size = 0;
                try {
                    size = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input\n");
                    continue;
                }
                if (size < 5 || size > 10) {
                    System.out.println("Enter size between 5 and 10\n");
                    continue;
                }
                game = initMineField(size);
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
