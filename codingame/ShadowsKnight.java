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
import static java.util.Arrays.asList;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * Vous allez rechercher les otages d'un batiment donné en sautant de fenêtre en fenêtre à l'aide de votre grappin. 
 * Votre but est d'arriver sur la fenêtre de la pièce où les otages se trouvent afin de désamorcer les bombes. 
 * Malheureusement vous n'avez qu'un nombre de sauts limités avant que les bombes n'explosent...
 **/
class ShadowsKnight {

    /**
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        int maxx = W - 1;
        int maxy = H - 1;
        int minx = 0;
        int miny = 0;

        // game loop
        while (true) { 
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)          
            
            if (bombDir.contains("D")) {
                miny = Y0 + 1;
            } else if (bombDir.contains("U")) {
                maxy = Y0 - 1;
            }
            if (bombDir.contains("L")) {
                maxx = X0 - 1;
            } else if (bombDir.contains("R")) {
                minx = X0 + 1;
            }
            X0 = minx + ((maxx - minx) / 2);
            Y0 = miny + ((maxy - miny) / 2);
            System.out.println(X0 + " " + Y0);
        }
    }
}

