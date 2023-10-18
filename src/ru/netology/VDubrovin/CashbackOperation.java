package ru.netology.VDubrovin;

import org.w3c.dom.ls.LSOutput;

public class CashbackOperation extends Operation implements ConsolePrintable{
    private int cashbackAmount;

    public CashbackOperation() {
        cashbackAmount = -1;
    }

    public CashbackOperation(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void printToConsole() {
        System.out.println("Сумма кэшбека составляет: " + cashbackAmount);
    }
}