
from flask import Flask, request, render_template
import gspread
from oauth2client.service_account import ServiceAccountCredentials

app = Flask(__name__, template_folder='C:/Users/Felix/Desktop/Formulaire/')

# Define the scope and credentials
scope = ["https://spreadsheets.google.com/feeds", "https://www.googleapis.com/auth/drive"]
credentials = ServiceAccountCredentials.from_json_keyfile_name('credentials.json', scope)
client = gspread.authorize(credentials)

# Open the Google Sheet by its ID
spreadsheet = client.open_by_key('1kdcYn3bTMbM-RGipBIE2OFYIuQmrbkXu9gYXTkSQWEc')  # Replace with your Google Sheet ID
sheet = spreadsheet.sheet1  # Access the first sheet

@app.route('/')
def form():
    return render_template('form.html')

@app.route('/submit', methods=['POST'])
def submit():
    name = request.form['name']
    surname = request.form['surname']
    age = request.form['age']
    phone = request.form['phone']
    
    # Data to be added to the Google Sheet
    row = [name, surname, phone, age]
    
    # Append data to the Google Sheet
    sheet.append_row(row)
    
    return f"Data submitted successfully: {name} {surname}, Phone: {phone}, Age: {age}"

if __name__ == '__main__':
    app.run(debug=True)