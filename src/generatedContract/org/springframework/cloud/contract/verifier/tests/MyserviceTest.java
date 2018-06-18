package org.springframework.cloud.contract.verifier.tests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;
import pl.rn.scc.MvcTest;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

public class MyserviceTest extends MvcTest {

	@Test
	public void validate_shouldAddUser() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"name\":\"8ficIXptvnB4Gr14WUyC\",\"age\":1990508546,\"address\":{\"city\":\"wawa\",\"type\":\"A\"}}");

		// when:
			ResponseOptions response = given().spec(request)
					.put("/user");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['age']").isEqualTo(1990508546);
			assertThatJson(parsedJson).field("['name']").isEqualTo("8ficIXptvnB4Gr14WUyC");
	}

}
