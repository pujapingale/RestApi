import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.equalTo;

public class RestApiUsingAssertion {

	@Test
	public void httpPostMethod() {

		// Base URL
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// endpoints related to employees

		String createURL = "/create";

		String deleteUrl = "/delete/%s";

		String getAllEmployeesUrl = "/employees";

		String updateUrl = "/update/%s";

		// API Body
		String apiBody = "{\"name\":\"puja Pingale\",\"salary\":\"600000\",\"age\":\"28\"}";
		
		String response = given().log().all().header("Content-Type", "application/json").body(apiBody).when()
				.post(createURL).then().log().all().assertThat().statusCode(200).body("status", equalTo("success"))
				.extract().body().asString();

		// Get all employee details
		given().log().all().header("Content-Type", "application/json").when().get(getAllEmployeesUrl).then().log().all()
				.assertThat().statusCode(200);

		//Update record using put method
		String apiBody1 = "{\"name\":\"puja More\",\"salary\":\"600000\",\"age\":\"28\"}";
		JsonPath js = new JsonPath(response);
		String id = js.getString("id");
		System.out.println("id is:"+id);
		String update = String.format(updateUrl, id);
		System.out.println("updated url is"+update);
		given().log().all().header("Content-Type", "application/json").body(apiBody1)
		.when().put(update)
		.then().log().all().assertThat().statusCode(200);
		
		
		//Delete record
		//JsonPath js1 = new JsonPath(response);
		//String id1 = js1.getString("id");
		String id1="24";
		String delete1 = String.format(deleteUrl, id1);
		System.out.println("Deleted url is: "+delete1);
		given().log().all().header("Content-Type", "application/json")
		.when().delete(delete1)
		.then().log().all().assertThat().statusCode(200);
		System.out.println("Record deleted");
		
	}
}
