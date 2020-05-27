import java.time.LocalDate;

public interface Account {
    public String getNumber();
    public void setNumber(String number);
    public double getBalance();
    public void setBalance(double balance);
    public boolean checkNumber(String number);
    public LocalDate getCreationDate();
    public LocalDate getExpirationDate();
    public void setExpirationDate(LocalDate expirationDate);
    public int monthesQuantityBeforeExpiration();
}
