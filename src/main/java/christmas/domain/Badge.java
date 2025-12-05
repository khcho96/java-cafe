package christmas.domain;

public enum Badge {
    STAR("별" ),
    TREE("트리"),
    SANTA("산타"),
    NONE(null);

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static Badge from(int price) {
        if (price >= 20_000) return SANTA;
        if (price >= 10_000) return TREE;
        if (price >= 5_000) return STAR;
        return NONE;
    }

    public String getName() {
        return name;
    }
}
