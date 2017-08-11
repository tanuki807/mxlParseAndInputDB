package dbconnection;

import org.w3c.dom.Element;

import readnode.Xml;




public interface TableStrategy {
	Xml getTagValue(Element element, Xml xml);
}
