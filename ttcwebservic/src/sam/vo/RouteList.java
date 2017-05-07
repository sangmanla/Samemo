package sam.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="body")
public class RouteList {
	@XmlElement(name="route")
	private List<Route> routes;

	public List<Route> getRoutes() {
		return routes;
	}
}