import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAccount implements Account, Cloneable{
    private String number;
    private double balance;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    public static final String EMPTY_STRING = "";
    public static final int ZERO = 0;

    protected AbstractAccount(String number, LocalDate expirationDate) {
        this(number,ZERO,LocalDate.now(),expirationDate);
    }

    protected AbstractAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) {
        setNumber(number);
        this.balance = balance;
        setExpirationDate(expirationDate);//Такой порядко важен!!!
        setCreationDate(creationDate);
    }

    private void setCreationDate(LocalDate creationDate){
        Objects.requireNonNull(creationDate);
        if(creationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Передана дата из будущего");
        if(creationDate.isAfter(expirationDate)) throw new IllegalArgumentException("Дата создания не может быть " +
                "позже даты завершения обслуживания");
        this.creationDate = creationDate;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(LocalDate expirationDate){
        this.expirationDate = Objects.requireNonNull(expirationDate);
    }

    @Override
    public int monthesQuantityBeforeExpiration() {
        int result =  expirationDate.getMonthValue() - LocalDate.now().getMonthValue();
        if(LocalDate.now().getDayOfMonth()>25) result--;
        return result;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        Utils.checkNumber(number);
        this.number = Objects.requireNonNull(number);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean checkNumber(String number){
        return this.number.equals(number);
    }

    @Override
    public String toString() {
        return String.format("number: %s balance: %d",number,balance);
    }

    @Override
    public int hashCode(){
        return number.hashCode() * (int) balance *
                creationDate.hashCode() * expirationDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractAccount that = (AbstractAccount) obj;
        return balance == that.getBalance() &&
                number.equals(that.getNumber()) &&
                creationDate.equals(that.creationDate) &&
                expirationDate.equals(that.expirationDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractAccount abs = (AbstractAccount) super.clone();
        abs.setExpirationDate(expirationDate);
        abs.setCreationDate(creationDate);
        return abs;
    }
}
