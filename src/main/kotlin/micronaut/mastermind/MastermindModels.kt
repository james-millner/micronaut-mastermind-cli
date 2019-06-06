package micronaut.mastermind

val availableColours = setOf(Colour.RED, Colour.YELLOW, Colour.GREEN, Colour.BLUE, Colour.ORANGE, Colour.PURPLE)

enum class Colour {
    RED,
    YELLOW,
    GREEN,
    BLUE,
    ORANGE,
    PURPLE,
}

data class Peg(var colour: Colour?)

data class MastermindResult(val score: Int, val code: List<Peg>)

fun generateCode(): List<Peg> =
    availableColours.shuffled()
        .subList(0, 4)
        .map { Peg(it) }


fun guessSelection(playerPegChoice: List<Peg>, secretCode: List<Peg>): MastermindResult {

    var matchedResults = emptyList<Pair<Boolean, Boolean>>().toMutableList()
    val guessedCode = emptyList<Peg>().toMutableList()

    playerPegChoice.forEachIndexed { index, peg ->
        val matchedPosition = secretCode[index].colour == peg.colour
        val matchedColour = secretCode.contains(peg)

        if (matchedColour) {
            guessedCode.add(peg)
            secretCode[index].colour = null //Set an unused colour to take this out of future calculations
        }

        matchedResults.add(Pair(matchedPosition, matchedColour))
    }

    val score = matchedResults
        .map { createScoreForPeg(it.first, it.second) }
        .toList()
        .sum()

    return MastermindResult(score = score, code = guessedCode)
}

fun createScoreForPeg(pegPosition: Boolean, pegSameColour: Boolean): Int {
    var score = 0

    if(pegPosition) {
        score += 1
    }

    if(pegSameColour) {
        score += 1
    }

    return score
}


