import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.StageStyle;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Bank> bankList = new ArrayList<>();
    static ArrayList<BankAccount> bankAccountList = new ArrayList<>();

    public static void main(String[] args) {
        // BankAccount accTabungAnak = new BankAccount("Izat", 5000, "Acc Anak");
        // accTabungAnak.GetAccountDetails();

        // BankAccount accTabungFamily = new BankAccount("Izat", 3000, "Acc Family");
        // accTabungFamily.GetAccountDetails();

        // BankAccount accTabungEmergency = new BankAccount("Nadia", 2000, "Acc
        // Emergency");
        // accTabungEmergency.GetAccountDetails();

        // ArrayList<BankAccount> accList = new ArrayList<BankAccount>();
        // accList.add(accTabungAnak);
        // accList.add(accTabungFamily);
        // accList.add(accTabungEmergency);

        // Bank bank = new Bank("Maybank");
        // bank.AddBankAccount(accList);

        // Print("Total balance af all account is " + bank.GetTotalBalance() + "\n");

        ClearConsole();

        Print("# Simple Banking Application #\n");
        Print("=======================================");
        Print("[1] Create bank");
        Print("[2] Create bank account");
        Print("[3] Add available bank account into bank");
        Print("[4] Make a deposit to bank account");
        Print("[5] Make a withdrawal from bank account");
        Print("=======================================");
        Print("\nAvailable bank: ");

        for (Bank bank : bankList) {
            try {
                Print("- " + bank.getBankName());
                for (BankAccount bankAccount : bank.getBankAccountList()) {
                    Print("   |-" + bankAccount.getDescription());

                }
            } catch (Exception ex) {
            }

        }

        Print("\nBank account available to be assigned to bank: ");

        for (BankAccount bankAccount : bankAccountList) {
            try {
                if (bankAccount.assignedToBank == false) {
                    Print("- " + bankAccount.getDescription());

                }

            } catch (Exception ex) {
            }

        }

        Print("\nSelect option:");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                CreateBank();
                break;
            case "2":
                CreateBankAccount();
                break;
            case "3":
                AddBankAccountIntoBank();
                break;
            case "4":
                break;
            case "5":
                break;
            default:

        }

        main(args);
    }

    private static void AddBankAccountIntoBank() {
        ClearConsole();
        if (bankAccountList.isEmpty()) {
            PrintError("No bank account available to assigned into bank. Please create one.");
            Print("Press enter to continue..");
            scanner.nextLine();
            return;
        }

        if (bankList.isEmpty()) {
            PrintError("No bank available. Please create one.");
            Print("Press enter to continue..");
            scanner.nextLine();
            return;
        }

        int num = 0;
        int num_ = 0;

        ArrayList<BankAccount> dummyList = new ArrayList<>();
        for (BankAccount bankAccount : bankAccountList) {
            try {
                if (bankAccount.assignedToBank == false) {
                    Print("[" + num + "] " + bankAccount.getDescription());
                    num++;
                    dummyList.add(bankAccount);
                }

            } catch (Exception ex) {
                PrintError(ex.getLocalizedMessage());
                Print("Press enter to continue..");
                scanner.nextLine();
                return;
            }

        }

        if(dummyList.size() < 1){
            PrintError("No available bank account to be assigned.");
                Print("Press enter to continue..");
                scanner.nextLine();
                return;
        }

        Print("\nSelect bank account:");

        // try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index >= 0 && index < bankAccountList.size()) {
                BankAccount bAccount = bankAccountList.get(index);

                ClearConsole();

                for (Bank bank : bankList) {
                    try {
                        Print("[" + num_ + "] " + bank.getBankName());
                        num_++;
                    } catch (Exception ex) {
                    }

                }

                Print("\nSelect bank:");
                int indexBank = Integer.parseInt(scanner.nextLine());
                if (indexBank >= 0 && indexBank < bankList.size()) {
                    Bank bank = bankList.get(indexBank);
                    bank.AddBankAccount(bAccount);
                    bankAccountList.remove(bAccount);
                    Print(bAccount.getDescription() + " is assigned to " + bank.getBankName());
                    Print("Press enter to continue..");
                    scanner.nextLine();
                    return;
                } else {
                    PrintError("Index is out of array");
                    Print("Press enter to continue..");
                    scanner.nextLine();
                    return;
                }
            } else {
                PrintError("Index is out of array");
                Print("Press enter to continue..");
                scanner.nextLine();
                return;
            } // the item does not exist
        // } catch (Exception ex) {
        //     PrintError(ex.getLocalizedMessage());
        //     Print("Press enter to continue..");
        //     scanner.nextLine();
        //     return;
        // }

    }

    private static void CreateBankAccount() {
        ClearConsole();
        String accountHoldername = "";
        double deposit = 0;
        String accountDescription = "";

        try {
            Print("Enter account holder's Name:");
            accountHoldername = scanner.nextLine();

            Print("Enter amount to deposit (RM):");
            deposit = Double.parseDouble(scanner.nextLine());

            Print("Enter account description:");
            accountDescription = scanner.nextLine();
        } catch (Exception ex) {
            PrintError(ex.getLocalizedMessage());
            Print("Press enter to continue..");
            scanner.nextLine();
            return;
        }

        BankAccount bankAcc = new BankAccount(accountHoldername, deposit, accountDescription);
        bankAccountList.add(bankAcc);

        Print("\nNew bank account created. Press enter to continue..");
        scanner.nextLine();
    }

    private static void CreateBank() {
        ClearConsole();
        Print("Enter Bank Name:");
        String bankName = scanner.nextLine();

        if (bankName.isEmpty()) {
            PrintError("Bank name cannot be empty.");
            Print("Press enter to continue..");
            scanner.nextLine();
            return;
        }

        Bank bank = new Bank(bankName);
        bankList.add(bank);

        Print("\nNew bank created. Press enter to continue..");
        scanner.nextLine();
    }

    private static void Print(String text) {
        System.out.println(text);
    }

    private static void PrintError(String text) {
        System.out.println("[Error] " + text + "\n");
    }

    private static void ClearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
