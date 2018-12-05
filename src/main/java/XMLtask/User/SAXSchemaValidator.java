package XMLtask.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SAXSchemaValidator {
    private final static Logger LOG = LogManager.getLogger();
    private static final String INPUT_XML = "beer.xml";
    private static final String INPUT_XSD = "beer.xsd";

    public static void main(String[] args) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(INPUT_XSD);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(INPUT_XML);
            validator.validate(source);
            LOG.info(INPUT_XML + "is valid." );
        }
        catch(SAXException e ){
            LOG.error("validation"+ INPUT_XML + "is not valid" + e.getMessage());
        }
        catch (IOException e ){
            LOG.error(INPUT_XML + "is not valid" + e.getMessage());
        }
    }
}
