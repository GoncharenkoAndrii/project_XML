package XMLtask.Models;

public class Lagger extends Beer {
    private int alcoholPercent ;
    private LaggerKind kind ;
    private Additives add = new Additives();
    // additives are specific to LAGGER beer
    public static class Additives {
        private int ginger;
        private int honey;

        public int getGinger() {
            return ginger;
        }

        public void setGinger(int ginger) {
            this.ginger = ginger;
        }

        public int getHoney() {
            return honey;
        }

        public void setHoney(int honey) {
            this.honey = honey;
        }

        @Override
        public String toString() {
            return "Additives{" +
                    "ginger=" + ginger +
                    ", honey=" + honey +
                    '}';
        }
    }

    public int getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(int alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }

    public LaggerKind getKind() {
        return kind;
    }

    public void setKind(LaggerKind kind) {
        this.kind = kind;
    }

    public Additives getAdd() {
        return add;
    }

    public void setAdd(Additives add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Lagger{" +
                "alcoholPercent=" + alcoholPercent +
                ", kind=" + kind +
                ", add=" + add +
                '}';
    }
}
