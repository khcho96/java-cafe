package christmas.constant;

public enum Menu {

    SOUP("애피타이저", "양송이수프", 6_000),
    TAPAS("애피타이저", "타파스", 5_500),
    SALAD("애피타이저", "시저샐러드", 8_000),

    STAKE("메인", "티본스테이크", 55_000),
    RIB("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000),

    CAKE("디저트", "초코케이크", 15_000),
    ICE_CREAM("디저트", "아이스크림", 5_000),

    COKE("음료", "제로콜라", 3_000),
    WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000),

    NONE(null, null, 0);

    private final String category;
    private final String name;
    private final int price;

    Menu(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu from(String name) {
        Menu[] menus = Menu.values();
        for (Menu menu : menus) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        return NONE;
    }

    public boolean isMain() {
        return category.equals("메인");
    }

    public boolean isDessert() {
        return category.equals("디저트");
    }

    public boolean isBeverage() {
        return category.equals("음료");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
