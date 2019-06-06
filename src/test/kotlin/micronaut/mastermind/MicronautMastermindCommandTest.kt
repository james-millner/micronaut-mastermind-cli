package micronaut.mastermind

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import java.io.ByteArrayOutputStream
import java.io.PrintStream


internal class MicronautMastermindCommandTest {

    private val ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)

    @Test
    fun `When the CLI receives an operation to run with a single argument, it succeeds`() {
        val baos = ByteArrayOutputStream()
        System.setOut(PrintStream(baos))

        val args = arrayOf("-v")
        PicocliRunner.run(MicronautMastermindCommand::class.java, ctx, *args)

        assertTrue(baos.toString().contains("Mastermind version 0.1"))
    }

}