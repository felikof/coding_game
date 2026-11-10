def validity(chaine):
    for char in chaine:
        if char not in "atgc":
            return False
    return len(chaine) > 0

def saisie():
    chaine = input("Entrez votre chaine : ")
    if validity(chaine):
        return chaine
    else:
        print("La chaine n'est pas valide")
        return saisie()

def proportion():
    chaine = input("Entrez votre chaine d'ADN: ")
    sequence = input("Entrez votre sequence : ")
    if validity(chaine) and validity(sequence):
        print("Chaine :", chaine)
        print("Sequence :", sequence)
        result = ((chaine.count(sequence)*len(sequence)) / len(chaine))*100
        return print("Il y'a", result, "% de", sequence, "dans votre chaine.")
    else:
        print("La chaine et ou la sequence ne sont pas valide")

if __name__ == '__main__':
    print("Bienvenue dans notre programme de recherche de sequence dans une chaine.")
    proportion()