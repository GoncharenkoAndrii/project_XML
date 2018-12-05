package XMLtask.Models;

public class Beer {
    private String BeerId;
    private BeerType type = BeerType.LAGGER;
    private String name;
    private String production;
    private Ingredients ingredients = new Ingredients();
    private AboutTheBeer aboutTheBeer = new AboutTheBeer();
    // static classes with additional info
    public static class Ingredients{
        private int sugar ;
        private int water ;
        // solod
        private int malt ;
        // hmil'
        private int hops ;

        public int getSugar() {
            return sugar;
        }

        public void setSugar(int sugar) {
            this.sugar = sugar;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }

        public int getMalt() {
            return malt;
        }

        public void setMalt(int malt) {
            this.malt = malt;
        }

        public int getHops() {
            return hops;
        }

        public void setHops(int hops) {
            this.hops = hops;
        }

        @Override
        public String toString() {
            return "Ingridients{" +
                    "sugar=" + sugar +
                    ", water=" + water +
                    ", malt=" + malt +
                    ", hops=" + hops +
                    '}';
        }
    }
    public static class AboutTheBeer{
        private int volOfAlcohol ;
        private int isFiltered ;
        private int energyAmount;

        @Override
        public String toString() {
            return "AboutTheBeer{" +
                    "volOfAlcohol=" + volOfAlcohol +
                    ", isFiltered=" + isFiltered +
                    ", energyAmount=" + energyAmount +
                    '}';
        }

        public int getVolOfAlcohol() {
            return volOfAlcohol;
        }

        public void setVolOfAlcohol(int volOfAlcohol) {
            this.volOfAlcohol = volOfAlcohol;
        }

        public int getIsFiltered() {
            return isFiltered;
        }

        public void setIsFiltered(int isFiltered) {
            this.isFiltered = isFiltered;
        }

        public int getEnergyAmount() {
            return energyAmount;
        }

        public void setEnergyAmount(int energyAmount) {
            this.energyAmount = energyAmount;
        }
    }
    // getters, setters and toString


    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public AboutTheBeer getAboutTheBeer() {
        return aboutTheBeer;
    }

    public void setAboutTheBeer(AboutTheBeer aboutTheBeer) {
        this.aboutTheBeer = aboutTheBeer;
    }

    public String getBeerId() {
        return BeerId;
    }

    public void setBeerId(String beerId) {
        BeerId = beerId;
    }

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "BeerId='" + BeerId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +

                ", production='" + production + '\'' +
                ", ingredients=" + ingredients +
                ", aboutTheBeer=" + aboutTheBeer +
                '}';
    }
}
