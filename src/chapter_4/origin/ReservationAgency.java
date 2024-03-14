package chapter_4.origin;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        boolean discountable = false;
        for (DiscountCondition discountCondition : movie.getDiscountConditions()) {
            if (discountCondition.getType() == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(discountCondition.getDayOfWeek()) &&
                        !discountCondition.getStartTime().isAfter(screening.getWhenScreened().toLocalTime()) &&
                        !discountCondition.getEndTime().isBefore(screening.getWhenScreened().toLocalTime());
            } else {
                discountable = discountCondition.getSequence() == screening.getSequence();
            }

            if (discountable) break;
        }

        Money fee;
        if (discountable) {
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT -> discountAmount = movie.getDiscountAmount();
                case PERCENT_DISCOUNT -> discountAmount = movie.getFee().times(movie.getDiscountPercent());
            }

            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee, audienceCount);
    }
}
