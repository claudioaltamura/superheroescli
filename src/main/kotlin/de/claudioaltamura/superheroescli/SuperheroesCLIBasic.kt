package de.claudioaltamura.superheroescli

import picocli.CommandLine
import picocli.CommandLine.Command
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(name= "sh", version= ["0.1"], mixinStandardHelpOptions = true, description = ["A Superheroes CLI"])
class SuperheroesCLIBasic : Callable<Int> {
    override fun call(): Int {
        println("I'm running!")
        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            exitProcess(CommandLine(SuperheroesCLIBasic()).execute(*args))
        }
    }
}