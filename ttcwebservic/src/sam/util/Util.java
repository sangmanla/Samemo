package sam.util;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Util {
	public static Object getParsingObject(Class className, String urlStr) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(className);
		Unmarshaller un = jc.createUnmarshaller();
		return un.unmarshal(new URL(urlStr));
	}
}
