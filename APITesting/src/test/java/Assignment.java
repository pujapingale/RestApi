import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Assignment {

	@Test
	public void assignmentDemo() {

		RestAssured.baseURI = "http://216.10.245.166";

		String createUrl = "Library/Addbook.php";
		
		String getUrl="/Library/GetBook.php?AuthorName=%s";
		
		String getUrlUsingId="Library/GetBook.php?ID=%s";
		
		String deleteUrl="/Library/DeleteBook.php";

		JsonPath js = new JsonPath(TestData.getCourseJson());

		String isbn = "bcd";
		String aisle = "26062001";
		String actual = isbn + "" + aisle;
		System.out.println(actual);
		String body1 = String.format(TestData.getCourseJson(), isbn, aisle);
		System.out.println(body1);

		//Using Post Method
		String response = given().log().all().header("Content-Type", "application/json").body(body1)
			.when().post(createUrl).then().log()
				.all().assertThat().statusCode(200).body("ID", equalTo(actual)).extract().body().asString();
		
		JsonPath js1 = new JsonPath(response);
		
		String expected = js1.getString("ID");
	
		Assert.assertEquals(actual, expected);
		
		//Get book records whose author is "John foe".
	
		String author="John foe";
		String authorname=String.format(getUrl, author);
		System.out.println("Author url is:"+authorname);
		given().log().all().header("Content-Type", "application/json")
				.when().get(authorname).then().log()
					.all().assertThat().statusCode(200);
		
		//Get Book record with specific id.
		JsonPath js2 = new JsonPath(response);
		String id = js2.getString("ID");
		String geturlusingid = String.format(getUrlUsingId, id);
		System.out.println("getUrlUsingId url is:"+geturlusingid);
		given().log().all().header("Content-Type", "application/json")
		.when().get(geturlusingid).then().log()
			.all().assertThat().statusCode(200);
		
		//Delete book using Post method.
		
		String ID = js1.getString("ID");
		String body2 = String.format(TestData.getID(), ID);
		System.out.println("body is: "+body2);
			String response1 =given().log().all().header("Content-Type", "application/json").body(body2)
				.when().post(deleteUrl).then().log()
					.all().assertThat().statusCode(200).extract().body().asString();
			
		
	}
	
	

}
