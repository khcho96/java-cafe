package cafe.domain;

public class Barista {

    private final String name;
    private int totalWorkTime;
    private int currentWorkTime;
    private Order order;

    private Barista(String name) {
        this.name = name;
    }

    public static Barista from(String name) {
        return new Barista(name);
    }

    public int getWorkTime() {
        if (order == null) {
            return 0;
        }

        return currentWorkTime;
    }

    public void doWork(Order order) {
        currentWorkTime = order.getWorkTime();
        totalWorkTime += currentWorkTime;
        this.order = order;
    }

    public void updateWorkTime(int worktime) {
        currentWorkTime -= worktime;
    }

    public String getName() {
        return name;
    }

    public int getTotalWorkTime() {
        return totalWorkTime;
    }

    public String mostWorkBarista(Barista baristaB) {
        if (totalWorkTime == baristaB.totalWorkTime) {
            return null;
        }

        if (totalWorkTime > baristaB.totalWorkTime) {
            return this.name;
        }

        return baristaB.name;
    }
}
