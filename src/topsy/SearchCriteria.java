package topsy;

public class SearchCriteria {

	private String query;
	private String window;
	private String type;
	private String zoom;
	private String query_features;
	private ListParameters listParams;
	private String rankcount_maxdays;


	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getRankcount_maxdays() {
		return rankcount_maxdays;
	}

	public void setRankcount_maxdays(String rankcount_maxdays) {
		this.rankcount_maxdays = rankcount_maxdays;
	}

	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            Follow query syntax as mentioned in {@link http
	 *            ://code.google.com/p/otterapi/wiki/QuerySyntax}
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	public String getWindow() {
		return window;
	}

	/**
	 * @param window
	 *            Time window for results. (Options: dynamic - will pick the
	 *            most relevant window. Possible responses are 1-23 hours or
	 *            1-100 days. (For example: h6 or d10). h last hour, d last day,
	 *            w last week, m last month, a all time. (DEPRECATED: auto -
	 *            automatically pick the most relevant window. This will return
	 *            one of the standard h,d,w,m,a window values.)
	 *
	 *            default is "a"
	 */
	public void setWindow(String window) {
		this.window = window;
	}

	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type of result. Default is nothing, which includes all
	 *            types. Other supported values are image, tweet and video
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getQuery_features() {
		return query_features;
	}

	/**
	 * @param query_features
	 *            Enables QueryFeatures {@link http
	 *            ://code.google.com/p/otterapi/wiki/QueryFeatures} described in
	 *            the wiki. Only works when window=dynamic. Set any non null
	 *            value to set query_features option.
	 */
	public void setQuery_features(String query_features) {
		this.query_features = query_features;
	}

	public ListParameters getListParams() {
		return listParams;
	}

	/**
	 * @param listParams
	 *            Refer: {@link http
	 *            ://code.google.com/p/otterapi/wiki/ResListParameters}
	 *
	 *            Not setting this value or leaving page value in list
	 *            parameters null will return all the results (10 pages max as
	 *            limited by API)
	 */
	public void setListParams(ListParameters listParams) {
		this.listParams = listParams;
	}

	public String toQuery() {
		String queryStr = "";
		queryStr += "q=" + query;
		if (window != null)
			queryStr += "&window=" + window;
		if (type != null)
			queryStr += "&type=" + type;
		if (zoom != null)
			queryStr += "&zoom=" + zoom;
		if (query_features != null)
			queryStr += "&query_features=1";
		if (listParams != null)
			queryStr += listParams.toQuery();
		if (rankcount_maxdays != null)
			queryStr += "&rankcount_maxdays=" + rankcount_maxdays;

		return queryStr;
	}
}
