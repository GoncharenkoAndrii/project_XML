package XMLtask.Builder;

import XMLtask.Models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class BeerHandler extends DefaultHandler {
    private final static Logger LOG = LogManager.getLogger();
    private Set<Beer> allBeers;
    private Beer beerCurrent;
    private BeerEnum currentEnum = BeerEnum.ALLBEER;

    public BeerHandler() {
        allBeers = new HashSet<>();
    }

    public Set<Beer> getAllBeer() {
        return allBeers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = BeerEnum.valueOf(localName.toUpperCase());
        switch (currentEnum) {
            case BEER:
                beerCurrent = new Beer();
                beerCurrent.setBeerId(attributes.getValue(0));
                if (attributes.getLength() == 2) {
                    beerCurrent.setType(BeerType.valueOf(attributes.getValue(1).toUpperCase()));
                }
                break;
            case LAGGER:
                beerCurrent = new Lagger();
                beerCurrent.setBeerId(attributes.getValue(0));
                if (attributes.getLength() == 2) {
                    ((Lagger) beerCurrent).setKind(LaggerKind.valueOf(attributes.getValue(1).toUpperCase()));
                }
                break;
            default:
                LOG.warn("No such beer. Try again");
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("beer".equals(localName) || "lagger".equals(localName)) {
            allBeers.add(beerCurrent);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String element = new String(ch, start, length).trim();
        if (!element.isEmpty()) {
            switch (currentEnum) {
                case NAME:
                    beerCurrent.setName(element);
                    break;
                case PRODUCTION:
                    beerCurrent.setProduction(element);
                    break;
                case HOPS:
                    beerCurrent.getIngredients().setHops(Integer.valueOf(element));
                    break;
                case WATER:
                    beerCurrent.getIngredients().setWater(Integer.valueOf(element));
                    break;
                case MALT:
                    beerCurrent.getIngredients().setMalt(Integer.valueOf(element));
                    break;
                case SUGAR:
                    beerCurrent.getIngredients().setSugar(Integer.valueOf(element));
                    break;
                case ALCOHOLPERCENT:
                    beerCurrent.getAboutTheBeer().setVolOfAlcohol(Integer.valueOf(element));
                    break;
                case ISFILTERED:
                    beerCurrent.getAboutTheBeer().setIsFiltered(Integer.valueOf(element));
                    break;
                case ENERGYAMOUNT:
                    beerCurrent.getAboutTheBeer().setEnergyAmount(Integer.valueOf(element));
                    break;
                case HONEY:
                    ((Lagger)beerCurrent).getAdd().setHoney(Integer.valueOf(element));
                    break;
                case GINGER:
                    ((Lagger)beerCurrent).getAdd().setGinger(Integer.valueOf(element));
                    break;
                case VOLOFALCOHOL:
                    ((Lagger)beerCurrent).setAlcoholPercent(Integer.valueOf(element));
                    break;
                default:
                    LOG.warn("no such field in a class ");
            }
        }
    }
}
