package Project.Models;

public class Cutlery {
    private static final String XML_NODE_NAME = "cutlery";
    private String id;
    private Type type;
    private Origin origin;
    private Visual visual;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public Cutlery() {
    }

    public enum Type {
        SPOON, FORK, KNIFE;
    }

    public enum Origin {
        BRA, GER, USA;
    }

    public enum Material {
        STEEL, SILVER;
    }

    public static class Visual {
        private int length;
        private Material material;

        public Visual(final int length, Material material) {
            this.length = length;
            this.material = material;
        }

        public int getLength() {
            return length;
        }

        public Material getMaterial() {
            return material;
        }

        @Override
        public String toString() {
            return String.format("[Length: %s, Material: %s]", length, material);
        }
    }

    public static class Builder {
        private String id;
        private Type type;

        private Origin origin = Origin.BRA;
        private Visual visual = new Visual(100, Material.STEEL);

        public Builder(String id, Type type) {
            this.id = id;
            this.type = type;
        }

        public Builder origin(Origin origin) {
            this.origin = origin;
            return this;
        }

        public Builder visual(Visual visual) {
            this.visual = visual;
            return this;
        }
    }
    public Cutlery(Builder builder ){
        this.id = builder.id;
        this.type = builder.type;
        this.origin = builder.origin;
        this.visual = builder.visual;
    }
    public static String getXmlNodeName() {
        return XML_NODE_NAME;
    }

    @Override
    public String toString() {
        return "Cutlery{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", origin=" + origin +
                ", visual=" + visual +
                '}';
    }
}
