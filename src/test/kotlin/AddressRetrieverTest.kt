import chapter10.AddressRetriever
import chapter10.util.Http
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import kotlin.test.fail

class AddressRetrieverTest {
    @Test
    fun answersAppropriateAddressForValidCoordinates() {
        val http: Http = object : Http {
            override fun get(url: String): String {

                if (!url.contains("lat=38.000000lon=-104.000000"))
                    fail("url" + url + "does not contain correct params")

                return "{\"address\": {" +
                        "\"house_number\":\"324\"," +
                        "\"road\":\"North Tejon Street\"," +
                        "\"city\": \"Colorado Springs\"," +
                        "\"state\": \"Colorado\"," +
                        "\"postcode\": \"80903\"," +
                        "\"country_code\": \"us\"}" +
                        "}"
            }
        }
        val retriever = AddressRetriever(http)
        val address = retriever.retrieve(38.0, -104.0)

        assertThat(address.houseNumber, equalTo("324"))
        assertThat(address.road, equalTo("North Tejon Street"))
        assertThat(address.city, equalTo("Colorado Springs"))
        assertThat(address.state, equalTo("Colorado"))
        assertThat(address.zip, equalTo("80903"))
    }
}