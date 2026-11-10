import math
import time
from tkinter import *

amis = ['Ama', 'Davido']
amis.append('ALI')
amis.sort()
amis.reverse()
ami = {'matin': 7, 'soir': 2}
print(ami.values())
print(math.sqrt(7))
print(type(amis))
#print(amis[2].lower())

# Taille du fichier en kilooctets
taille_ko = 536
# Conversion en octets
taille_octets = taille_ko * 1024
# Conversion en bits
taille_bits = taille_octets * 8
print(f"Taille du fichier : {taille_octets} octets")
print(f"Taille du fichier : {taille_bits} bits")

def calculer_cle_de_controle(num_secu):
    # Suppression des espaces et vérification de la longueur
    num_secu = num_secu.replace(" ", "")
    
    # Convertir le numéro en entier
    numero = int(num_secu)
    
    # Calculer la clé de contrôle
    cle_de_controle = 97 - (numero % 97)
    
    # Retourner la clé de contrôle avec un format à deux chiffres
    return f"{cle_de_controle:02}"
# Exemple de numéro de sécurité sociale
numero_secu = "1 89 11 26 108 268 91"
cle = calculer_cle_de_controle(numero_secu)
print(f"La clé de contrôle est : {cle}")

#A partir des deux variables prenom et nom, afficher les initiales (par exemple LM pour Léa Martin).
def afficher_init(prenom, nom):
    init = prenom[0] + nom[0]
    return init.upper()
print(afficher_init('Lea', 'Martin'))

login = {'alecoutur': 'Huz4', 'lmartin': 'monty', 'fsincere': 'gnugpl'}
id = input('Entrez votre ID : ')
mdp = input('Entrez votre mot de passe : ')
valide = True
if id in login and login[id] == mdp:
    print(valide)
else:
    valide = False
    print(valide)

def mdp(prenom, nom):
    #prenom = input('Entrez votre prenom : ')
    #nom = input('Entrez votre nom : ')
    mdp = prenom[0] + nom[0:8]
    print(mdp.lower())
print(mdp('Felix', 'Agbavo'))

# Validité numéro de sécurité social
def validity(social_sec_num, control_key):
    if len(social_sec_num) == 13:
        ctrl = 97 - ((int(social_sec_num))%97)
        print(ctrl)
        if ctrl == control_key:
            print('VALIDE')
        else:
            print('INVALIDE')
print(validity('2891126108268', 41))

# Horloge
quitter = 'n'
while quitter != 'o':
    print('Heure courante ', time.strftime('%H:%M:%S'))
    quitter = input("Voulez-vous quitter le programme (o/n) ? ")
print("A bientôt")

# Multiplication
for i in range(1, 10):
    print('Table de ', i)
    for j in range(1, 12):
        print(j, '*', i ,' = ', j*i)

# Racine carré
def racine(num):
    try:
        result = math.sqrt(num)
    except ValueError:
        print('Nombre invalide')
    else:
        print('Racine carré de ', num, ' = ', result)
racine(-6)

# KeyboardInterrupt
try:
    i = 0
    while True:
        i += 1
        print(i)
except KeyboardInterrupt:
    print('Fin de programme')

# Essai
class EnterMessage(Frame):
    def __init__(self, master=None):
        """Initialisation : création d'un widget Frame"""
        Frame.__init__(self, master, bg='navy')
        self.pack(padx=10, pady=10)
        self.CreateWidgets()

    def CreateWidgets(self):
        """ Création des widgets Entry et Button dans le widget Frame """
        self.NouveauMessage = StringVar()
        Entry(master=self, textvariable=self.NouveauMessage).pack(side=LEFT, padx=10, pady=10)
        Button(master=self, text="Nouveau", fg="navy", command=self.Nouveau).pack(padx=10, pady=10)

    def Nouveau(self):
        """ Création d'une instance de la classe NewPostIt """
        if self.NouveauMessage.get() != "":
            NewPostIt(master=self.master, message=self.NouveauMessage.get())
            self.NouveauMessage.set("")

class NewPostIt(Frame):
    def __init__(self, master=None, message=None):
        """Initialisation : création d'un widget Frame"""
        Frame.__init__(self, master, bg="maroon")
        self.pack(side=LEFT, padx=10, pady=10)
        self.CreateWidgets(message)

    def CreateWidgets(self, message):
        Label(master=self, text=message, fg='maroon', bg='white').pack(padx=10, pady=10)
        Button(master=self, text="Effacer", fg="navy", command=self.destroy).pack(padx=10, pady=10)

if __name__ == '__main__':
    # création de la fenêtre principale
    Mafenetre = Tk()
    Mafenetre.title('Post-it')
    Mafenetre['bg'] = 'bisque'

    # Création d'une instance de la classe EnterMessage
    EnterMessage(master=Mafenetre)

    Mafenetre.mainloop()
