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
 *L'art ASCII permet de représenter des formes en utilisant des caractères. Dans notre cas, ces formes sont précisément des mots. Par exemple, le mot "MANHATTAN" pourra être affiché ainsi en art ASCII :

 
# #  #  ### # #  #  ### ###  #  ###
### # # # # # # # #  #   #  # # # #
### ### # # ### ###  #   #  ### # #
# # # # # # # # # #  #   #  # # # #
# # # # # # # # # #  #   #  # # # #
 
​Votre mission : Ecrire un programme capable d'afficher une ligne de texte en art ASCII dans un style qui vous est fourni en entrée.
 **/
class AsciiArt {
    static String[][] mat;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        String[][] mat = new String[H][27];

        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            mat = tabChar(mat, ROW, L, i);
            
        }
        /*for (int i = 0; i < H; i++) {
            for (int j = 0; j < 27; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println('\n');
        }*/
        


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(indexOf(T, H, mat));
    }

    public static int getIndex(char c) {
        int i = (int)c;
        if (i >= 97 && i <= 122) {
            i = i - 97;
        } else if (i >= 65 && i <= 90) {
            i = i - 65;
        } else {
            i = 26;
        }
        return i;
    }

    public static String indexOf(String s, int H, String[][] tab) {
        StringBuilder sb = new StringBuilder();
        int[] tabIdx = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {   
            int idx = getIndex(s.charAt(i));
            tabIdx[i] = idx;
            //sb.append(tabString(idx, H, tab));
        }
        sb.append(tabString(tabIdx, H, tab));
        return sb.toString();
    }

    public static String[][] tabChar(String[][] mat, String s, int k, int line) {
        int cols = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder c = new StringBuilder();
            int temp = 0;
            for (int j = i; j < i + k; j++) {
                c.append(s.charAt(j));
                temp = j;
            }
            i = temp;
            mat[line][cols] = c.toString();
            cols++;
        }
        return mat;
    }

    public static String tabString(int index, int hauteur, String[][] tab) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hauteur; i++) {
            s.append(tab[i][index]).append('\n');
        }
        return s.toString();
    }
    public static String tabString(int[] index, int hauteur, String[][] tab) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < index.length; j++) {
                s.append(tab[i][index[j]]);
            }
            s.append('\n');
        }
        return s.toString();
    }
}