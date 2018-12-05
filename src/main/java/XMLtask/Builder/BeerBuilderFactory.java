package XMLtask.Builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeerBuilderFactory {
    private static final Logger LOG = LogManager.getLogger();

    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractBeerBuilder createBeerBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new BeerDomBuilder();
            case SAX:
                return new BeerSaxBuilder();
            case STAX:
                return new BeerStaxBuiler();
            default:
                LOG.warn("no such type of parser. Default parser will be SAX ");
        }
        return new BeerSaxBuilder();
    }

}
