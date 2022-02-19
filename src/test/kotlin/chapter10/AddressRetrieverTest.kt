package chapter10

import chapter10.util.Http
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class AddressRetrieverTest {

    @Mock private lateinit var http: Http
    @InjectMocks private lateinit var retriever: AddressRetriever

    @Before
    fun createRetriever() {
        retriever = AddressRetriever()
        MockitoAnnotations.openMocks(this)  // 교재 코드에서는 initMocks(this)
    }

    @Test
    fun answersAppropriateAddressForValidCoordinates() {
        // 테스트의 기대 사항들을 설정
        `when`(http.get(contains("lat=38.000000&lon=-104.000000")))
            .thenReturn(    // 기대 사항이 충족되었을 때 처리
            "{\"address\": {" +
                    "\"house_number\":\"324\"," +
                    "\"road\":\"North Tejon Street\"," +
                    "\"city\": \"Colorado Springs\"," +
                    "\"state\": \"Colorado\"," +
                    "\"postcode\": \"80903\"," +
                    "\"country_code\": \"us\"}" +
                    "}"
        )

        val address = retriever.retrieve(38.0, -104.0)

        assertThat(address.houseNumber, equalTo("324"))
        assertThat(address.road, equalTo("North Tejon Street"))
        assertThat(address.city, equalTo("Colorado Springs"))
        assertThat(address.state, equalTo("Colorado"))
        assertThat(address.zip, equalTo("80903"))
    }
}