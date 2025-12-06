package cafe.constant;

public enum Menu {

    AMERICANO("coffee", "아메리카노", 4000, 2),
    CAFE_LATTE("coffee", "카페라테", 4500, 3),
    BANILLA_LATTE("coffee", "바닐라라테", 5000, 3),
    COLD_BREW("coffee", "콜드브루", 4500, 2),

    CHOCO_LATTE("non coffee", "초코라테", 5000, 3),
    MATCHA_LATTE("non coffee", "녹차라테", 5000, 3),
    HUB_TEA("non coffee", "허브티", 4000, 2),

    CHEESE_CAKE("dessert", "치즈케이크", 5500, 0),
    MACAROON("dessert", "마카롱세트", 7000, 0),
    BROWNIE("dessert", "브라우니", 4500, 0),

    NONE(null, null, null, null);

    private final String category;
    private final String name;
    private final Integer price;
    private final Integer workTime;

    Menu(String category, String name, Integer price, Integer workTime) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.workTime = workTime;
    }

    public static Menu from(String name) {
        for (Menu value : Menu.values()) {
            if (name.equals(value.name)) {
                return value;
            }
        }
        return NONE;
    }

    public boolean isDessert() {
        return category.equals("dessert");
    }

    public boolean isNone() {
        return category == null;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Integer getWorkTime() {
        return workTime;
    }
}
