/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codingame;

/**
 *
 * @author Felix
 */
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Scanner;
/**
 * Don't let the machines win. You are humanity's last hope...
 * Le jeu se joue sur une grille rectangulaire de taille variable représentant une micro-puce. 
 * Certaines cellules de la grille contiennent des n?uds d'alimentation. Les autres cellules sont vides.
 * Le but est d'indiquer, s'ils existent, le voisin horizontal et le voisin vertical de chaque n?ud d'alimentation.
 **/
class ThereIsSpoon {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }

        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            grid[i] = in.nextLine().toCharArray();
        }

        // Process each cell in the grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == '0') { // Found a node
                    int rightX = -1, rightY = -1;
                    int bottomX = -1, bottomY = -1;

                    // Find right neighbor
                    if (x + 1 < width && grid[y][x + 1] == '0') {
                        rightX = x + 1;
                        rightY = y;
                    } else if (x + 2 < width && grid[y][x + 2] == '0') {
                        rightX = x + 2;
                        rightY = y;
                    }

                    // Find bottom neighbor
                    if (y + 1 < height && grid[y + 1][x] == '0') {
                        bottomX = x;
                        bottomY = y + 1;
                    } else if (y + 2 < height && grid[y + 2][x] == '0') {
                        bottomX = x;
                        bottomY = y + 2;
                    }

                    // Three coordinates: a node, its right neighbor, its bottom neighbor
                    System.out.println(x + " " + y + " " + rightX + " " + rightY + " " + bottomX + " " + bottomY);
                }
            }
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
    }
}