package XMLtask.Builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class BeerSaxBuilder extends AbstractBeerBuilder {
    private final static Logger LOG = LogManager.getLogger();
    private BeerHandler beerHandler ;
    private XMLReader reader ;

    public BeerSaxBuilder(){
        beerHandler = new BeerHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(beerHandler);
        }
        catch(SAXException e){
            LOG.error(e);
        }
        beerSet = beerHandler.getAllBeer();
    }

    @Override
    public void buildSetBeer(String filename) {
        try{
            reader.parse(filename);
        }
        catch (SAXException e ){
            LOG.error(e);
        }
        catch(IOException e){
            LOG.error("Wrong file",e);
        }
    }
}
