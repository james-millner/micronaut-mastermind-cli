package micronaut.mastermind

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(name = "micronaut-mastermind", description = ["..."],
        mixinStandardHelpOptions = true)
class MicronautMastermindCommand : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose: Boolean = false

    val MAX_ATTEMPTS = 12
    val MAX_SCORE = 8

    override fun run() {

        val secretCode = generateCode()

        println(" ___      ___       __        ________  ___________  _______   _______   ___      ___   __    _____  ___   ________   \n" +
                "|\"  \\    /\"  |     /\"\"\\      /\"       )(\"     _   \")/\"     \"| /\"      \\ |\"  \\    /\"  | |\" \\  (\\\"   \\|\"  \\ |\"      \"\\  \n" +
                " \\   \\  //   |    /    \\    (:   \\___/  )__/  \\\\__/(: ______)|:        | \\   \\  //   | ||  | |.\\\\   \\    |(.  ___  :) \n" +
                " /\\\\  \\/.    |   /' /\\  \\    \\___  \\       \\\\_ /    \\/    |  |_____/   ) /\\\\  \\/.    | |:  | |: \\.   \\\\  ||: \\   ) || \n" +
                "|: \\.        |  //  __'  \\    __/  \\\\      |.  |    // ___)_  //      / |: \\.        | |.  | |.  \\    \\. |(| (___\\ || \n" +
                "|.  \\    /:  | /   /  \\\\  \\  /\" \\   :)     \\:  |   (:      \"||:  __   \\ |.  \\    /:  | /\\  |\\|    \\    \\ ||:       :) \n" +
                "|___|\\__/|___|(___/    \\___)(_______/       \\__|    \\_______)|__|  \\___)|___|\\__/|___|(__\\_|_)\\___|\\____\\)(________/  \n\n")

        // business logic here
        if (verbose) {
            print("Mastermind version 0.1")
            return
        }

        var successfulGuess = false
        var iteration = 0

        while (iteration < MAX_ATTEMPTS) {

            println("Please enter your code in CSV format. Attempt: ${iteration+1}")

            val colours = readLine()!!.split(",")
                    .map { it.toUpperCase() }
                    .map { Peg(colour = Colour.valueOf(it)) }

            val mastermindResult = guessSelection(colours, secretCode)

            if (mastermindResult.score == MAX_SCORE) {
                println("YOU WIN!")
                println("The secret code was: ")
                mastermindResult.code
                        .forEachIndexed { index, peg -> println("$index: ${peg.colour!!.name}") }

                successfulGuess = true
                break
            }


            println(mastermindResult.score)
            iteration++
        }

        if(iteration == 12 && !successfulGuess) {
            println("Game over! The code was:")
            println(secretCode.toTypedArray())
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(MicronautMastermindCommand::class.java, *args)
        }
    }
}
