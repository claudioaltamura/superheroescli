package de.claudioaltamura.superheroescli

import kotlinx.serialization.Serializable

object SuheApi {

    fun getSuperhero(query : String?) : Response<Superhero> {
        return Response(0, null,null, listOf(Superhero("Spider-Man")))
    }

    fun getCity(query : String?) : Response<City> {
        return Response(0, null,null, listOf(City("New York")))
    }

    private fun queryString(query: String?) = if(query == null)  "" else  "?search=${query}"
}

@Serializable sealed class Data
@Serializable data class Response<Data>(val count: Int, val next : String?, val previous : String?, val results : List<Data>)
@Serializable data class City(val name: String) : Data()
@Serializable data class Superhero(val name: String) : Data()