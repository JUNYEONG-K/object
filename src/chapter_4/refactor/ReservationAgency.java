package chapter_4.refactor;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) throws Exception {
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
