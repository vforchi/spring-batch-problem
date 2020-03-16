package test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerSpec extends Specification {
	
	@Autowired
	TestRestTemplate restTemplate

	void "Submit Job"() {
		when:
		def start = System.currentTimeMillis()
		def response = restTemplate.getForEntity("/test", null, String)
		def elapsed = System.currentTimeMillis() - start

		then:
		response.status == HttpStatus.OK.value()
		elapsed > 10_000
	}
	
}
