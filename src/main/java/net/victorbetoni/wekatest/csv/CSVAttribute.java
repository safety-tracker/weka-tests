package net.victorbetoni.wekatest.csv;

public class CSVAttribute {
    public enum Type {
        INTEGER, STRING, ENUM;
    }

    private Type type;
    private String identifier;

    public CSVAttribute(Type type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }
}
