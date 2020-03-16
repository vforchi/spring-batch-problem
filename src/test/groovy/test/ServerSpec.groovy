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
		def response = restTemplate.getForEntity("/test", null, String)

		then:
		response.status == HttpStatus.OK.value()
	}
	
}
