/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codingame.solvex;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felix
 */
public class Equation {
    SubExp sub;
    SubExp xPart;
    String s;
    List<SubExp> l;
    int i = 0;
    Boolean sign = false;

    public Equation(String s) {
        this.s = s;
        this.l = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Equation{" + "sub=" + sub.toString() + ", xPart=" + xPart.toString() + ", s=" + s + ", l=" + l + ", i=" + i + ", sign=" + sign + '}';
    }
    
    public void build() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        SubExp sub = new SubExp();
        while (i < s.length()) {
            if (s.charAt(i) != '+' || s.charAt(i) != '-') {
                sb.append(s.charAt(i));
            }
            if (s.charAt(i) == '(') {
                sb.append(SubExp.recoverExp(s, i));
            }
            i += 1;
        }
        sub.setS(sb.toString());
        sub.build();
        this.l.add(sub);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String s = "(197*((41+(122*91))+126)+x)=12+3";
        Equation e = new Equation(s);
        e.build();
        System.out.println(e.l.toString());
    }
    
}
