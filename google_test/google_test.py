num_to_words = {
    0: 'Zero', 1: 'One', 2: 'Two', 3: 'Three', 4: 'Four', 5: 'Five', 6: 'Six', 7: 'Seven', 8: 'Eight', 9: 'Nine',
    10: 'Ten', 11: 'Eleven', 12: 'Twelve', 13: 'Thirteen', 14: 'Fourteen', 15: 'Fifteen', 16: 'Sixteen',
    17: 'Seventeen', 18: 'Eighteen', 19: 'Nineteen', 20: 'Twenty', 30: 'Thirty', 40: 'Forty', 50: 'Fifty',
    60: 'Sixty', 70: 'Seventy', 80: 'Eighty', 90: 'Ninety'
}

def number_to_words(n):
    if n == 0:
        return num_to_words[0]

    def words_below_1000(num):
        if num < 20:
            return num_to_words[num]
        elif num < 100:
            tens, below_ten = divmod(num, 10)
            return num_to_words[tens * 10] + ('' if below_ten == 0 else ' ' + num_to_words[below_ten])
        else:
            hundreds, below_hundred = divmod(num, 100)
            return num_to_words[hundreds] + ' Hundred' + ('' if below_hundred == 0 else ' ' + words_below_1000(below_hundred))

    result = ''
    billions, n = divmod(n, 10**9)
    millions, n = divmod(n, 10**6)
    thousands, n = divmod(n, 10**3)

    if billions > 0:
        result += words_below_1000(billions) + ' Billion '
    if millions > 0:
        result += words_below_1000(millions) + ' Million '
    if thousands > 0:
        result += words_below_1000(thousands) + ' Thousand '
    if n > 0:
        result += words_below_1000(n)

    return result.strip()

print(number_to_words(23456743543))
