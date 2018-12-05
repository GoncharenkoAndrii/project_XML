package XMLtask.Builder;

import XMLtask.Models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BeerStaxBuiler extends AbstractBeerBuilder {
    private final static Logger LOG = LogManager.getLogger();
    private XMLInputFactory factory;

    public BeerStaxBuiler() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetBeer(String filename) {
        FileInputStream inputStream;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(filename));
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (BeerEnum.valueOf(name.toUpperCase()) == BeerEnum.BEER) {
                        Beer beer = buildBeer(reader, false);
                        beerSet.add(beer);
                    }
                    if ((BeerEnum.valueOf(name.toUpperCase()) == BeerEnum.LAGGER)) {
                        Lagger lagger = (Lagger) buildBeer(reader, true);
                        beerSet.add(lagger);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOG.error("STAX parsing error in BuildSetBeer method" + e);
        } catch (FileNotFoundException e) {
            LOG.error("File " + filename + "not found " + e);
            throw new RuntimeException();
        }
    }

    private Beer buildBeer(XMLStreamReader reader, boolean isLagger) throws XMLStreamException {
        Beer beer;
        String name;
        if (isLagger) {
            beer = new Lagger();
            ((Lagger) beer).setKind(LaggerKind.valueOf(reader.getAttributeValue(null, BeerEnum.KIND.getValue().toUpperCase())));
        } else {
            beer = new Beer();
            beer.setType(BeerType.valueOf(reader.getAttributeValue(null, BeerEnum.TYPEE.getValue().toUpperCase())));

        }
        beer.setBeerId(reader.getAttributeValue(null, BeerEnum.BeerID.getValue()));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BeerEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            beer.setName(getXMLText(reader));
                            break;
                        case PRODUCTION:
                            beer.setProduction(getXMLText(reader));
                            break;
                        case INGRIDIENTS:
                            beer.setIngredients(getXMLIngridients(reader));
                            break;
                        case ABOUTTHEBEER:
                            beer.setAboutTheBeer(getXMLAboutTheBeer(reader));
                            break;
                        case VOLOFALCOHOL:
                            ((Lagger) beer).setAlcoholPercent(Integer.valueOf(getXMLText(reader)));
                            break;
                        case ADDITIVES:
                            ((Lagger) beer).setAdd(getXMLAdditives(reader));
                            break;
                        default:
                            LOG.warn("no such field in Beer class");
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (((BeerEnum.valueOf(name.toUpperCase())) == BeerEnum.BEER) ||
                            ((BeerEnum.valueOf(name.toUpperCase())) == BeerEnum.LAGGER)) {
                        return beer;
                    }
                    break;


            }
        }
        throw new XMLStreamException("Unknown element in tag Beer");
    }

    private Lagger.Additives getXMLAdditives(XMLStreamReader reader) throws XMLStreamException {
        Lagger.Additives additives = new Lagger.Additives();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BeerEnum.valueOf(name.toUpperCase())) {
                        case GINGER:
                            additives.setGinger(Integer.valueOf(getXMLText(reader)));
                            break;
                        case HONEY:
                            additives.setHoney(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BeerEnum.valueOf(name.toUpperCase()) == BeerEnum.ADDITIVES) {
                        return additives;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Additives");
    }

    private Beer.AboutTheBeer getXMLAboutTheBeer(XMLStreamReader reader) throws XMLStreamException {
        Beer.AboutTheBeer aboutTheBeer = new Beer.AboutTheBeer();
        int type ;
        String name ;
        while (reader.hasNext()){
            type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BeerEnum.valueOf(name.toUpperCase())){
                        case VOLOFALCOHOL:
                            aboutTheBeer.setVolOfAlcohol(Integer.valueOf(getXMLText(reader)));
                            break;
                        case ENERGYAMOUNT:
                            aboutTheBeer.setEnergyAmount(Integer.valueOf(getXMLText(reader)));
                            break;
                        case ISFILTERED:
                            aboutTheBeer.setIsFiltered(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BeerEnum.valueOf(name.toUpperCase())==BeerEnum.ABOUTTHEBEER){
                        return aboutTheBeer;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag AboutTheBEER");
    }

    private Beer.Ingredients getXMLIngridients(XMLStreamReader reader) throws XMLStreamException {
        Beer.Ingredients ingredients = new Beer.Ingredients();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BeerEnum.valueOf(name.toUpperCase())) {
                        case HOPS:
                            ingredients.setHops(Integer.valueOf(getXMLText(reader)));
                            break;
                        case MALT:
                            ingredients.setMalt(Integer.valueOf(getXMLText(reader)));
                            break;
                        case WATER:
                            ingredients.setWater(Integer.valueOf(getXMLText(reader)));
                            break;
                        case SUGAR:
                            ingredients.setSugar(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BeerEnum.valueOf(name.toUpperCase())== BeerEnum.INGRIDIENTS){
                        return ingredients;
                    }
                    break;

            }
        }
        throw new XMLStreamException("Unknown element in tag INGRIDIENTS");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
