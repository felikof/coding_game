/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felix
 */
public class XmlParser{

    /*public static String sortXmlAttrs(String s) { // "<note importance="high" logged="true" date="2024-07-03"
        String sortedXml = "";
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '<') {
                int start = i;
                while (i < s.length() && s.charAt(i) != '>') {
                    i++;
                }
                int end = i;

                String element = s.substring(start, end + 1);
                if (element.charAt(1) != '/') {
                    sortedXml += sortAttrs(element);
                } else {
                    sortedXml += element;
                }
            } else {
                sortedXml += s.charAt(i);
            }
            i++;
        }
        return sortedXml;
    }

    private static String sortAttrs(String element) { // "note importance="high" logged="true" date="2024-07-03""
        int spaceIndex = element.indexOf(' ');
        if (spaceIndex == -1) {
            return element;
        }

        String tagName = element.substring(0, spaceIndex);
        String attributesString = element.substring(spaceIndex + 1, element.length() - 1);
        String[] attributes = attributesString.split("\\s+");

        for (int i = 0; i < attributes.length - 1; i++) {
            for (int j = i + 1; j < attributes.length; j++) {
                if (attributes[i].split("=")[0].compareTo(attributes[j].split("=")[0]) > 0) {
                    // Échanger les attributs
                    String temp = attributes[i];
                    attributes[i] = attributes[j];
                    attributes[j] = temp;
                }
            }
        }

        String sortedElement = tagName;
        for (String attribute : attributes) {
            sortedElement += " " + attribute;
        }
        sortedElement += ">";
        return sortedElement;
    }*/

    public static String sortXmlAttrs(String s) {
        String sortedXml = "";
        int cursor = 0;

        while (cursor < s.length()) {
            if (s.charAt(cursor) == '<') {
                int start = cursor;
                while (cursor < s.length() && s.charAt(cursor) != '>') {
                    cursor++;
                }
                int end = cursor;

                String e = getSubstring(s, start, end + 1);
                if (e.charAt(1) != '/') {
                    sortedXml += sortAttrs(e);
                } else {
                    sortedXml += e;
                }
            } else {
                sortedXml += s.charAt(cursor);
            }
            cursor++;
        }
        return sortedXml;
    }

    private static String sortAttrs(String e) {
        int spaceIdx = e.indexOf(' ');
        if (spaceIdx == -1) {
            return e;
        }

        String tagName = getSubstring(e, 0, spaceIdx);
        String attr = "";
        for (int i = spaceIdx + 1; i < e.length() - 1; i++) {
            attr += e.charAt(i);
        }

        String[] attrs = parseAttributes(attr);

        // Tri des attributs
        for (int i = 0; i < attrs.length - 1; i++) {
            for (int j = i + 1; j < attrs.length; j++) {
                if (compare(getAttributeName(attrs[i]), getAttributeName(attrs[j])) > 0) {
                    // Échanger les attributs
                    String temp = attrs[i];
                    attrs[i] = attrs[j];
                    attrs[j] = temp;
                }
            }
        }

        String sortedElement = tagName;
        for (String attribute : attrs) {
            sortedElement += " " + attribute;
        }
        sortedElement += ">";

        return sortedElement;
    }

    private static String[] parseAttributes(String attr) {
        int length = attr.length();
        int start = 0;
        List<String> attributes = new ArrayList<>();

        while (start < length) {
            int equalsIdx = -1;
            for (int i = start; i < length; i++) {
                if (attr.charAt(i) == '=') {
                    equalsIdx = i;
                    break;
                }
            }
            if (equalsIdx == -1) break;

            int end = equalsIdx + 1;
            while (end < length && attr.charAt(end) != ' ') {
                end++;
            }

            attributes.add(getSubstring(attr, start, end));
            start = end + 1;
        }
        return attributes.toArray(new String[0]);
    }

    private static String getAttributeName(String attr) {
        int equalsIdx = -1;
        for (int i = 0; i < attr.length(); i++) {
            if (attr.charAt(i) == '=') {
                equalsIdx = i;
                break;
            }
        }
        if (equalsIdx == -1) {
            return attr; 
        }
        return getSubstring(attr, 0, equalsIdx);
    }

    private static String getSubstring(String s, int start, int end) {
        String sb = "";
        for (int i = start; i < end; i++) {
            sb += s.charAt(i);
        }
        return sb;
    }

    private static int compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int lim = Math.min(len1, len2);

        for (int k = 0; k < lim; k++) {
            char c1 = s1.charAt(k);
            char c2 = s2.charAt(k);
            if (c1 != c2) {
                return c1 - c2;
            }
        }
        return len1 - len2;
    }

    public static void main(String[] args) {
        String xml = """
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
        System.out.println(sortXmlAttrs(xml));
    }
}

