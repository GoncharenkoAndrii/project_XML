package Project.Parsers;

import Project.Models.Cutlery;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface CutleryXMLParser {
    List<Cutlery> parseCutlery (String fileName)throws IOException, SAXException, ParserConfigurationException;
}
