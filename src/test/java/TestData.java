import io.restassured.path.json.JsonPath;

public class TestData {
	
	public static String getCourseJson(){
		
	return "{\n" +
	"\"name\":\"Learn Appium Automation with Java\",\n"+
	 "\"isbn\":\"%s\",\n"+
	 "\"aisle\":\"%s\",\n"+
	 "\"author\":\"John foe\"\n"+
	 "}";
	}
	
	public static String getID() {
		return "{\n" +	 
			"\"ID\" :\"%s\"\n"+ 
				"}";

	}
}