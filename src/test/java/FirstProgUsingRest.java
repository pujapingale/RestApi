import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class FirstProgUsingRest {
	@Test
	public void httpPostMethod(){

	    //Base URL
	    RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

	    //endpoints related to employees

	    String createURL = "/create";

	    String deleteUrl = "/delete/{id}";

	    String getAllEmployeesUrl = "/employees";


	    //API Body
	    String apiBody = "{\"name\":\"puja\",\"salary\":\"600000\",\"age\":\"28\"}";
	    System.out.println(apiBody);
	    given().log().all().header("Content-Type", "application/json").body(apiBody)
	            .when().post(createURL)
	                    .then().log().all().assertThat().statusCode(200);

	}

}
