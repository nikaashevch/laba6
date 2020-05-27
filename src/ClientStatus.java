public enum ClientStatus {
    GOOD(0),
    GOLD(3),
    PLATINUM(5),
    RISKY(-2),
    BAD(-4);

    private int creditScoreBound;

    ClientStatus(int creditScoreBound) {
        this.creditScoreBound = creditScoreBound;
    }

    public int getCreditScoreBound(){
        return creditScoreBound;
    }
}
