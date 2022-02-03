package chapter10

import chapter10.util.Http
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.IOException
import java.text.ParseException

class AddressRetriever(val http: Http) {

    @Throws(IOException::class, ParseException::class)
    fun retrieve(latitude: Double, longitude: Double): Address {
        val params = String.format("lat=%.6f&lon=%.6f", latitude, longitude)
        val response = http.get(
            "http://open.mapquestapi.com/nominatim/v1/reserve?format=json&"
                    + params
        )
        val obj: JSONObject = JSONParser().parse(response) as JSONObject

        val address: JSONObject = obj["address"] as JSONObject
        val country = address["country_code"]
        if (country!! != "us") {
            throw UnsupportedOperationException(
                "cannot support non-US addresses at this time"
            )
        }
        val houseNumber = address["house_number"] as String
        val road = address["road"] as String
        val city = address["city"] as String
        val state = address["state"] as String
        val zip = address["postcode"] as String
        return Address(houseNumber, road, city, state, zip)
    }
}