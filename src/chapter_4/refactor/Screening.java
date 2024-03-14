package chapter_4.refactor;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money calculateFee(int audienceCount) throws Exception {
        switch (movie.getMovieType()) {
            case AMOUNT_DISCOUNT -> {
                if (movie.isDiscountable(whenScreened, sequence)) return movie.calculateAmountDiscountFee().times(audienceCount);
            }
            case PERCENT_DISCOUNT -> {
                if (movie.isDiscountable(whenScreened, sequence)) return movie.calculatePercentDiscountFee().times(audienceCount);
            }
            case NONE_DISCOUNT -> {
                return movie.calculateNoneDiscountFee().times(audienceCount);
            }
        }

        throw new Exception();
    }
}
