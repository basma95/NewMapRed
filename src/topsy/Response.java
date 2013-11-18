package topsy;

import java.util.List;

public class Response {

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	private List<Post> list;
	private String total;
	private String perpage;
	private String last_offset;
	private String offset;
	private String w;
	private String h;
	private String a;
	private String d;
	private String m;
	private String window_max_day_window_count;
	private String window;
	private String page;
	private String window_all_count;
	private QueryFeatures query_features;
	private String window_score;
	private String hidden;

	public String getWindow_max_day_window_count() {
		return window_max_day_window_count;
	}

	public void setWindow_max_day_window_count(
			String window_max_day_window_count) {
		this.window_max_day_window_count = window_max_day_window_count;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getWindow_all_count() {
		return window_all_count;
	}

	public void setWindow_all_count(String window_all_count) {
		this.window_all_count = window_all_count;
	}

	public QueryFeatures getQuery_features() {
		return query_features;
	}

	public void setQuery_features(QueryFeatures query_features) {
		this.query_features = query_features;
	}

	public String getWindow_score() {
		return window_score;
	}

	public void setWindow_score(String window_score) {
		this.window_score = window_score;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getPerpage() {
		return perpage;
	}

	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}

	public String getLast_offset() {
		return last_offset;
	}

	public void setLast_offset(String last_offset) {
		this.last_offset = last_offset;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

}
