package Random;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
public class playingWJson {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		/**USE-CASE Need to correct the names in an incoming JSON as per a pre-defined Dictionary.**/ 
		
				//  JSON String -- INPUT  
//						     {
//						     "Department" : "BSDK" ,
//						     "Names " : ["zoravar","hey234","yesh"],
//							 "Kachra" : ["c1","c2","c3","c4"]	
//						     }
					     		     
		     
		     String str2 = "{\"Department\" : \"BSDK\" ,\r\n" + 
		     		"\"Names\" : [\"zoravar\",\"hey234\",\"yesh\"],\r\n" + 
		     		"\"Kachra\" : [\"c1\",\"c2\",\"c3\",\"c4\"]}";
		
		     	//Mapping the JSON string(Input) to a Hashmap of type<String,Object> because
		     	//key will always be a String but the value can be a single string or further a List therefore took it as Object type.
		     	//Will be using object mapper class from jackson.databind package
		     
			Map<String, Object> jsonMap = new ObjectMapper().readValue(str2,HashMap.class);
			
				//Iterating the Map
			for (String key : jsonMap.keySet()) {
					
				//Checking if the key is "Names" and its value is a list
			    if(key.toString().equals("Names")) {
					if(jsonMap.get(key).getClass().equals(ArrayList.class)) {
						
				    	//putting value against "Names" key, the value will be a list of processed strings(ie Names) returned by the function replacingWaala 
				    	jsonMap.put(key, replacingWaala((List)jsonMap.get(key))); //Type conversion -from object to List type
				    	
				    }
			    }
			    	
			}
			
			//Converting back the Map to a JSON object using a class JSONObject from net.sf.json package
			JSONObject j = new JSONObject();
			j.putAll(jsonMap);
			System.out.println(j.toString());	
	  } 
	  
	  public static List<String> replacingWaala(List<String> names) {

		  List<String> dict = new ArrayList<String>();  //Dictionary
		     dict.add("Arup");
		     dict.add("Ubhi");
		     dict.add("Yash");
		     dict.add("Hey123");
		     dict.add("Shrey");
		     dict.add("Shubham");
		     dict.add("Sidharth");
		     dict.add("Jorawar");
		     dict.add("Aggarwal");
		     
		  for(int i =0;i<3;i++) {
			  names.set(i, jaadu(dict,names.get(i)));
	      }
		  return names;
		  
	  }
	  
	  
	  public static String jaadu(List<String> collection, String name) {
	      int distance = Integer.MAX_VALUE;
	      String closest = null;
	      for (String compareObject : collection) {
	          int currentDistance = StringUtils.getLevenshteinDistance(compareObject, name);
	          if(currentDistance < distance) {
	              distance = currentDistance;
	              closest = compareObject;
	          }
	      }
	      return closest;
	  }

}
