package ru.netology.VDubrovin;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private int operationsCount;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        operationsCount = 0;
    }

    public Client() {
        this.id = -1;
        this.name = "none";
        operationsCount = 0;
    }

    public int getId(){
        return this.id;
    }

    public int getOperationsCount() { return this.operationsCount; }

    public void setOperationsCount(int operationsCount) { this.operationsCount = operationsCount; }

    public void print(){
        String res = "ID клиента: " + this.id + "\n" +
                "Имя клиента: " + this.name;
        System.out.println(res);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Client client = (Client) obj;
        return id == client.id
                && (name != null && name.equals(client.name))
                && (operationsCount == client.operationsCount);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return("Имя клиента: " + this.name +
                "\nID клиента: " + this.id +
                "\nКол-во операций клиента: " + this.operationsCount);
    }
}