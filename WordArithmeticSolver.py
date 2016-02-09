import random

numWords = 0
words = []
unusedDigits = []
operator = ''

print 'Word Arithmetic Solver\n'

# Read input
numWords = int(raw_input('Number of words: '))

for i in range(numWords):
	words.append(raw_input('Next word: ').lower())

while len(operator) != 1 or operator not in '+-/*':
	operator = raw_input('Enter the operator: ')

unusedString = raw_input('Enter unused numbers (space separated): ')
unusedDigits = [int(numStr) for numStr in unusedString.split()]

# Double check input
if len(words) < 3 or (len(words) >= 4 and operator in '/-'):
	print 'Sorry, you must have typed something wrong.'
	exit()

print '\nWorking...'

# Solve the puzzle
solved = False
counter = 0
wordNumbers = []
while not solved:
	if counter % 100 == 0:
		print 'Try', counter

	# Replace all letters with random digits
	remainingDigits = [False] * 10
	replacedWords = list(words)
	for word in words:
		for letter in word:
			if letter.isalpha():
				# Pick a random digit
				randDigit = random.randint(0, 9)
				while remainingDigits[randDigit]:
					randDigit = random.randint(0, 9)

				# Replace the letter in all words
				replacedWords = [word2.replace(letter, str(randDigit)) for word2 in replacedWords]

	wordNumbers = [int(word) for word in replacedWords]

	if operator == '+':
		solved = (sum(wordNumbers[:-1]) == wordNumbers[-1])
	elif operator == '-':
		solved = (wordNumbers[0] - wordNumbers[1] == wordNumbers[2])
	elif operator == '*':
		curTotal = 1
		for i in range(len(wordNumbers) - 1):
			curTotal *= wordNumbers[i]
		solved = (curTotal == wordNumbers[-1])
	else:
		solved = (wordNumbers[1] != 0 and wordNumbers[0] / wordNumbers[1] == wordNumbers[2])

	counter += 1

print wordNumbers