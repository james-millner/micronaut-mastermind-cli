package micronaut.mastermind

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.lang.IllegalArgumentException

const val MAX_ATTEMPTS = 12
const val MAX_SCORE = 8

@Command(name = "micronaut-mastermind", description = ["..."],
        mixinStandardHelpOptions = true)
class MicronautMastermindCommand : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose: Boolean = false

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

        var iteration = 0

        println("You can choose from the following colours:")
        availableColours.forEach{ println(it.name) }

        println("--------------------------------------------------------------------------------------------------------------------------------")

        while (iteration < MAX_ATTEMPTS) {

            try {

                println("Please enter your code in CSV format. Attempt: ${iteration + 1}")

                val colours = readLine()!!.split(",")
                        .map { it.toUpperCase() }
                        .map { Peg(colour = Colour.valueOf(it)) }

                val mastermindResult = guessSelection(colours, secretCode)

                if (mastermindResult.isSuccessfulGuess()) break

                println(mastermindResult.score)
                iteration++
            } catch (exc: IllegalArgumentException) {
                println("--------------------------------------------------------------------------------------------------------------------------------")
                println("The available colours are: ")
                availableColours.forEach{ println(it.name) }
                println("Please try again!")
                println("--------------------------------------------------------------------------------------------------------------------------------")
            }
        }

        if (iteration == 12) {
            println("Game over! The code was:")
            secretCode.forEach{ println(it) }
        }

    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(MicronautMastermindCommand::class.java, *args)
        }
    }
}
