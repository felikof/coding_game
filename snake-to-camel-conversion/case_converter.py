import re

def snake_to_camel(snake_str):
    components = snake_str.split('_')
    return components[0] + ''.join(x.capitalize() for x in components[1:])

def convert_script(script):
    def replace_snake_case(match):
        return snake_to_camel(match.group(0))
    
    # Remplacement des noms de variables et de fonctions en snake_case
    converted_script = re.sub(r'\b[a-z]+(?:_[a-z]+)+\b', replace_snake_case, script)
    return converted_script

# Script fictif en snake_case
script_snake_case = """let user_id = 123;
let user_name = "John Doe";
let first_name = "John";
let last_name = "Doe";

// Fonction format_date en snake_case
function format_date(date_string) {
    let date_parts = date_string.split("-");
    return `${date_parts[2]}/${date_parts[1]}/${date_parts[0]}`;
}

let take_this = 'take_this';

// Test d'un xpath avec une variable à convertir
const x_path = `//*[@id="formSaisie:basic_world"]/option[it_contains(some_text(),'${take_this}')]`;

// Autres variables de test en snake_case
let current_user_id = user_id;
let current_user_name = user_name;
let current_user_details = { first_name, last_name };
let formatted_date = format_date("2025-01-23");

console.log("x_path avant transformation : ", x_path);
console.log("Date formatée : ", formatted_date);
"""
script_snake_case0 = """
    import re

    def snake_to_camel(snake_str):
        components = snake_str.split('_')
        return components[0] + ''.join(x.capitalize() for x in components[1:])

    def convert_script(script):
        def replace_snake_case(match):
            return snake_to_camel(match.group(0))
        
        # Remplacement des noms de variables et de fonctions en snake_case
        converted_script = re.sub(r'\b[a-z]+(?:_[a-z]+)+\b', replace_snake_case, script)
        return converted_script
"""
# Conversion du script
script_camel_case = convert_script(script_snake_case0)
print(script_camel_case)