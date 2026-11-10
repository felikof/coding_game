class LoyaltyCard:
    def __init__(self, size):
        self.tampons = [False] * size
        self.size = size

    # Ajouter un tampon à un cercle
    def add_tampon(self, i):
        if i < 0 or i >= self.size:
            print(f"Cercle invalide. Veuillez saisir un cercle entre 0 et {self.size - 1}.")
            return
        self.tampons[i] = True

    # Vérifier si un cercle est tamponné
    def stamped_circle(self, i):
        if i < 0 or i >= self.size:
            print(f"Cercle invalide. Veuillez saisir un cercle entre 0 et {self.size - 1}.")
            return False
        return self.tampons[i]

    # Afficher les tampons
    def show_tampons(self):
        print("État des tampons sur la carte de fidélité :")
        for i in range(self.size):
            print("O " if self.stamped_circle(i) else "N ", end="")
        print()
        hex_value = self.encode_hex(self.tampons)
        print(f"Encodé en hexadécimal: {hex_value}")
        decoded_value = self.decode_hex(hex_value)
        print(f"Décodé en tampons: {decoded_value}")

    # Méthode pour encoder les tampons en hexadécimal
    def encode_hex(self, tampons):
        val = 0
        for i in range(self.size):
            if tampons[i]:
                val |= (1 << (self.size - 1 - i))
        return f"{val:03x}"

    # Méthode pour décoder une chaîne hexadécimale en tampons
    def decode_hex(self, hex_string):
        val = int(hex_string, 16)
        tampons = []
        for i in range(self.size - 1, -1, -1):
            mask = 1 << i
            if val & mask:
                tampons.append('O')
            else:
                tampons.append('N')
        return ''.join(tampons)


if __name__ == "__main__":
    size = int(input("Entrez le nombre de cercle : "))
    card = LoyaltyCard(size)

    for i in range(size):
        while True:
            response = input(f"Entrez 'oui' ou 'non' pour le cercle {i + 1} : ").lower()
            if response not in ["oui", "non"]:
                print("Réponse invalide. Veuillez entrer 'oui' ou 'non'.")
            else:
                if response == "oui":
                    card.add_tampon(i)
                break

    card.show_tampons()