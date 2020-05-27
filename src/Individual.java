import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Individual implements Client, Cloneable {
    private String title;
    private Account[] accounts;
    private int size;
    private int creditScore;

    public static final int SIXTEEN = 16;
    public static final int ZERO = 0;

    public Individual(String title) {
        this.title = title;
        this.accounts = new Account[SIXTEEN];
        this.size = ZERO;
        this.creditScore = ZERO;
    }

    public Individual(int size, String title) {
        this.title = title;
        this.accounts = new Account[size];
        this.size = ZERO;
        this.creditScore = ZERO;
    }

    public Individual(String title, Account[] accounts) {
        this.title = title;
        this.accounts = accounts;
        this.size = accounts.length;
        this.creditScore = ZERO;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean add(Account account) {
        Objects.requireNonNull(account);
        if (size == accounts.length) {
            extendArray();
            return false;
        } else {
            hideAdd(account);
            return true;
        }
    }

    private void hideAdd(Account account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                size++;
                return;
            }
        }
    }

    private void extendArray() {
        Account[] buf = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, buf, 0, accounts.length);
        accounts = buf;
    }

    @Override
    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public void addCreditScore(int creditScore) {
        this.creditScore += creditScore;
    }

    @Override
    public Account[] getCreditAccounts() {
        ArrayList<Account> acs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (accounts[i] instanceof CreditAccount) acs.add(accounts[i]);
        }
        return (Account[]) acs.toArray();
    }

    @Override
    public boolean add(Account account, int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Objects.requireNonNull(account);
        if (accounts[index] == null) {
            accounts[index] = account;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Account get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return accounts[index];
    }

    @Override
    public Account get(String number) {
        Objects.requireNonNull(number);
        Utils.checkNumber(number);
        for (int i = 0; i < size; i++) {
            if (accounts[i].checkNumber(number)) return accounts[i];
        }
        return null;
    }

    @Override
    public boolean hasAccountWithNumber(String number) {
        Objects.requireNonNull(number);
        Utils.checkNumber(number);
        for (int i = 0; i < size; i++) {
            if (accounts[i].checkNumber(number)) return true;
        }
        return false;
    }

    @Override
    public Account set(Account account, int index) {
        Objects.requireNonNull(account);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Account buf = accounts[index];
        accounts[index] = account;
        return buf;
    }

    @Override
    public Account remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Account buf = accounts[index];
        accounts[index] = null;
        for (int i = index; i < accounts.length - 1; i++) {
            Account tmp = accounts[i];
            accounts[i] = accounts[i + 1];
            accounts[i + 1] = tmp;
        }
        accounts[accounts.length] = null;
        size--;
        return buf;
    }

    @Override
    public Account remove(String number) {
        Objects.requireNonNull(number);
        Utils.checkNumber(number);
        return remove(indexOf(number));
    }

    @Override
    public boolean remove(Account account) {
        Objects.requireNonNull(account);
        if (indexOf(account) >= 0) {
            remove(indexOf(account));
            return true;
        } else return false;
    }

    @Override
    public int indexOf(String number) {
        Objects.requireNonNull(number);
        Utils.checkNumber(number);
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(number)) return i;
        }
        return -1;
    }

    @Override
    public int indexOf(Account account) {
        Objects.requireNonNull(account);
        for (int i = 0; i < size; i++) {
            if (accounts[i].equals(account)) return i;
        }
        return -1;
    }

    //Возвращает общее число счетов
    @Override
    public int size() {
        return size;
    }

    //С учётом того что после каждого удаления массив сдвигается, нулов быть не должно
    @Override
    public Account[] getAccounts() {
        Account[] buf = new Account[size];
        System.arraycopy(accounts, 0, buf, 0, size);
        return buf;
    }

    @Override
    public Account[] getSortedAccountsByBalance() {
        return Utils.sort(accounts.clone());
    }

    @Override
    public double getTotalBalance() {
        double totalBalance = 0;
        for (int i = 0; i < size; i++) {
            totalBalance += accounts[i].getBalance();
        }
        return totalBalance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client\nname:");
        sb.append(title).append("\ncreditScore: ").append(creditScore);
        for (int i = 0; i < size; i++) {
            sb.append(accounts[i].toString()).append('\n');
        }
        sb.append("total: ").append(getTotalBalance());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = title.hashCode() ^ creditScore;
        for (int i = 0; i < size; i++) {
            hash ^= accounts[i].hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Individual that = (Individual) obj;
        return size == that.size &&
                creditScore == that.creditScore &&
                Objects.equals(title, that.title) &&
                Arrays.equals(accounts, that.accounts);
    }

    public Object clone() throws CloneNotSupportedException {
        Individual buf = new Individual(size, title);
        buf.creditScore = this.creditScore;
        Account[] bufAcs = new Account[accounts.length];
        System.arraycopy(accounts, 0, bufAcs, 0, size);
        buf.accounts = bufAcs;
        return buf;
    }

    @Override
    public double debtTotal() {
        double totalDebt = 0;
        for (int i = 0; i < size; i++) {
            if (accounts[i].getBalance() < 0) totalDebt += accounts[i].getBalance();
        }
        return Math.abs(totalDebt);
    }
}
