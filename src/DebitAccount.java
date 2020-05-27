import java.time.LocalDate;

public class DebitAccount extends AbstractAccount implements Account,Cloneable{

    public DebitAccount(String number, LocalDate expirationDate) {
        super(number,expirationDate);
    }

    public DebitAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) {
        super(number,0,creationDate,expirationDate);
        super.setBalance(balance);
    }

    @Override
    public void setBalance(double balance){
        if(balance<0) throw new IllegalArgumentException("Передан отрицательный баланс баланс");
        super.setBalance(balance);
    }

    @Override
    public String toString() {
        return "Debit account - " + super.toString();
    }

    @Override
    public int hashCode(){
        return 53 * super.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
