package com.example.data.remote

import android.content.Context
import com.example.data.R
import okhttp3.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MockInterceptorPokemonSpecies(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = Response.Builder().protocol(Protocol.HTTP_1_0).code(200).request(chain.request())
        addResponse(response)
        return  response.build()
    }

    private fun addResponse(response: Response.Builder) {
        val mockJson = getMockJSONResource(R.raw.pokemon_species_jigglypuff, context)
        response.message(mockJson)
        response.body(ResponseBody.create(MediaType.get("application/json"),mockJson))
    }

    private fun getMockJSONResource(pokemon: Int, context: Context): String {
        val inputStream = context.resources.openRawResource(pokemon)
        val br = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?

        line = br.readLine()

        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close()
        return sb.toString()
    }

}