package bean;

public class Subject implements java.io.Serializable {

	private String schoolCd;
	private String cd;
	private String name;

	public String getSchoolCd() {
		return schoolCd;
	}
	public String getCd() {
		return cd;
	}
	public String getName() {
		return name;
	}

	public void setSchoolCd(String schoolCd) {
		this.schoolCd=schoolCd;
	}
	public void setCd(String cd) {
		this.cd=cd;
	}
	public void setName(String name) {
		this.name=name;
	 }
	}
