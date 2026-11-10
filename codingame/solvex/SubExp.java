/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codingame.solvex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felix
 */
public class SubExp {
    String s;
    String op;
    int i = 0;
    Boolean isNeg = false;
    List<SubExp> num = new ArrayList<SubExp>();
    List<SubExp> denum = new ArrayList<SubExp>();
    
    public SubExp(String s) {
        this.s = s;
    }
    public SubExp() {}

    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }

    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }

    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }

    public Boolean getIsNeg() {
        return isNeg;
    }
    public void setIsNeg(Boolean isNeg) {
        this.isNeg = isNeg;
    }
    
    public List<SubExp> getNum() {
        return num;
    }
    public void setNum(List<SubExp> num) {
        this.num = num;
    }

    public List<SubExp> getDenum() {
        return denum;
    }
    public void setDenum(List<SubExp> denum) {
        this.denum = denum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubExp{").append("\n").append("   ");
        sb.append("s=").append(s).append("\n").append("   ");
        sb.append(", op=").append(op).append("\n").append("   ");
        sb.append(", i=").append(i).append("\n").append("   ");
        sb.append(", isNeg=").append(isNeg).append("\n").append("   ");
        sb.append(", num=").append(num).append("\n");
        sb.append(", denum=").append(denum);
        sb.append('}');
        return sb.toString();
    }
    
    /*public void build() {
        System.out.println(s);
        while (i < s.length()) {
            String currOp = "";
            if (s.charAt(i) == '(') {
                SubExp S1 = new SubExp(recoverExp(s, i, 0));
                i += S1.getS().length();
                S1.setIsNeg(isNeg);
                S1.setOp(currOp);
                addSubExp(S1);
                //S1.build();
            }
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                currOp = String.valueOf(s.charAt(i));
                if (s.charAt(i) == '-') {
                    isNeg = true;
                }
                i = i + 1;
            }
            if (Character.isDigit(s.charAt(i))) {
                setNum(num);
                this.setNum(addSubExp(S).getNum());
            }
        }
    }
    
    public static SubExp addSubExp(SubExp S) {
        List<Integer> l;
        l = recoverNum(S.getS());
        for (int i = 0; i < l.size(); i++) {
            SubExp s = new SubExp(String.valueOf(l.get(i)));
            if ("/".equals(S.getOp())) {
                S.denum.add(s);
            } else if (S.getOp() == null || !"/".equals(S.getOp())){
                S.num.add(s);
            }
        }
        return S;
    }
    
    public static String recoverExp(String s, int idx, int start) {
        s = s.substring(idx);
        String temp;
        StringBuilder sb = new StringBuilder();
        if (s.charAt(0) == '(') {
            for (int j = 0 + start; j <= s.indexOf(")"); j++) {
                sb.append(s.charAt(j));
            }
        } else if (s.contains(")") && Character.isDigit(s.charAt(0))) {
            for (int j = 0 + start; j <= s.indexOf(")"); j++) {
                if (Character.isDigit(s.charAt(j))) {
                    sb.append(s.charAt(j));
                }
            }
        } else if (s.charAt(0) != '(' && Character.isDigit(s.charAt(0))) {
            //temp = s.substring(idx);
            sb.append(recoverNum(s).toString());
        }
        return sb.toString();
    }*/
     
    public static /*List<Integer>*/String recoverNum(String s, int cursor) {
        //cursor = 9;
        //List<Integer> l = new ArrayList<>();
        //while (cursor < s.length()) {
        StringBuilder sb = new StringBuilder();
        if (s.charAt(cursor) == '(' || s.charAt(cursor) == ')') {
            cursor = cursor + 1;
        }
        while (cursor < s.length() && Character.isDigit(s.charAt(cursor))) {
            sb.append(s.charAt(cursor));
            cursor++;
        }
        cursor--;
        /*if (!sb.isEmpty()) {
            l.add(Integer.valueOf(sb.toString()));
        }
        System.out.println(cursor);
            cursor++;
        }*/
        return sb.toString();
    }
    
    public static String recoverExp(String s, int i) {
        StringBuilder sb = new StringBuilder();
        Boolean next = true;
        s = s.substring(i);
        int p1 = 0;
        int p2 = 0;
        i = 0;
        while (next == true) {
            if (s.charAt(i) == '(') {
                p1 += 1;
            }
            sb.append(s.charAt(i));
            if (sb.toString().charAt(i) == ')') {
                p2 += 1;
            }
            if (p1 != 0) {
               next = p1 != p2; 
            }
            i++;
        }
        return sb.toString();
    }
    
    public void build() {
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                System.out.println("first iteration");
                i += 1;
                SubExp S0 = new SubExp();
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                    S0.setOp(String.valueOf(s.charAt(i)));
                    i += 1;
                    SubExp S1 = new SubExp(s.substring(i));
                    S1.build();
                    S0.num.add(S1);
                }
                if (Character.isDigit(s.charAt(i))) {
                    S0.setS(recoverNum(s, i));
                    i += S0.getS().length();
                } else if (!Character.isDigit(s.charAt(i))) {
                    S0.setS(recoverExp(s, i));
                    S0.build();
                }
                if (S0.getOp() == null || !S0.getOp().equals("/")) {
                    this.num.add(S0);
                } else if (S0.getOp().equals("/")) {
                    this.denum.add(S0);
                }
                //i -= 1;
            }
            /*if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                System.out.println("second iteration");
                s = s.substring(i + 1);
                SubExp S0 = new SubExp(recoverExp(s, 0));
                S0.build();
            }*/
            if (Character.isDigit(s.charAt(i))) {
                System.out.println("second iteration");
                SubExp S0 = new SubExp(recoverNum(s, i));
                S0.setS(recoverNum(s, i));
                i += S0.getS().length();
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                    S0.setOp(String.valueOf(s.charAt(i)));
                    i += 1;
                }
                if (S0.getOp() == null || !S0.getOp().equals("/")) {
                    this.num.add(S0);
                } else if (S0.getOp().equals("/")) {
                    this.denum.add(S0);
                }
                if (s.charAt(i) == '(') {
                    build();
                }
            }
            i++;
        }
    }
    
    public static void main(String[] args) {
        String s0 = "(197+2)*((41+(122*91))+126)";
        String s1 = "197+12*(123*2)";
        String s2 = "(197*((41+(122*91))+126))";
        SubExp sb = new SubExp(s2);
        //System.out.println(sb.getS());
        //System.out.println("s1 = " + addSubExp(sb));
        sb.build();
        System.out.println(sb);
        System.out.println(recoverNum(s1, 1));
        System.out.println(recoverNum(recoverExp(s2, 5), 0));
    }
}
