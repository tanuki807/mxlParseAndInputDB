package dbconnection;

import org.w3c.dom.Element;

import domain.Ams;
import domain.Xml;


public interface NodeStrategy {
	Xml getTagValue(Element element, Xml xml);
}
