/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication26;

/**
 *
 * @author Felix
 */
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * Écrivez un programme qui affiche la température la plus proche de 0 parmi les données d'entrée. 
 * Si deux nombres sont aussi proches de zéro, alors l'entier positif sera considéré comme étant le plus proche de zéro 
 * (par exemple, si les températures sont -5 et 5, alors afficher 5).
 **/
class Temperature {

    /**
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int temp = 0;

        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            if (i == 0) temp = t;
            if (abs(t) < abs(temp)) {
                temp = t;
            } else if (t > 0 && temp == (-1 * t)) {
                temp = t;
            }
        }
        System.out.println(temp);
    }

    public static int abs(int i) {
        return i < 0 ? -1 * i : i;
    }
}
