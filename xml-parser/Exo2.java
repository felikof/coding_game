/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlparser;

/**
 *
 * @author Felix
 */
public class Exo2 {
    public static int indexOf(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
		if (s.charAt(i) == c) {
                    return i;
		}
            }
            return -1;
	}

    public static int indexOfWord(String txt, String word) {
	int j = 0;
	for (int i = 0; i < txt.length(); i++) {
            for (j = 0; j < word.length() && word.charAt(i + j) == txt.charAt(i); j++);
		if(j == word.length())
		return i;
            }
            return -1;
	}

    public static int find(String txt, String word) {
	for (int i = 0; i < txt.length(); i++) {
            int j = 0;
            for (; j < word.length() && txt.charAt(i + j) == word.charAt(j); j++);

		if (j == word.length())
		return i;
            }
            return -1;
	}

    public static void main(String[] args) {
    	System.out.println(indexOf("Bonjour", 'j'));
    	System.out.println(indexOfWord("Je vais au marche", "vais"));
    	System.out.println(find("Je vais au marche", "vais"));
    	System.out.println(find("Bonjour le jour", "jour"));


    }
}
