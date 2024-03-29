package micronaut.mastermind

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MastermindModelsTest {

    @Test
    fun testAvailableColours() = assertTrue(availableColours.size == 6)

    @Test
    fun testIsSuccessfulGuess() = assertTrue(MastermindResult(score = 8, code = emptyList()).isSuccessfulGuess())

    @Test
    fun testIsUnsuccessfulGuess() = assertFalse(MastermindResult(score = 4, code = emptyList()).isSuccessfulGuess())
}
