class Compte:
    def __init__(self, solde_initial):
        self.solde = float(solde_initial)

    def nouveau_solde(self, somme):
        self.solde = float(somme)
    
    def solde(self):
        return self.solde
    
    def debit(self, somme):
        self.solde -= somme
        return self.solde
    
    def credit(self, somme):
        self.solde += somme
        return self.solde
    
    def __add__(self, somme):
        self.solde += somme
        print("Nouveau solde : {:+.2f} FCFA".format(self.solde))
        return self
    
    def __sub__(self, somme):
        self.solde -= somme
        print("Nouveau solde : {:-.2f} FCFA".format(self.solde))
        return self

    def main(self):
        while True:
            print("\n1. Voir le solde")
            print("2. Faire un crédit")
            print("3. Faire un débit")
            print("4. Quitter")
            choix = input("Choisissez une option : ")
            if choix == "1":
                print("Solde actuel : {:.2f} FCFA".format(self.solde))
            elif choix == "2":
                somme = float(input("Entrez la somme à créditer : "))
                self.credit(somme)
                print("Nouveau solde : {:.2f} FCFA".format(self.solde))
            elif choix == "3":
                somme = float(input("Entrez la somme à débiter : "))
                self.debit(somme)
                print("Nouveau solde : {:.2f} FCFA".format(self.solde))
            elif choix == "4":
                print("Merci de votre confiance")
                break
            else:
                print("Option non valide. Veuillez réessayer.")

if __name__ == '__main__':
    compte1 = Compte(10000)
    compte1.main()