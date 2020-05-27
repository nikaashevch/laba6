public interface Client {
    public String getTitle();
    public void setTitle(String title);
    public boolean add(Account account);
    public boolean add(Account account,int index);
    public Account get(int index);
    public Account get(String number);
    public boolean hasAccountWithNumber(String number);
    public Account set(Account account,int index);
    public Account remove(int index);
    public Account remove(String number);
    public boolean remove(Account account);
    public int size();
    public Account[] getAccounts();
    public Account[] getSortedAccountsByBalance();
    public double getTotalBalance();
    public int indexOf(String number);
    public int indexOf(Account account);
    public int getCreditScore();
    public void addCreditScore(int creditScore);
    public Account[] getCreditAccounts();
    public double debtTotal();

    public default ClientStatus getStatus(){
        if(getCreditScore()>=ClientStatus.PLATINUM.getCreditScoreBound()) return ClientStatus.PLATINUM;
        if(getCreditScore()>=ClientStatus.GOLD.getCreditScoreBound()) return ClientStatus.GOLD;
        if(getCreditScore()>=ClientStatus.GOOD.getCreditScoreBound()) return ClientStatus.GOOD;
        if(getCreditScore()>=ClientStatus.RISKY.getCreditScoreBound()) return ClientStatus.RISKY;
        else return ClientStatus.BAD;
    }
}
