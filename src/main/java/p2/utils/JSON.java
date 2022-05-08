package p2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class JSON {
	Map<String, String> lista;

	public JSON() {
		this.lista = new HashMap<String, String>();
	}

	public JSON(String json_to_parse) {
		this.lista = new HashMap<String, String>();
		parse(json_to_parse);
	}

	public void parse(String json_to_parse) {
		String[] json_array = json_to_parse.split("\\{");
		for (int i = 1; i < json_array.length; i++) {
			String[] json_object = json_array[i].split("\\}");
			String[] json_object_array = json_object[0].split(",");
			for (int j = 0; j < json_object_array.length; j++) {
				String[] json_object_array_pair = json_object_array[j].replaceAll("\"","").replaceAll(" ","").replaceAll("\n","").split(":");
				if (json_object_array_pair.length == 2)
					this.lista.put(json_object_array_pair[0], json_object_array_pair[1]);
			}
		}
	}   

	public String get(String hashIndex) {
		return this.lista.get(hashIndex);
	}

  public String getString(String hashIndex) {
    return this.lista.get(hashIndex);
  }

  public Integer getInteger(String hashIndex) {
    return Integer.parseInt(this.lista.get(hashIndex));
  }

  public double getDouble(String hashIndex) {
    return Double.parseDouble(this.lista.get(hashIndex));
  }

  public float getFloat(String hashIndex) {
    return Float.parseFloat(this.lista.get(hashIndex));
  }

  public boolean getBoolean(String hashIndex) {
    return Boolean.parseBoolean(this.lista.get(hashIndex));
  }

	public Map<String, String> getAll(){
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

  public static Map<String, String> getParams(String body){
		JSON myJson = new JSON(body);
		return myJson.getAll();
	}
}
