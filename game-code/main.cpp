#include <iostream>
#include <stdio.h>
#include <conio.h>
#define rows 10
#define cols 20
#include <windows.h> // pour Sleep() et system("cls")

using namespace std;

//const int rows = 5;
//const int cols = 5;
char tab[rows][cols];
string symbol = "*";
string s = "";
int x;
int y;

// Fonction pour effacer l'écran
void clearScreen() {
    // system("cls") fonctionne sous Windows
    system("cls");
}

    /* char ch[7] = {0};
    for (int i = 0; i < 7; i++) {
        ch[i] = getch();
        clearScreen();
        cout <<"Received Input: " << ch << endl;
    } */
    void rightDir() {
        if (x < cols - 1) {
            x += 1;
        }
    }
    void leftDir() {
        if (x > 0) {
            x -= 1;
        }
    }
    void upDir() {
        if (y > 0) {
            y -= 1;
        }
    }
    void downDir() {
        if (y < rows - 1) {
            y += 1;
        }
    }

    string boite() {
        string s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (y == i && x == j) {
                    s += symbol;
                } else {
                    s += ' ';
                }
            }
            s += '\n';
        }
        return s;
    }

int main()
{

    int cpt = 0;
    while (true) {

        if (kbhit() != 0) {
            int c = getch();

            if (c == 77) {
                rightDir();
                s = boite();
                clearScreen();
                cout << s << endl;
            } else if (c == 75) {
                leftDir();
                s = boite();
                clearScreen();
                cout << s << endl;
            } else if (c == 72) {
                upDir();
                s = boite();
                clearScreen();
                cout << s << endl;
            } else if (c == 80) {
                downDir();
                clearScreen();
                s = boite();
                cout << s << endl;
            }
        }
        cpt++;

    }
    return 0;
    /*
    char symbol = '*';
    //char ch = getch();
    char ch[20] = {0};
    for (int i = 0; i < 19; i++) {
        ch[i] = getch();
        clearScreen();
        cout <<"Received Input: " << ch << endl;
    }
    */
}
