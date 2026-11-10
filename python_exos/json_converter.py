import json
from collections.abc import Collection
from typing import Any

class JsonConverter:

    def convert_to_json(obj: Any, indent_level: int = 0) -> str:
        if obj is None:
            return "null"
        
        obj_type = type(obj)

        # String type
        if obj_type is str:
            return f"\"{obj}\""

        # Primitive types
        if obj_type in [int, bool, float, complex]:
            return str(obj).lower() if isinstance(obj, bool) else str(obj)
        
        # List/Array type
        if isinstance(obj, (list, tuple)):
            return JsonConverter.convert_array_to_json(obj, indent_level)
        
        # Collection type (Set, List, Tuple, etc.)
        if isinstance(obj, Collection) and not isinstance(obj, str):
            return JsonConverter.convert_collection_to_json(obj, indent_level)
        
        # Dictionary/Map type
        if isinstance(obj, dict):
            return JsonConverter.convert_map_to_json(obj, indent_level)

        # Handle objects
        return JsonConverter.convert_object_to_json(obj, indent_level)

    def convert_object_to_json(obj: Any, indent_level: int) -> str:
        json_str = "{\n"
        fields = vars(obj)
        first = True
        for field, value in fields.items():
            if not first:
                json_str += ",\n"
            json_str += f"{JsonConverter.get_indentation(indent_level + 1)}\"{field}\": {JsonConverter.convert_to_json(value, indent_level + 1)}"
            first = False
        json_str += f"\n{JsonConverter.get_indentation(indent_level)}}}"
        return json_str

    def convert_array_to_json(arr: list, indent_level: int) -> str:
        json_str = "[\n"
        first = True
        for item in arr:
            if not first:
                json_str += ",\n"
            json_str += f"{JsonConverter.get_indentation(indent_level + 1)}{JsonConverter.convert_to_json(item, indent_level + 1)}"
            first = False
        json_str += f"\n{JsonConverter.get_indentation(indent_level)}]"
        return json_str

    def convert_collection_to_json(collection: Collection, indent_level: int) -> str:
        json_str = "[\n"
        first = True
        for item in collection:
            if not first:
                json_str += ",\n"
            json_str += f"{JsonConverter.get_indentation(indent_level + 1)}{JsonConverter.convert_to_json(item, indent_level + 1)}"
            first = False
        json_str += f"\n{JsonConverter.get_indentation(indent_level)}]"
        return json_str

    def convert_map_to_json(map_obj: dict, indent_level: int) -> str:
        json_str = "{\n"
        first = True
        for key, value in map_obj.items():
            if not first:
                json_str += ",\n"
            json_str += f"{JsonConverter.get_indentation(indent_level + 1)}\"{key}\": {JsonConverter.convert_to_json(value, indent_level + 1)}"
            first = False
        json_str += f"\n{JsonConverter.get_indentation(indent_level)}}}"
        return json_str

    def get_indentation(level: int) -> str:
        return "  " * level

class Person:
    def __init__(self, name, age, is_student):
        self.name = name
        self.age = age
        self.is_student = is_student

# Create a few objects to test with:
person1 = Person("Alice", 30, True)
person2 = Person("Bob", 24, False)

# A list of persons
persons = [person1, person2]

# A dictionary
data = {"title": "Student List", "count": 2, "students": persons}

# Testing the JsonConverter:
print(JsonConverter.convert_to_json(persons))
