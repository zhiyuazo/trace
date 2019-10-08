package testModel;

public class fatherInner {
	String name = "father";
	sonInner si  = new sonInner();
	public String getName() {
		return name;
	}
	public sonInner getSi() {
		return si;
	}
	public void setSi(sonInner si) {
		this.si = si;
	}
	public void setName(String name) {
		this.name = name;
	}
}
