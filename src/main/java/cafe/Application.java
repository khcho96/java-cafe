package cafe;

import cafe.controller.CafeController;
import cafe.service.CafeService;

public class Application {
    public static void main(String[] args) {
        CafeService cafeService = new CafeService();
        CafeController cafeController = new CafeController(cafeService);
        cafeController.run();
    }
}
