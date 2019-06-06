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

    val coloursSeen = emptyList<Colour>().toMutableList()
    val matchedResults = emptyList<Pair<Boolean, Boolean>>().toMutableList()

    playerPegChoice.forEachIndexed { index, peg ->
        val matchedPosition = secretCode[index].colour == peg.colour
        var matchedColour = secretCode.contains(peg)

        when {
            matchedColour && !coloursSeen.contains(peg.colour) -> peg.colour?.let { coloursSeen.add(it) }
            else -> matchedColour = false
        }

        matchedResults.add(Pair(matchedPosition, matchedColour))
    }

    val score = matchedResults
        .map { createScoreForPeg(it.first, it.second) }
        .toList()
        .sum()

    return MastermindResult(score = score, code = secretCode)
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


