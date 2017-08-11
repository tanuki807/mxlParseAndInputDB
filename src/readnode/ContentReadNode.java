package readnode;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContentReadNode implements ReadNode {
	List<String> valueList;
	Content content;
	
	public ContentReadNode() {
		content = new Content();
		valueList = new ArrayList();
	}
	
	@Override
	public Content readNode(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				valueList.add(getTagValue("Value", element));
				content.setVlaue(valueList);
			}
		}
		System.out.println(content.getVlaue());
		return content;
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
