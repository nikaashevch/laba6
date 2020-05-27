import java.time.LocalDate;

public interface Credit {
    public double getAPR();
    public void setAPR(double APR);
    public double nextPaymentValue();
    public LocalDate nextPaymentDate();
}
