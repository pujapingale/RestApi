import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class FileReadingDemo {

	public static String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
	@Test
	public static void testData() throws IOException {

		String body = generateStringFromResource("D:\\restapi\\CreateJson.txt");
		
		RestAssured.baseURI = "http://216.10.245.166";

		String createUrl = "Library/Addbook.php";

		String getUrl = "/Library/GetBook.php?AuthorName=%s";

		String deleteUrl = "/Library/DeleteBook.php";
		
		String isbn = "bcd";
		String aisle = "26062006";
		String actual = isbn + "" + aisle;
		System.out.println(actual);
		String body1 = String.format(body, isbn, aisle);
		System.out.println(body1);

		//Using Post Method
		
		String response = given().log().all().header("Content-Type", "application/json").body(body1)
				.when().post(createUrl)
				.then().log().all().assertThat().statusCode(200).body("ID", equalTo(actual)).extract().body().asString();
		
		System.out.println(response);
		
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
		

		//Delete book using Post method.
				String ID = js1.getString("ID");
				String deleteBody = generateStringFromResource("D:\\restapi\\DeleteID.txt");
				String body2 = String.format(deleteBody, ID);
				String response1 =given().log().all().header("Content-Type", "application/json").body(body2)
						.when().post(deleteUrl).then().log()
							.all().assertThat().statusCode(200).extract().body().asString();
					
	}

}
