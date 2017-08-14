package readnode;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public interface ReadNode {
	Xml readNode(NodeList list);
	String getTagValue(String tag, Element element);
}
