package christmas.dto;

import christmas.domain.Badge;
import java.util.List;

public record EventDto(List<Boolean> eventResult, List<Integer> benefitPrice, Badge badge) {
}
