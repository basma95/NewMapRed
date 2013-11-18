package topsy;
import java.net.URL;

import topsy.ListParameters;
import topsy.TopsyConfig;
import topsy.TopsyConstants;
import topsy.TopsyInvoker;

/**
 * @author Maruti Gollapudi (maruti (dot) gollapudi (at) gmail.com)
 *
 *         Java wrapper for Otter API Version 0.1
 *
 */
public class Search {

	private TopsyConfig topsyConfig;

	public TopsyConfig getTopsyConfig() {
		return topsyConfig;
	}

	public void setTopsyConfig(TopsyConfig topsyConfig) {
		this.topsyConfig = topsyConfig;
	}

	/**
	 * @param criteria
	 * @return
	 * @throws Otter4JavaException
	 */
	//public SearchResponse search(SearchCriteria criteria)
	public URL search(SearchCriteria criteria)
			throws Exception {
		if (criteria.getWindow() == null)
			criteria.setWindow("a");
		if (criteria.getListParams() == null
				|| criteria.getListParams().getPage() == null)
			return fetchAllURL(criteria, "search");
			//return fetchAll(criteria, "search");
		//return fetch(criteria, "search");
		return fetchURL(criteria, "search");
	}

	public SearchResponse searchdate(SearchCriteria criteria)
			throws Exception {
		if (criteria.getWindow() == null)
			criteria.setWindow("a");
//		if (criteria.getListParams() == null
//				|| criteria.getListParams().getPage() == null)
//			return fetchAll(criteria, "searchdate");
		return fetch(criteria, "searchdate");
	}

	private SearchResponse fetchAll(SearchCriteria criteria, String type)
			throws Exception {

		int page = 1;
		ListParameters listParameters = null;
		if (criteria.getListParams() != null)
			listParameters = criteria.getListParams();
		else
			listParameters = new ListParameters();
		listParameters.setPage("" + page);
		listParameters.setPerpage("100");
		criteria.setListParams(listParameters);
		SearchResponse response = fetch(criteria, type);
		int total = Integer.parseInt(response.getResult().getTotal());
		int fetchedRecs = response.getResult().getList().size();
		SearchResponse response1 = null;
		String lastOffset = response.getResult().getLast_offset();
		page++;
		while (total > fetchedRecs && page <= 10) {
			listParameters.setPage("" + page);
			//listParameters.setOffset(lastOffset);
			response1 = fetch(criteria, type);
			response.getResult().getList().addAll(
					response1.getResult().getList());
			fetchedRecs += response1.getResult().getList().size();
			System.out.println(response1.getResult().getList().size());
			lastOffset = response1.getResult().getLast_offset();
			page++;
		}

		return response;
	}

	private SearchResponse fetch(SearchCriteria criteria, String type)
			throws Exception {
		TopsyInvoker invoker = new TopsyInvoker();
		invoker.setTopsyConfig(topsyConfig);

		String query = criteria.toQuery().replaceAll("\\s", "%20");
		String url = TopsyConstants.baseSearchURL;
		if ("searchdate".equalsIgnoreCase(type))
			url = TopsyConstants.baseSearchdateURL;

		SearchResponse data = (SearchResponse) invoker.fetchData(
				url + query, "search");

		return data;
	}
	private URL fetchAllURL(SearchCriteria criteria, String type) throws Exception {
		URL urlFull;
		try{
		String query = criteria.toQuery().replaceAll("\\s", "%20");
		String url = TopsyConstants.baseSearchURL;
		if ("searchdate".equalsIgnoreCase(type))
			url = TopsyConstants.baseSearchdateURL;
		urlFull = new URL(url +query+ topsyConfig.getApiKeyStr());
		return urlFull;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	private URL fetchURL(SearchCriteria criteria, String type) {
		URL urlFull;
		try{
		String query = criteria.toQuery().replaceAll("\\s", "%20");
		String url = TopsyConstants.baseSearchURL;
		if ("searchdate".equalsIgnoreCase(type))
			url = TopsyConstants.baseSearchdateURL;
		urlFull = new URL(url + query+ topsyConfig.getApiKeyStr());
		return urlFull;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
