package topsy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.regex.Pattern;

import topsy.ListParameters;
import topsy.TopsyConfig;
import topsy.Post;
import topsy.Search;
import topsy.SearchCriteria;
import topsy.SearchResponse;

/**
 * @author Maruti Gollapudi (maruti (dot) gollapudi (at) gmail.com)
 *
 *         Java wrapper for Otter API Version 0.1
 *
 */

public class TopsyTest {

	private final static Pattern FILTERS = Pattern
	.compile(".*(\\.(css|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
			+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	private BufferedWriter writer;
	private long count=0;
	String outputFolder="C:\\topsy2\\tweetOutput.txt";
	public static void main(String[] args) throws Exception {
		new TopsyTest().test("bostonmarathon",1,1366055400);
	}

	public String test(String searchWord,int pageNumber,int startTime) {
		TopsyConfig config = new TopsyConfig();
		//config.setApiKey("AX5NQKD6YHZON3S2IEMQAAAAACSTJFZJQVJAAAAAAAAFQGYA");
		config.setApiKey("BARR7EPH5EIWFZK7SAMQAAAAAACF2GCUVZJAAAAAAAAFQGYA"); 
		String url = search(config,searchWord,pageNumber,startTime);
		 return url;
	}

	public String search(TopsyConfig config,String searchWord,int pageNumber,int startTime) {
		Search searchTopsy = new Search();
		searchTopsy.setTopsyConfig(config);
		SearchResponse results = null;
		String netURL;
		try {
			SearchCriteria criteria = new SearchCriteria();
			criteria.setQuery(searchWord);
			ListParameters lis = new ListParameters();
			lis.setMintime(startTime+"");
			lis.setMaxtime(startTime+600+"");
			lis.setPerpage("100");
			lis.setPage(pageNumber+"");
			criteria.setListParams(lis);
			netURL = searchTopsy.search(criteria).toString();
			System.out.println(netURL);
			return netURL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}