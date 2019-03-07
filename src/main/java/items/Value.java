package items;

import java.io.Serializable;
import java.math.BigDecimal;

public class Value implements Serializable {
    BigDecimal amount;
    String currency;

    public Value(BigDecimal amount) {
        this.amount = amount;
        this.currency = "EUR";
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
