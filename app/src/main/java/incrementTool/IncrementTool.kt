package incrementTool

class IncrementTool {

    private val sequenceOfChars = listOf<Char>(
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

//    fun incrementValue(valueToIncrement: String): String {
//
//        var changedValue = valueToIncrement
//
//        for (i in valueToIncrement.length..0 step -1) {
//
//            if (valueToIncrement[i - 1] == 'Z') {
//                changedValue = changedValue.substring(0, i) + changedValue.substring(i + 1)
//            } else {
//            }
//        }
//        return "increment"
//    }
//
//    fun decrementValue(valueToDecrement: String): String {
//        return "decrement"
//    }
//
//
//    private fun incrementSymbol(symbolToIncrement: Char): Char {
//
//        var symbolToChange = symbolToIncrement
//
//        for (i in 0..sequenceOfChars.size) {
//            if (sequenceOfChars[i].uppercase() == symbolToChange.toString()) {
//                if (i + 1 == sequenceOfChars.size) {
//                    symbolToChange = sequenceOfChars[0]
//                    break
//                } else {
//                    symbolToChange = sequenceOfChars[i + 1]
//                }
//            }
//        }
//        return symbolToChange
//    }
}