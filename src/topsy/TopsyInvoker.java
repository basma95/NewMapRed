package topsy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import topsy.SearchResponse;


public class TopsyInvoker {

	public static Map<String, Class> responseMap = new HashMap<String, Class>();
	private TopsyConfig topsyConfig;
	private Map<String, String> errorMessages = new HashMap<String, String>() {
		{
			put(
					"400",
					"Parameter check failed - indicates that a required " +
					"parameter is missing or a parameter has a value that is out of bounds. ");
			put("403", "Forbidden");
			put("404", "Action not supported");
			put("500", "Unexpected internal error");
			put("503", "Temporarily unavailable");
		}
	};

	public TopsyInvoker() {
		responseMap.put("search", SearchResponse.class);
		}

	public TopsyConfig getTopsyConfig() {
		return topsyConfig;
	}

	public void setTopsyConfig(TopsyConfig topsyConfig) {
		this.topsyConfig = topsyConfig;
	}

	public Object fetchData(String urlStr, String resource)
			throws Exception {
		if (topsyConfig.isSetProxy()) {
			System.setProperty("http.proxyHost", topsyConfig.getProxyHost());
			System.setProperty("http.proxyPort", topsyConfig.getProxyPort());
		}
		System.out.println(urlStr + topsyConfig.getApiKeyStr());
		URL url = null;

		try {
			url = new URL(urlStr + topsyConfig.getApiKeyStr());
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}

		HttpURLConnection conn = null;
		String json = "";
		try {
			conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				System.out.println("Failed to get profile detail for \""
						+ "\", HTTP status: " + conn.getResponseCode() + " "
						+ conn.getResponseMessage());
				String message = errorMessages.get(conn.getResponseCode());
				if (message == null) {
					message = "Unknown error occured";
				}
				String rateLimitLimit = conn
						.getHeaderField("X-RateLimit-Limit");
				String rateLimitLimitRemaining = conn
						.getHeaderField("X-RateLimit-Remaining");
				String rateLimitLimitReset = conn
						.getHeaderField("X-RateLimit-Reset");
				message += "X-RateLimit-Limit=" + rateLimitLimit
						+ " X-RateLimit-Remaining=" + rateLimitLimitRemaining
						+ " X-RateLimit-Reset=" + rateLimitLimitReset;
				throw new Exception(message);
			}

			InputStream obj = (InputStream) conn.getContent();

			BufferedReader br = new BufferedReader(new InputStreamReader(obj));
			String str = "";

			while ((str = br.readLine()) != null) {
				json += str;
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
		// System.out.println(json);
		Gson gson = new Gson();
		return gson.fromJson(json.trim(), responseMap.get(resource));
	}
	
}
