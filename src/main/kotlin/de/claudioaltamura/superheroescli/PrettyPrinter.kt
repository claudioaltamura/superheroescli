package de.claudioaltamura.superheroescli

import picocli.CommandLine
import picocli.CommandLine.Help.Ansi

const val asciiArt =
    """
  _________     .__           _________ .____    .___ 
 /   _____/__ __|  |__   ____ \_   ___ \|    |   |   |
 \_____  \|  |  \  |  \_/ __ \/    \  \/|    |   |   |
 /        \  |  /   Y  \  ___/\     \___|    |___|   |
/_______  /____/|___|  /\___  >\______  /_______ \___|
        \/           \/     \/        \/        \/    
"""

class PrettyPrinter(val spec : CommandLine.Model.CommandSpec) {
    fun <T : Data> print(response: Response<T>){

        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,green \uD83D\uDE80 Found ${response.count} total results for that query \uD83D\uDE80 |@")}
            ${if (response.count > 0) Ansi.AUTO.string("@|underline,green Showing ${response.results.size} results|@") else ""}
            
        """.trimIndent())

        response.results.map {
            when(it){
                is Superhero -> printSuperhero(it)
                is City -> printCity(it)
            }
        }
    }

    private fun printSuperhero(superhero: Superhero){
        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,yellow \uD83D\uDC7D ${superhero.name}|@")}
        """.trimIndent())
    }

    private fun printCity(city: City){
        spec.commandLine().out.println("""
            ${Ansi.AUTO.string("@|bold,yellow \uD83D\uDEE3 ${city.name}|@")}
        """.trimIndent())
    }
}