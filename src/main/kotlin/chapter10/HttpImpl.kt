package chapter10

import chapter10.util.Http
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

class HttpImpl: Http {
    override fun get(url: String): String {
        val client: CloseableHttpClient = HttpClients.createDefault()
        val request = HttpGet(url)
        val response = client.execute(request)
        response.use { res ->
            val entity = res.entity
            return EntityUtils.toString(entity)
        }
    }

}
