/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jsonconvert;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Felix
 */

public class JsonConverter {

    public static String convertToJson(Object o) {
        return convertToJson(o, 0);
    }

    private static String convertToJson(Object o, int indentLevel) {
        if (o == null) {
            return "null";
        }

        Class<?> oClass = o.getClass();

        if (oClass == String.class) {
            return "\"" + o.toString() + "\"";
        }

        if (oClass == int.class || oClass == Integer.class ||
            oClass == boolean.class || oClass == Boolean.class ||
            oClass == double.class || oClass == Double.class ||
            oClass == long.class || oClass == Long.class ||
            oClass == float.class || oClass == Float.class ||
            oClass == short.class || oClass == Short.class ||
            oClass == byte.class || oClass == Byte.class) {
            return o.toString();
        }

        if (oClass.isArray()) {
            return convertArrayToJson((Object[]) o, indentLevel);
        }

        if (Collection.class.isAssignableFrom(oClass)) {
            return convertCollectionToJson((Collection<?>) o, indentLevel);
        }

        if (Map.class.isAssignableFrom(oClass)) {
            return convertMapToJson((Map<?, ?>) o, indentLevel);
        }

        return convertObjectToJson(o, indentLevel);
    }

    private static String convertObjectToJson(Object o, int indentLevel) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        Field[] fields = o.getClass().getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!first) {
                    json.append(",\n");
                }
                json.append(getIndentation(indentLevel + 1)).append("\"").append(field.getName()).append("\": ").append(convertToJson(field.get(o), indentLevel + 1));
                first = false;
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            }
        }
        json.append("\n").append(getIndentation(indentLevel)).append("}");
        return json.toString();
    }

    private static String convertArrayToJson(Object[] arr, int indentLevel) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        boolean first = true;
        for (Object item : arr) {
            if (!first) {
                json.append(",\n");
            }
            json.append(getIndentation(indentLevel + 1)).append(convertToJson(item, indentLevel + 1));
            first = false;
        }
        json.append("\n").append(getIndentation(indentLevel)).append("]");
        return json.toString();
    }

    private static String convertCollectionToJson(Collection<?> collection, int indentLevel) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        boolean first = true;
        for (Object item : collection) {
            if (!first) {
                json.append(",\n");
            }
            json.append(getIndentation(indentLevel + 1)).append(convertToJson(item, indentLevel + 1));
            first = false;
        }
        json.append("\n").append(getIndentation(indentLevel)).append("]");
        return json.toString();
    }

    private static String convertMapToJson(Map<?, ?> map, int indentLevel) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                json.append(",\n");
            }
            json.append(getIndentation(indentLevel + 1)).append("\"").append(entry.getKey().toString()).append("\": ").append(convertToJson(entry.getValue(), indentLevel + 1));
            first = false;
        }
        json.append("\n").append(getIndentation(indentLevel)).append("}");
        return json.toString();
    }

    private static String getIndentation(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }

    public static void main(String[] args) {
        // Test avec un seul objet
        Address address = new Address("123 Main St", "Springfield");
        Person person = new Person("John Doe", 30, address);
        String jsonPerson = JsonConverter.convertToJson(person);
        System.out.println(jsonPerson);

        // Test avec une liste d'objets
        Address address1 = new Address("123 Main St", "Springfield");
        Person person1 = new Person("John Doe", 30, address1);

        Address address2 = new Address("456 Elm St", "Shelbyville");
        Person person2 = new Person("Jane Smith", 25, address2);

        List<Person> people = Arrays.asList(person1, person2);
        String jsonPeople = JsonConverter.convertToJson(people);
        System.out.println(jsonPeople);
    }
    
}

