/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codingame;

/**
 *
 * @author Felix
 */
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * Le type MIME est utilisé dans de nombreux protocoles internet pour associer un type de média (html, image, vidéo, ...) avec le contenu envoyé.
 * Ce type MIME est généralement déduit de l'extension du fichier à transférer.
 * Vous devez écrire un programme qui permet de détecter le type MIME d'un fichier à partir de son nom.
 **/
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class MimeType {

    public static String getMimeType(String file, Map<String, String> mimeMap) {
        int dotIdx = file.lastIndexOf('.');
        String mime;
        if (dotIdx == -1 || dotIdx == file.length() - 1) {
            return "UNKNOWN";
        }
        String ext = file.substring(dotIdx + 1).toLowerCase();
        if (mimeMap.containsKey(ext)) {
            mime = mimeMap.get(ext);
        } else {
            mime = mimeMap.getOrDefault(ext, "UNKNOWN");
        }
        return mime;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        Map<String, String> mimeType = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            mimeType.put(EXT.toLowerCase(), MT);
        }
        in.nextLine();

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            System.out.println(getMimeType(FNAME.toLowerCase(), mimeType));
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
    }
}
