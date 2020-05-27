import java.time.LocalDate;

public class CreditAccount extends AbstractAccount implements Credit,Cloneable{
    private double APR;

    public static final double DEFAULT_APR = 30;

    public CreditAccount(String number, LocalDate expirationDate){
        super(number,expirationDate);
        setAPR(DEFAULT_APR);
    }

    public CreditAccount(String number, double balance, double APR, LocalDate creationDate, LocalDate expirationDate){
        super(number,0,creationDate,expirationDate);
        setBalance(balance);
        setAPR(APR);
    }

    @Override
    public void setBalance(double balance){
        if(balance>0) throw new IllegalArgumentException("Передан положительный баланс");
        super.setBalance(balance);
    }

    @Override
    public double getAPR() {
        return APR;
    }

    @Override
    public void setAPR(double APR) {
        this.APR = APR;
    }

    @Override
    public double nextPaymentValue() {
        return getBalance() * (1 + getAPR() * (getExpirationDate().getYear() - LocalDate.now().getYear())) /
                (getExpirationDate().getMonthValue() - LocalDate.now().getMonthValue());
    }

    @Override
    public LocalDate nextPaymentDate() {
        if(LocalDate.now().getDayOfMonth()<25)
            return LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),25);
        else return LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth().plus(1),25);
    }

    @Override
    public String toString() {
        return "Credit account - " + super.toString() + " APR: " +APR;
    }

    @Override
    public int hashCode(){
        return 71 * super.hashCode() + (int) APR;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        CreditAccount that = (CreditAccount) obj;
        return APR == that.APR;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
