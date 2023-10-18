package ru.netology.VDubrovin;

public class LoanOperation extends Operation implements ConsolePrintable{
    private int loanId;

    public LoanOperation() {
        loanId = -1;
    }

    public LoanOperation(int loanId) {
        this.loanId = loanId;
    }

    @Override
    public void printToConsole() {
        System.out.println("ID кредита: " + loanId);
    }
}
