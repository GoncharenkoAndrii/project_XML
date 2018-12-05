package XMLtask.Models;

public enum BeerEnum {

    ALLBEER("ALLBEER"),BEER("beer"),DARK("dark"),LAGGER("lagger"),LIGHT("light"),PRODUCTION("production"),TYPEE("type"),
    BeerID("beerID"),NAME("name"),TYPE("type"),ENERGY("energy"),GINGER("ginger"),HONEY("honey"),KIND("kind"),ADDITIVES("additives"),
    WATER("water"),SUGAR("sugar"),MALT("malt"),HOPS("hops"),INGRIDIENTS("ingridients"),ALCOHOLPERCENT("alcoholpercent"),
    ABOUTTHEBEER("aboutTheBeer"),VOLOFALCOHOL("VolOfAlcohol"),ISFILTERED("IsFIltered"),ENERGYAMOUNT("EnergyAmount");
    private String value;

    BeerEnum(String value) {
        this.value = value;
    }
    public String getValue (){
        return value;
    }
}
