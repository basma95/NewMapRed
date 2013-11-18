package topsy;

public class TopsyConfig {
	private boolean setProxy;
	private String proxyHost;
	private String proxyPort;
	private String apiKey;
	private String apiKeyStr;

	public String getApiKeyStr() {
		return apiKeyStr;
	}

	public TopsyConfig() {
		apiKeyStr = "";
	}

	public TopsyConfig(String apiKey) {
		this.apiKey = apiKey;
		apiKeyStr = "&apikey=" + apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
		apiKeyStr = "&apikey=" + apiKey;
	}

	public boolean isSetProxy() {
		return setProxy;
	}

	public void setSetProxy(boolean setProxy) {
		this.setProxy = setProxy;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}
}
