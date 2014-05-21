package s1;

public class News {
	private String name;
	private String url;
	private String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "News [name=" + name + ", url=" + url + ", date=" + date + "]";
	}
	
	
}

