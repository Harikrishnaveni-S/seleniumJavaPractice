package frameworkDesign.testData;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	public List<HashMap<String, String>> getMapFromJsonData() throws IOException
	{
		
		//json to string 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir"+"\\src\\test\\java\\frameworkDesign\\testData\\purchaseOrder.json")),StandardCharsets.UTF_8);
		
		//string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> testData = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		
		return testData;
	}
}
