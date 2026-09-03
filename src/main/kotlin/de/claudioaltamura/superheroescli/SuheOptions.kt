package de.claudioaltamura.superheroescli

import picocli.CommandLine
import picocli.CommandLine.*
import picocli.CommandLine.Model.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "suhe",
    version = ["0.1"],
    mixinStandardHelpOptions = true,
    description = ["A Superheroes CLI"]
)
class SuheOptions : Callable<Int> {
    @Spec
    lateinit var spec: CommandSpec

    @Parameters(index = "0", arity = "0..1", description = ["Search query for the request. (Example : Spider-Man)"])
    private var searchQuery : String? = null

    @ArgGroup(exclusive = true, multiplicity = "1")
    private lateinit var exclusive: Exclusive

    internal class Exclusive {
        @Option(names = ["-c", "--characters"], required = true, description = ["Search for characters"])
        var characters = false

        @Option(names = ["-p", "--cities"], required = true, description = ["Search for cities"])
        var cities = false
    }

    override fun call(): Int {
        if (exclusive.characters)
            PrettyPrinter(spec).print(SuheApi.getSuperhero(searchQuery))
        if (exclusive.cities)
            PrettyPrinter(spec).print(SuheApi.getCity(searchQuery))

        return 0
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            exitProcess(CommandLine(SuheOptions()).execute(*args))
        }
    }
}