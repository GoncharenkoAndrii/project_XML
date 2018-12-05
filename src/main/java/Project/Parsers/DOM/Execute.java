package Project.Parsers.DOM;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Execute {
    public static void main(String[] args) {
        try {
            new CutleryDOMParser().parseCutlery("xmlFiles/Cutlery.xml").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    }

