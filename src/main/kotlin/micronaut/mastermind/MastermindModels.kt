package micronaut.mastermind

import com.sun.org.apache.xpath.internal.operations.Bool

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

fun MastermindResult.isSuccessfulGuess(): Boolean =
        if (this.score == MAX_SCORE) {
            println("YOU WIN!")
            println("The secret code was: ")
            this.code
                    .forEachIndexed { index, peg -> println("$index: ${peg.colour!!.name}") }

            true
        } else {
            false
        }

class MastermindGame(
        val generateCode: () -> List<Peg>,
        val playTurn: (List<Peg>, List<Peg>) -> MastermindResult
)
