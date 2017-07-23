package entities;

import javax.persistence.*;

@Entity
// @DiscriminatorValue("Credit Card") // for single table
@Table(name = "credit_card")
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends BasicBillingDetail {

    private String cardType;
    private String expirationMonth;
    private int expirationYear;

    @Column(name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "expiration_month")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "expiration_year")
    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
