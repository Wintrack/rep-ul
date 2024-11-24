package ca.ulaval.glo4003.repUL.domain.payment;

import java.time.LocalDate;

public class CreditCard {
    String CardNumber;
    int CCV;
    LocalDate ExpDate;
    String FullName;

    public CreditCard(String CardNumber, int CCV, LocalDate ExpDate, String FullName) {
        this.CardNumber = CardNumber;
        this.CCV = CCV;
        this.ExpDate = ExpDate;
        this.FullName = FullName;
    }
}
