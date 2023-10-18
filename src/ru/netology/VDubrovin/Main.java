package ru.netology.VDubrovin;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int MAXOPERATIONSCOUNT = 100;
    public static Operation[] operations = new Operation[MAXOPERATIONSCOUNT];
    public static Client[] clients = new Client[MAXOPERATIONSCOUNT];
    public static int[][] statement = new int[MAXOPERATIONSCOUNT][MAXOPERATIONSCOUNT];
    public static int operationNumber = 0;


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Dialog(scanner);
        System.out.println();
        scanner.nextLine();

        while (true) {
            System.out.println("Выберите что сделать: 1 - вывести информацию о транзакциях, 2 - вывести информацию о клиентах, 3 - вывести транзакции определенного клиента, 4 - закончить");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 1) {
                for (Operation operation : operations)
                    if (operation != null)
                        operation.print();
                System.out.println();
            }
            else if (answer == 2) {
                for (Client client : clients)
                    if (client != null) {
                        client.print();
                        System.out.println();
                    }
            }
            else if (answer == 3)
                printClientTransactions(scanner);
            else if (answer == 4)
                break;
        }
    }

    public static Operation[] getClientOperations(int clientId){
        int operationsCount = clients[clientId].getOperationsCount();
        Operation[] res = new Operation[operationsCount];
        for (int i = 0; i < operationsCount; i++)
            res[i] = operations[statement[clientId][i]];
        return res;
    }

    public static void Dialog(Scanner scanner) throws Exception {
        while (true) {
            makeOperation(scanner);

            if (operationNumber == MAXOPERATIONSCOUNT - 1) {
                throw new OperationException();
            }

            System.out.println("Продолжить ввод транзакций(после 100 транзакций ввод остановится)? (0 - нет, 1 - да)");
            int answer = scanner.nextInt();
            while (answer != 0 && answer != 1) {
                System.out.println("Продолжить ввод транзакций(после 100 транзакций ввод остановится)? (0 - нет, 1 - да)");
                answer = scanner.nextInt();
            }
            if (answer == 0)
                break;
            operationNumber++;
        }
    }

    public static void makeOperation(Scanner scanner) throws Exception{
        System.out.println("Введите сумму транзакции");
        int sum = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите валюту транзакции (rub, usd, eu)");
        String currency = scanner.nextLine();

        if (!currency.equals("rub") && !currency.equals("usd") && !currency.equals("eu")) {
            throw new OperationException();
        }

        System.out.println("Введите магазин, где совершилась транзакция");
        String merchant = scanner.nextLine();

        System.out.println("Введите id покупателя");
        int clientID = Integer.parseInt(scanner.nextLine());
        if (clientID < 0 || clientID > 99) {
            throw new OperationException();
        }
        if (clients[clientID] == null) {
            System.out.println("Это новый покупатель, введите его имя");
            String clientName = scanner.nextLine();
            clients[clientID] = new Client(clientID, clientName);
        }

        clients[clientID].setOperationsCount(clients[clientID].getOperationsCount() + 1);
        operations[operationNumber] = new Operation(operationNumber, sum, currency, merchant);
        statement[clientID][clients[clientID].getOperationsCount() - 1] = operationNumber;
    }

    public static void printClientTransactions(Scanner scanner) {
        System.out.println("Введите ID клиента");
        int id = Integer.parseInt(scanner.nextLine());
        boolean haveclient = false;
        for (Client client : clients)
            if (client != null && client.getId() == id) {
                haveclient = true;
                break;
            }
        if (!haveclient)
            System.out.println("Клиента с таким ID нет");
        else {
            clients[id].print();
            System.out.println("Транзакции клиента:");
            for (Operation operation : getClientOperations(clients[id].getId()))
                if (operation != null)
                    operation.print();
        }
        System.out.println();
    }
}