package readnode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Content;
import factory.DaoFactory;

public class ContentReadNode implements ReadNode {
	private List<String> valueList;
	private ContentTag contentTag;
	private Content content;
	
	public ContentReadNode() {
		contentTag = new ContentTag();
		valueList = new ArrayList();
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	
	@Override
	public ContentTag readNode(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				valueList.add(getTagValue("Value", element));
				contentTag.setVlaue(valueList);
				
			}
		}
		content.setValue(valueList);
		inputContent(content);
		System.out.println(contentTag.getVlaue());
		return contentTag;
	}
	
	
	private void inputContent(Content content) {
		String title = content.getTitle();
		List advisorList = content.getAdvisories();
		List content_FileSizeList = content.getContent_FileSize();
		List content_CheckSumList = content.getContent_CheckSum();
		List valueList = content.getValue();
		
		Iterator<String> advisorIt = advisorList.iterator();
		Iterator<String> fileSizeIt = content_FileSizeList.iterator();
		Iterator<String> checkSumIt = content_CheckSumList.iterator();
		Iterator<String> valueIt = valueList.iterator();
		
		while(advisorIt.hasNext()) {
			//contentDao.add(title, advisorIt.next(), fileSizeIt.next(), checkSumIt.next(), valueIt.next());
		}
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
