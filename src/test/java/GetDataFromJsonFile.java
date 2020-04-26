
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetDataFromJsonFile {
	
	 @Test
	    public void httpPostMethod() {
		 int total=0;
	        //Base URL
	        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

	        //endpoints related to employees

	        String createURL = "/create";

	        String deleteUrl = "/delete/{id}";

	        String getAllEmployeesUrl = "/employees";

	        //API Body
	        String apiBody = "{\"name\":\"puja\",\"salary\":\"600000\",\"age\":\"28\"}";

	        given().log().all().header("Content-Type", "application/json").body(apiBody)
	                .when().post(createURL)
	                        .then().log().all().assertThat().statusCode(200);


	       String response = "{\n" +
	               "    \"status\": \"success\",\n" +
	               "    \"data\": {\n" +
	               "        \"name\": \"puja\",\n" +
	               "        \"salary\": \"600000\",\n" +
	               "        \"age\": \"28\",\n" +
	               "        \"id\": 64\n" +
	               "    }\n" +
	               "}";

	        JsonPath js = new JsonPath(response);
	        int outputIdFromJSPath = js.getInt("data.id");
	        String nameFromResponse = js.getString("data.name");
	        System.out.println("Output for name is -> "+nameFromResponse);
	        System.out.println("Output for id is -> "+ outputIdFromJSPath);


	        /* Learning of JSON Array*/

	        JsonPath js2 = new JsonPath(Payload.getCourseJson());
	        int amount = js2.getInt("dashboard.purchaseAmount");
	        int lengthOfArray = js2.getInt("courses.size()");
	       // System.out.println(length);

	       
	        for (int j=0;j< lengthOfArray;j++){
	            System.out.println(js2.getString("courses["+j+"].title"));
	            int price = js2.getInt("courses["+j+"].price");
	            total = total+price;
	        }
	        System.out.println(total);
	        Assert.assertEquals(total, amount);


	    }


	}