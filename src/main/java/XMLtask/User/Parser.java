package XMLtask.User;


import XMLtask.Builder.AbstractBeerBuilder;
import XMLtask.Builder.BeerBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {
    private static final Logger LOG= LogManager.getLogger();
    private static final String INPUT_XML = "beer.xml";
    private static final String PARSER = "stax";

    public static void main(String[] args) {
        BeerBuilderFactory factory = new BeerBuilderFactory();
        AbstractBeerBuilder builder = factory.createBeerBuilder(PARSER);
        builder.buildSetBeer(INPUT_XML);
        LOG.info(PARSER + "parser : " + builder.getBeerSet());
    }
}
