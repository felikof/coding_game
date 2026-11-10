class Crypteur:
    def __init__(self, word, key):
        self.word = word
        self.key = key
        
    def crypt_char(self, s):
        self.l = {chr(i): i - 97 for i in range(97, 123)}
        s = s.lower()
        if s in self.l:
            self.crypt = (self.l[s] * self.key) % 26
            #crypted = l.keys(crypt)
            self.crypted = [k for k, v in self.l.items() if v == self.crypt][0]
            return self.crypted

    def crypt_word(self):
        self.sb = []
        for char in self.word:
            if char.isalpha():
                self.sb.append(self.al(char))
        return ''.join(self.sb)
    
    def main(self):
        print("Crypteur de mot")
        self.word = input("Entrer le mot à crypter : ")
        self.key = int(input("Entrer la clé de cryptage : "))
        print("Mot crypté : ", self.crypt_word())

if __name__ == '__main__':
    crypteur = Crypteur("Bonjour", 3)
    crypteur.main()
