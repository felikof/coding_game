/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlparser;

/**
 *
 * @author Felix
 */
public class Node {
    Node node;
    String openName = "";
    String closeName;
    String txt;

    private Node(String openName, String txt,  Node node, String closeName) {
        this.openName = openName;
        this.txt = txt;
        this.node = node;
        this.closeName = closeName;  
    }
        
    private Node(String openName, Node node, String closeName) {
        this.openName = openName;
        this.node = node;
        this.closeName = closeName;   
    }
    
    private Node(String openName, String txt, String closeName) {
        this.openName = openName;
        this.txt = txt;
        this.closeName = closeName;
    }
        
    private Node(String openName, String closeName) {
        this.openName = openName;
        this.closeName = closeName;
    }

    @Override
    public String toString() {
        return "Node{" + "node=" + node + ", openName=" + openName + ", closeName=" + closeName + ", txt=" + txt + '}';
    }
            
}
