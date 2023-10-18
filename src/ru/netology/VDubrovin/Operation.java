package ru.netology.VDubrovin;

public class Operation implements ConsolePrintable{
    private int operationID;
    private int sum;
    private String currency;
    private String merchant;

    public Operation(int operationID, int sum, String currency, String merchant) {
        this.operationID = operationID;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
    }

    public Operation() {
        this.operationID = -1;
        this.sum = -1;
        this.currency = "none";
        this.merchant = "none";
    }

    @Override
    public String toString() {
        String res = "Транзакция #" + this.operationID + "\n" + "Сумма транзакции: " + this.sum + " " + this.currency + " магазин: " + this.merchant;
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Operation operation = (Operation) obj;
        return operationID == operation.operationID
                && (sum == operation.sum)
                && (currency != null && currency.equals(operation.currency))
                && (merchant != null && merchant.equals(operation.merchant));
    }

    @Override
    public int hashCode() {
        return this.operationID;
    }

    public void print(){
        System.out.println((this.toString()));
    }

    @Override
    public void printToConsole() {
        System.out.println("Сумма транзакции: " + this.sum + " " + this.currency + "\nМагазин: " + this.merchant);
    }
}