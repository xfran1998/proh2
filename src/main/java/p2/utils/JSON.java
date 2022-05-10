package p2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class JSON {
	Map<Object, Object> lista;

	public JSON() {
		this.lista = new HashMap<Object, Object>();
	}

	public JSON(String json_to_parse) {
		this.lista = new HashMap<Object, Object>();
		parse(json_to_parse);
	}

	public void parse(String json_to_parse) {
		// convert JSON to Map<Object, Object>
		json_to_parse.replace("{", "");
		String[] json_array = json_to_parse.split(",");
		
		
		for (String json_pair : json_array) {
			System.out.println("json_pair: " + json_pair);
			String[] json_pair_array = json_pair.split(":");
			System.out.println("json_pair: " + json_pair);
			String key = json_pair_array[0].replace("\"", "");
			System.out.println("key: " + key);
			String value = json_pair_array[1].replace("\"", "");
			System.out.println("value: " + value);
			
			lista.put(key, value);
		}
	}   

	public Object get(String hashIndex) {
		return this.lista.get(hashIndex);
	}

  public String getString(String hashIndex) {
	  System.out.println((String)this.lista.get(hashIndex));
    return (String)this.lista.get(hashIndex);
  }

  public Integer getInteger(String hashIndex) {
    return Integer.parseInt((String)this.lista.get(hashIndex));
  }

  public double getDouble(String hashIndex) {
    return Double.parseDouble((String)this.lista.get(hashIndex));
  }

  public float getFloat(String hashIndex) {
    return Float.parseFloat((String)this.lista.get(hashIndex));
  }

  public boolean getBoolean(String hashIndex) {
    return Boolean.parseBoolean((String)this.lista.get(hashIndex));
  }

	public Map<Object, Object> getAll(){
		return this.lista;
	}
	
	public int size() {
		return this.lista.size();
	}

	public String toString() {
		String json_string = "{";
		for (int i = 0; i < lista.size(); i++) {
			json_string += "\"" + lista.get(i) + "\"";
			if (i < lista.size() - 1) {
				json_string += ",";
			}
		}
		json_string += "}";
		return json_string;
	}

  public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

  public static Map<Object, Object> getParams(String body){
		JSON myJson = new JSON(body);
		return myJson.getAll();
	}
}
