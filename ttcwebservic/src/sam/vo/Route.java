package sam.vo;

import javax.xml.bind.annotation.XmlAttribute;

public class Route {
	@XmlAttribute(name="tag")
	private String busNumber;
	@XmlAttribute(name="title")
	private String busName;
	
	public String getBusNumber() {
		return busNumber;
	}
	public String getBusName() {
		return busName;
	}
}