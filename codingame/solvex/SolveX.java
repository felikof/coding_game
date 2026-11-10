package codingame.solvex;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * In this puzzle, you are given an equation with a variable x somewhere in that equation. 
 * Your task is to REARRANGE the terms of the expressions on either side of the equal sign to produce an equation in the following form:
 * x=expression
 **/
class Solution {

    public static void main(String args[]) {
        Solution so = new Solution();
        Scanner in = new Scanner(System.in);
        int numEquations = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < numEquations; i++) {
            String equation = in.nextLine();
            //System.err.println("Eq:" + equation);
            Equation e = so.new Equation(equation);
            System.out.println(e.formater());
        }
        for (int i = 0; i < numEquations; i++) {
            // Write an answer using System.out.println()
            // To debug: System.err.println("Debug messages...");
            
            //System.out.println("x=<<expression>> (expression must be rearranged to standard form)");
        }
    }

    private class Equation {
        int cursor = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        private String eq;
        private Boolean isRight = false;

        public Equation(String s) {
            this.eq = s;
            sb.append("x=");
        }

        public List<Integer> getNum(String s) {
            int nb = 0;
            int cursor = 0;
            List<Integer> l = new ArrayList<>();
            
            while (cursor < s.length()) {
                StringBuilder sb = new StringBuilder();
                while (cursor < s.length() && Character.isDigit(s.charAt(cursor))) {
                    if (s.charAt(cursor) != '+' || s.charAt(cursor) != '-' || s.charAt(cursor) != '*') {
                        sb.append(s.charAt(cursor));
                    }
                    cursor++;
                }
                if (!sb.isEmpty() && sb != null) {
                    nb = Integer.parseInt(sb.toString());
                    l.add(nb);
                }
                cursor++;
            }
            return l;
        }

        public Map<String, List<Integer>> parser(String s) {
            Map<String, List<Integer>> L = new HashMap<>();
            String lf = s.substring(0, s.indexOf("="));
            String rt = s.substring((s.indexOf("=") + 1), s.length());
            
            if (s.contains("*")) {
                if (lf.contains("x")) {
                    if (lf.indexOf("x") == 0) lf = lf.replace("x", "");
                    if (lf.indexOf("x") != lf.length()) lf = lf.replace("x", "");
                    if (cursor < lf.length()) {
                        left = getNum(lf);
                        cursor++;
                    }
                } else if (!lf.contains("x")) {
                    left = getNum(lf);
                    cursor++;
                }

                if (rt.contains("x")) {
                    isRight = true;
                    if (rt.indexOf("x") == 0) rt = rt.replace("x*", "");
                    if (rt.indexOf("x") != rt.length()) rt = rt.replace("*x", "");
                    right = getNum(rt);
                    cursor++;
                } else {
                    right = getNum(rt);
                    cursor++;
                }
            } else if (s.contains("+") || s.contains("-")) {
                if (lf.contains("x")) {
                    if (lf.indexOf("x") == 0) lf = lf.replace("x", "");
                    if (lf.indexOf("x") != lf.length()) lf = lf.replace("x", "");
                    if (cursor < lf.length()) {
                        left = getNum(lf);
                        cursor++;
                    }
                } else if (!lf.contains("x")) {
                    left = getNum(lf);
                    cursor++;
                }
            
                if (rt.contains("x")) {
                    isRight = true;
                    if (rt.indexOf("x") == 0) rt = rt.replace("x+", "");
                    if (rt.indexOf("x") != rt.length()) rt = rt.replace("+x", "");
                    right = getNum(rt);
                    cursor++;
                } else {
                    right = getNum(rt);
                    cursor++;
                }
                if (s.indexOf("x") != 0 && s.indexOf("x") != (s.indexOf("=") + 1) && (left.size() != 1 && right.size() != 1)) {
                    int temp = left.get(0);
                    left.remove(0);
                    left.add(right.get(0));
                    right.remove(0);
                    right.add(temp);
                }
            } else if (s.contains("/")) {
                if (lf.contains("x")) {
                    if (lf.indexOf("x") == 0) lf = lf.replace("x", "");
                    if (lf.indexOf("x") != lf.length()) lf = lf.replace("x", "");
                    if (cursor < lf.length()) {
                        left = getNum(lf);
                        cursor++;
                    }
                } else if (!lf.contains("x")) {
                    left = getNum(lf);
                    cursor++;
                }

                if (rt.contains("x")) {
                    isRight = true;
                    if (rt.indexOf("x") == 0) rt = rt.replace("x/", "");
                    if (rt.indexOf("x") != rt.length()) rt = rt.replace("/x", "");
                    right = getNum(rt);
                    cursor++;
                } else {
                    right = getNum(rt);
                    cursor++;
                }
            }

            Collections.sort(left);
            Collections.sort(right);
            L.put("Left", left);
            L.put("Right", right);
            return L;
        }

        public String formater() {
            int idx = eq.indexOf("x");
            if (eq.contains("+")) {
                Map<String, List<Integer>> L = parser(eq);
                left = L.get("Left");
                right = L.get("Right");
                if (isRight == false) {
                    if (eq.charAt(idx + 1) == '+' || eq.charAt(idx - 1) == '+') {
                        sb.append(right.get(0));
                        for (int i = 1; i < right.size(); i++) {
                            sb.append("+").append(right.get(i));
                        }
                        for (int j = 0; j < left.size(); j++) {
                            sb.append("-").append(left.get(j));
                        }
                    }
                } else if (isRight == true) {
                    if (eq.charAt(idx - 1) == '+' || eq.charAt(idx + 1) == '+') {
                        sb.append(left.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("+").append(left.get(i));
                        }
                        for (int j = 0; j < right.size(); j++) {
                            sb.append("-").append(right.get(j));
                        }
                    }
                }
            } else if (eq.contains("-")) {
                Map<String, List<Integer>> L = parser(eq);
                left = L.get("Left");
                right = L.get("Right");
                if (isRight == false) {
                    if (eq.charAt(idx + 1) == '-') {
                        sb.append(right.get(0));
                        for (int i = 1; i < right.size(); i++) {
                            sb.append("-").append(right.get(i));
                        }
                        for (int j = 0; j < left.size(); j++) {
                            sb.append("+").append(left.get(j));
                        }
                    } else if (eq.charAt(idx - 1) == '-' && idx > 2) {
                        sb.append(right.get(0));
                        for (int j = 1; j < right.size(); j++) {
                            sb.append("+").append(right.get(j));
                        }
                        for (int i = 0; i < left.size(); i++) {
                            sb.append("-").append(left.get(i));
                        }
                    } else if (eq.charAt(idx - 1) == '-' && idx <= 2) {
                        sb.append(left.get(0));
                        for (int j = 1; j < right.size(); j++) {
                            sb.append("+").append(right.get(j));
                        }
                        sb.append("-").append(right.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("-").append(left.get(i));
                        }
                    }
                } else if (isRight == true) {
                    if (eq.charAt(idx - 1) == '-' && idx != (eq.length() - 1)) {
                        sb.append(left.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("+").append(left.get(i));
                        }
                        for (int j = 0; j < right.size(); j++) {
                            sb.append("-").append(right.get(j));
                        }
                    } else if (eq.charAt(idx - 1) == '-' && idx >= (eq.length() - 1)) {
                        sb.append(right.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("+").append(left.get(i));
                        }
                        sb.append("-").append(left.get(0));
                        for (int j = 1; j < right.size(); j++) {
                            sb.append("-").append(right.get(j));
                        }
                    } else if (eq.charAt(idx + 1) == '-') {
                        sb.append(left.get(0));
                        for (int j = 1; j < left.size(); j++) {
                            sb.append("-").append(left.get(j));
                        }
                        for (int i = 0; i < right.size(); i++) {
                            sb.append("+").append(right.get(i));
                        }
                    }
                }
            } else if (eq.contains("*")) {
                Map<String, List<Integer>> L = parser(eq);
                left = L.get("Left");
                right = L.get("Right");

                if (isRight == false) {
                    if (eq.charAt(idx + 1) == '*' || eq.charAt(idx - 1) == '*') {
                        sb.append(right.get(0));
                        for (int i = 1; i < right.size(); i++) {
                            sb.append("*").append(right.get(i));
                        }
                        for (int j = 0; j < left.size(); j++) {
                            sb.append("/").append(left.get(j));
                        }
                    }
                } else if (isRight == true) {
                    if (eq.charAt(idx - 1) == '*' || eq.charAt(idx + 1) == '*') {
                        sb.append(left.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("*").append(left.get(i));
                        }
                        for (int j = 0; j < right.size(); j++) {
                            sb.append("/").append(right.get(j));
                        }
                    }
                }
            } else if (eq.contains("/")) {
                Map<String, List<Integer>> L = parser(eq);
                left = L.get("Left");
                right = L.get("Right");

                if (isRight == false) {
                    if (eq.charAt(idx + 1) == '/') {
                        sb.append(left.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("*").append(left.get(i));
                        }
                        sb.append("*").append(right.get(0));
                        for (int j = 1; j < right.size(); j++) {
                            sb.append("/").append(right.get(j));
                        }
                    } else if (eq.charAt(idx - 1) == '/') {
                        for (int i = 0; i < left.size(); i++) {
                            sb.append(left.get(i)).append("/");
                        }
                        sb.append(right.get(0));
                        for (int j = 1; j < right.size(); j++) {
                            sb.append("*").append(right.get(j));
                        }
                    }
                } else if (isRight == true) {
                    if (eq.charAt(idx - 1) == '/') {
                        sb.append(right.get(0));
                        for (int i = 1; i < right.size(); i++) {
                            sb.append("/").append(right.get(i));  
                        }
                        for (int j = 0; j < left.size(); j++) {
                            sb.append("/").append(left.get(j));  
                        }
                    } else if (eq.charAt(idx + 1) == '/') {
                        sb.append(left.get(0));
                        for (int i = 1; i < left.size(); i++) {
                            sb.append("/").append(left.get(i));
                        }
                        for (int j = 0; j < right.size(); j++) {
                            sb.append("*").append(right.get(j));
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
}