package topsy;

public class ListParameters {

	private String page;
	private String perpage;
	private String offset;
	private String mintime;
	private String maxtime;
	private String nohidden;
	private String allow_lang;
	private String family_filter;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPerpage() {
		return perpage;
	}

	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getMintime() {
		return mintime;
	}

	public void setMintime(String mintime) {
		this.mintime = mintime;
	}

	public String getMaxtime() {
		return maxtime;
	}

	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}

	public String getNohidden() {
		return nohidden;
	}

	public void setNohidden(String nohidden) {
		this.nohidden = nohidden;
	}

	public String getAllow_lang() {
		return allow_lang;
	}

	public void setAllow_lang(String allow_lang) {
		this.allow_lang = allow_lang;
	}

	public String getFamily_filter() {
		return family_filter;
	}

	public void setFamily_filter(String family_filter) {
		this.family_filter = family_filter;
	}

	public String toQuery() {
		String query = "";
		if (page != null)
			query += "&page=" + page;
		if (perpage != null)
			query += "&perpage=" + perpage;
		if (offset != null)
			query += "&offset=" + offset;
		if (mintime != null)
			query += "&mintime=" + mintime;
		if (maxtime != null)
			query += "&maxtime=" + maxtime;
		if (nohidden != null)
			query += "&nohidden=" + nohidden;
		if (allow_lang != null)
			query += "&allow_lang=" + allow_lang;
		if (family_filter != null)
			query += "&family_filter=" + family_filter;
		return query;
	}
}
