package cafe.controller;

import cafe.dto.ResultDto;
import cafe.service.CafeService;
import cafe.util.InputParser;
import cafe.view.InputView;
import cafe.view.OutputView;
import java.util.List;

public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    public void run() {
        int customerCount = registerCustomerCount();
        ResultDto resultDto = computeResult(customerCount);
        OutputView.print(resultDto);
    }

    private int registerCustomerCount() {
        while (true) {
            try {
                String rawCustomerCount = InputView.readCustomerCount();

                return InputParser.parseCustomerCount(rawCustomerCount);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private ResultDto computeResult(int customerCount) {
        ResultDto resultDto = null;
        for (int i = 0; i < customerCount; i++) {
            while (true) {
                try {
                    String rawOrder = InputView.readOrder(i + 1);
                    List<String> orderMenus = InputParser.parseOrders(rawOrder);

                    resultDto = cafeService.computeResult(orderMenus, i + 1);
                    break;
                } catch (IllegalArgumentException e) {
                    OutputView.printErrorMessage(e);
                }
            }
        }

        return resultDto;
    }
}
