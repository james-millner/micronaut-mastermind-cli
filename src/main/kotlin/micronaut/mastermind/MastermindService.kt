package micronaut.mastermind

fun generateCode(): List<Peg> =
        availableColours.shuffled()
                .subList(0, 4)
                .map { Peg(it) }


fun guessSelection(playerPegChoice: List<Peg>, secretCode: List<Peg>): MastermindResult {

    val matchedResults = getMatchResults(playerPegChoice, secretCode)

    val score = matchedResults
            .map { createScoreForPeg(it.first, it.second) }
            .toList()
            .sum()

    return MastermindResult(score = score, code = secretCode)
}

fun getMatchResults(playerPegChoice: List<Peg>, secretCode: List<Peg>): List<Pair<Boolean, Boolean>> {
    val coloursSeen = emptyList<Colour>().toMutableList()

    return playerPegChoice.mapIndexed { index, peg ->
        val matchedPosition = secretCode[index].colour == peg.colour
        var matchedColour = secretCode.contains(peg)

        when {
            matchedColour && !coloursSeen.contains(peg.colour) -> peg.colour?.let { coloursSeen.add(it) }
            else -> matchedColour = false
        }

        Pair(matchedPosition, matchedColour)
    }.toList()
}

fun createScoreForPeg(pegPosition: Boolean, pegSameColour: Boolean) =
        0.let { if (pegPosition) it.plus(1) else 0 }
                .let { if (pegSameColour) it.plus(1) else it }
