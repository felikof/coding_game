/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlparser;

import java.util.ArrayList;
import java.util.List; 
import java.util.Map;
import java.util.TreeMap;


/**
 *
 * @author Felix
 */
public class XmlParser2 {

    private class Node {
        String name;
        List<Node> children = new ArrayList<>();
        String text = "";
        Map<String, String> attrs = new TreeMap<>();
        
        /*@Override
        public String toString() {
            String formated = "";
            formated += '<' + name;
            if (attrs != null) {
                for (Map.Entry<String, String> attr :attrs.entrySet()) {
                    formated += " " + attr.getKey() + "=\"" + attr.getValue() + "\"";
                }
                formated += ">";
            }else if (children.isEmpty() && text.isEmpty()){
                formated += "/>";
            } else if (attrs != null && children.isEmpty() && text.isEmpty()){
                formated += ">";
            }
            if (!text.isEmpty()) {
                formated += text + "</" + name + ">";
            } else if (!children.isEmpty()) {
                formated += "\n";
                for (Node child : children) {
                    formated += "   " + child.toString() + "\n";
                    //formated += "\n";
                }
                formated += "</" + name + ">";
            }
            return formated; 
        }
    }*/

        @Override
        public String toString() {
            StringBuilder formatted = new StringBuilder();
            formatted.append('<').append(name);
            
            for (Map.Entry<String, String> attr : attrs.entrySet()) {
                formatted.append(" ").append(attr.getKey()).append("=\"").append(attr.getValue()).append("\"");
            }
            
            if (children.isEmpty() && text.isEmpty()) {
                // Self-closing tag
                formatted.append("/>");
            } else {
                formatted.append(">");
                if (!text.isEmpty()) {
                    formatted.append(text);
                }
                for (Node child : children) {
                    formatted.append(child.toString()).append("\n");
                }
                formatted.append("</").append(name).append(">");
            }
            return formatted.toString();
        }}
    private int cursor = 0;
    private String s;

    public String getXml(String s) {
        this.s = s;
        cursor = 0;
        Node node = getNode();
        return node.toString();
        //return formatNode(node, "").toString();
    }

    private Node getNode() {
        skipSpaces();
        if (cursor >= s.length() || s.charAt(cursor) != '<') {
            throw new RuntimeException("Invalid XML format");
        }

        Node node = new Node();
        cursor++;
        node.name = getNodeName(); 
        getAttrs(node);
        
        if (cursor < s.length() && s.charAt(cursor) == '/') {
            cursor++;
            if (cursor < s.length() && s.charAt(cursor) == '>') {
                cursor++;
                return node;
            }
        }
        // cursor++;
        while (cursor < s.length()) {
            skipSpaces();
            if (s.charAt(cursor) == '<') {
                if (cursor + 1 < s.length() && s.charAt(cursor + 1) == '/') {
                    cursor += 2;
                    String endTagName = getNodeName();
                    if (!node.name.equals(endTagName)) {
                        throw new RuntimeException("Mismatched end tag: " + endTagName);
                    }
                    if (cursor < s.length() && s.charAt(cursor) == '>') {
                        cursor++; // Skip '>'
                    }
                    return node;
                } else {
                    Node child = getNode();
                    node.children.add(child);
                }
            } else {
                node.text = getText();
            }
        }
        return node;
    }

    private String getNodeName() {
        StringBuilder nodeName = new StringBuilder();
        while (cursor < s.length() && !Character.isWhitespace(s.charAt(cursor)) && s.charAt(cursor) != '>' && s.charAt(cursor) != '/') {
            nodeName.append(s.charAt(cursor));
            cursor++;
        }
        return nodeName.toString();
    }

    private void getAttrs(Node node) {
        // List<String> attrs = new ArrayList<>();
        while (cursor < s.length() && s.charAt(cursor) != '>') {
            skipSpaces();
            if (s.charAt(cursor) == '/') {
                cursor++;
                if (cursor < s.length() && s.charAt(cursor) == '>') {
                    cursor++;
                    return;
                }
            }
            String attrName = getAttrName();
            cursor++; // Skip '='
            skipSpaces();
            String attrValue = getAttrValue();
            node.attrs.put(attrName, attrValue);
            skipSpaces();
        }
        cursor++;
    }

    private String getAttrName() {
        StringBuilder attrName = new StringBuilder();
        while (cursor < s.length() && s.charAt(cursor) != '=') {
            attrName.append(s.charAt(cursor));
            cursor++;
        }
        return attrName.toString();
    }

    private String getAttrValue() {
        if (cursor < s.length() && s.charAt(cursor) == '"') {
            cursor++; // Skip '"'
            StringBuilder attrValue = new StringBuilder();
            while (cursor < s.length() && s.charAt(cursor) != '"') {
                attrValue.append(s.charAt(cursor));
                cursor++;
            }
            cursor++;
            return attrValue.toString();
        }
        return "";
    }

    private String getText() {
        StringBuilder text = new StringBuilder();
        while (cursor < s.length() && s.charAt(cursor) != '<') {
            text.append(s.charAt(cursor));
            cursor++;
        }
        return text.toString();
    }

    private void skipSpaces() {
        while (cursor < s.length() && Character.isWhitespace(s.charAt(cursor))) {
            cursor++;
        }
    }
    
    
    public static void main(String[] args) {
        String s1 = "<note/>";
        String s2 = "<note                category=\"personal\" date=\"2024-07-03\" id=\"1\" importance=\"high\" logged=\"true\">Ali</note>";
        String s3 = """
                    <note          category="personal" date="2024-07-03" id="1" importance="high" logged="true"  >
                        <to>Tove</to>
                        <from>Jani</from>
                        <heading reminder="yes" type="short">Reminder</heading>
                        <body>Don't forget me this weekend!</body>
                    </note>""";
        String s4 = """
                    <root>
                    <note id="1" logged="true" category="personal" importance="high" date="2024-07-03">
                            <to>Tove</to>
                            <from>Jani</from>
                            <heading type="short" reminder="yes">Reminder</heading>
                            <body>Don't forget me this weekend!</body>
                        </note>
                        <note date="2024-07-04" category="work" logged="false" id="2" importance="low">
                            <to>Peter</to>
                            <from>John</from>
                            <heading reminder="no" type="long">Meeting</heading>
                            <body>Don't forget the meeting at 10 AM.</body>
                        </note>
                        <task assigned="Alice" due="2024-07-10" id="3" priority="high" status="in-progress">
                            <description>Finish the quarterly report.</description>
                            <comment>Include all department summaries.</comment>
                            <attachment type="pdf" name="report_template.pdf" />
                        </task>
                        <task priority="medium" assigned="Bob" due="2024-07-15" id="4" status="not-started">
                            <description>Prepare presentation for client meeting.</description>
                            <comment>Focus on Q2 results and projections.</comment>
                            <attachment type="ppt" name="client_presentation.pptx" />
                        </task>
                        <project id="5" name="Website Redesign" start="2024-01-01" end="2024-12-31" budget="50000">
                            <team>
                                <member role="manager" id="1" name="Eva" />
                                <member role="developer" id="2" name="Mike" />
                                <member id="3" role="designer" name="Sophie" />
                                <member name="Liam" role="tester" id="4" />
                            </team>
                            <milestone due="2024-03-01" status="completed" id="1" name="Phase 1 Completion" />
                            <milestone due="2024-06-01" id="2" name="Phase 2 Completion" status="in-progress" />
                            <milestone id="3" due="2024-09-01" name="Final Review" status="not-started" />
                        </project>
                    </root>""";

        XmlParser2 xmlParser = new XmlParser2();
        System.out.println(xmlParser.getXml(s1));
        System.out.println(xmlParser.getXml(s2));
        System.out.println(xmlParser.getXml(s3));
        System.out.println(xmlParser.getXml(s4));
    }
}