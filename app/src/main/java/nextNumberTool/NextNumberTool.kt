package nextNumberTool

class NextNumberTool {

    private val sequenceOfChars = listOf(
        '0',
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z'
    )

    private val minimumLimitReached = "Minimum limit reached..."
    private val maximumLimitReached = "Maximum limit reached..."

    private val lastSymbolInSequence = sequenceOfChars[sequenceOfChars.size - 1]
    val firstSymbolInSequence = sequenceOfChars[0].toString()


    fun incrementValue(valueToIncrement: String): String {

        var incrementedValue = valueToIncrement.uppercase()

        if (areAllCharsInValueEqual(incrementedValue, lastSymbolInSequence)) {
            return maximumLimitReached
        } else if (incrementedValue == maximumLimitReached.uppercase()) {
            return firstSymbolInSequence
        }

        for (i in valueToIncrement.length downTo 0) {
            if (valueToIncrement[i - 1] == lastSymbolInSequence) {
                incrementedValue =
                    incrementedValue.replaceRange(i - 1, i, firstSymbolInSequence)
            } else {
                val changedSymbol = incrementSymbol(valueToIncrement[i - 1])
                incrementedValue =
                    incrementedValue.replaceRange(i - 1, i, changedSymbol.toString())
                break
            }
        }
        return incrementedValue
    }

    fun decrementValue(valueToDecrement: String): String {

        var changedValue = valueToDecrement.uppercase()

        if (areAllCharsInValueEqual(changedValue, sequenceOfChars[0])) {
            return minimumLimitReached
        } else if (changedValue == minimumLimitReached.uppercase()) {
            return lastSymbolInSequence.toString()
        }

        for (i in valueToDecrement.length downTo 0) {
            if (valueToDecrement[i - 1] == sequenceOfChars[0]) {
                changedValue =
                    changedValue.replaceRange(
                        i - 1,
                        i,
                        sequenceOfChars[sequenceOfChars.size - 1].toString()
                    )
            } else {
                val changedSymbol = decrementSymbol(valueToDecrement[i - 1])
                changedValue = changedValue.replaceRange(i - 1, i, changedSymbol.toString())
                break
            }
        }
        return changedValue
    }

    private fun incrementSymbol(symbolToIncrement: Char): Char {

        var symbolToChange = '0'

        var currentSymbolFromIteration: String

        for (i in 0..sequenceOfChars.size - 1) {

            currentSymbolFromIteration = sequenceOfChars[i].uppercase()

            if (currentSymbolFromIteration == symbolToIncrement.toString()) {
                if (i + 1 == sequenceOfChars.size) {
                    symbolToChange = sequenceOfChars[i]
                    break
                } else {
                    symbolToChange = sequenceOfChars[i + 1]
                    break
                }
            }
        }
        return symbolToChange
    }

    private fun decrementSymbol(symbolToDecrement: Char): Char {

        var symbolToChange = 'Z'
        var currentSymbolFromIteration: String

        for (i in 0..sequenceOfChars.size - 1) {
            currentSymbolFromIteration = sequenceOfChars[i].uppercase()
            if (currentSymbolFromIteration == symbolToDecrement.toString()) {
                val neededPosition = i - 1
                if (neededPosition == -1) {
                    symbolToChange = sequenceOfChars[sequenceOfChars.size - 1]
                    break
                } else {
                    symbolToChange = sequenceOfChars[neededPosition]
                    break
                }
            }
        }
        return symbolToChange
    }

    private fun areAllCharsInValueEqual(value: String, char: Char): Boolean {

        for (i in value.uppercase()) {
            if (i != char) {
                return false
            }
        }
        return true
    }
}