package XMLtask.Builder;

import XMLtask.Models.Beer;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBeerBuilder {
    public Set<Beer> beerSet ;
    public AbstractBeerBuilder(){
        beerSet= new HashSet<>();
    }

    public Set<Beer> getBeerSet() {
        return beerSet;
    }
    public abstract void buildSetBeer (String filename);
}

