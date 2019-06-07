package micronaut.mastermind

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MastermindServiceTest {

    @Test
    fun createScoreForPeg() =
        assertEquals(2, createScoreForPeg(pegPosition = true, pegSameColour = true))

    @Test
    fun createScoreForPegWithCorrectPosition() =
        assertEquals(1, createScoreForPeg(pegPosition = true, pegSameColour = false))

    @Test
    fun createScoreForNoMatches() =
            assertEquals(0, createScoreForPeg(pegPosition = false, pegSameColour = false))

    @Test
    fun testGuessSelectionForOneFullMatchDupeColours() {
        val code = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val playerPegChoice = listOf(Peg(Colour.RED), Peg(Colour.RED), Peg(Colour.RED), Peg(Colour.RED))
        val mastermind = guessSelection(playerPegChoice, code)
        assertEquals(2, mastermind.score)
    }

    @Test
    fun testGuessSelectionPositionForOneHalfMatches() {
        val code = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val playerPegChoice = listOf(Peg(Colour.RED), Peg(Colour.GREEN), Peg(Colour.YELLOW), Peg(Colour.BLUE))
        val mastermind = guessSelection(playerPegChoice, code)

        assertEquals(3, mastermind.score)
    }

    @Test
    fun testGuessSelectionPositionForFullMatches() {
        val code = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val playerPegChoice = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val mastermind = guessSelection(playerPegChoice, code)

        assertEquals(8, mastermind.score)
    }

    @Test
    fun testGenerateCode() = assertEquals(4, generateCode().size)

    @Test
    fun testGetMatchResultsExactMatch() {
        val code = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val playerPegChoice = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))

        val expectedResult = listOf(Pair(true,true),Pair(true,true),Pair(true,true),Pair(true,true))

        assertEquals(expectedResult, getMatchResults(playerPegChoice, code))
    }

    @Test
    fun testGetMatchResultsOneCompleteMiss() {
        val code = listOf(Peg(Colour.RED), Peg(Colour.YELLOW), Peg(Colour.ORANGE), Peg(Colour.PURPLE))
        val playerPegChoice = listOf(Peg(Colour.RED), Peg(Colour.GREEN), Peg(Colour.ORANGE), Peg(Colour.PURPLE))

        val expectedResult = listOf(Pair(true,true),Pair(false,false),Pair(true,true),Pair(true,true))

        assertEquals(expectedResult, getMatchResults(playerPegChoice, code))
    }
}
