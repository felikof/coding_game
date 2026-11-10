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
 * Le binaire avec des 0 et des 1 c'est bien. Mais le binaire avec que des 0, ou presque, c'est encore mieux.
    Ecrivez un programme qui, à partir d'un message en entrée, affiche le message codé avec cette technique en sortie.
 **/
 
class Unary {

    public static String numToBinar(int c) {
        String binStr = Integer.toBinaryString(c);
        return String.format("%7s", binStr).replace(' ', '0');
    }

    public static String charToInt(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(numToBinar(s.charAt(i)));
        }
        return sb.toString();
    }

    public static String encodeMessage(String m) {
        StringBuilder encodedMessage = new StringBuilder();
        String binaryString = charToInt(m);
        int n = binaryString.length();
        int i = 0;

        while (i < n) {
            char currentChar = binaryString.charAt(i);
            int count = 0;

            while (i < n && binaryString.charAt(i) == currentChar) {
                count++;
                i++;
            }

            if (currentChar == '1') {
                encodedMessage.append("0 ");
                encodedMessage.append("0".repeat(count)); 
            } else if (currentChar == '0') {
                encodedMessage.append("00 ");
                encodedMessage.append("0".repeat(count)); 
            }
            
            encodedMessage.append(" ");
        }
        
        if (encodedMessage.length() > 0) {
            encodedMessage.setLength(encodedMessage.length() - 1);
        }

        return encodedMessage.toString();
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(encodeMessage(MESSAGE));
    }

}


