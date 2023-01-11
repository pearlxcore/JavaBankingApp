import java.util.Random;

public class BankAccount {
    public boolean assignedToBank;
    private String accHolderName;
    private double balance;
    public double GetBalance() {
        return balance;
    }
    public String getDescription() {
        return accountDescription;
    }

    private long accountNumber;
    private String accountDescription;

    public BankAccount(String accHolderName, double balance, String accountDescription) {
        this.accHolderName = accHolderName;
        this.balance = balance;
        this.accountDescription = accountDescription;
        assignedToBank = false;
        this.accountNumber = GenerateAccountNumber();
    }

    public void Deposit(double deposit){
        if(deposit < 0){
            System.out.println("Deposit amount cannot be negative number.");
            return;
        }
        this.balance += deposit;
    }

    public void Withdraw(double amount){
        if(balance < 0){
            System.out.println("Balance is below than 0");
            return;
        }
        else if(amount > balance){
            System.out.println("Withdrawal amount is larger than total balance.");
            return;
        }
        else if(amount < 0){
            System.out.println("Withdrawal amount cannot be negative number.");
            return;
        }
        balance = balance - amount;
    }

    public void GetAccountDetails(){
        System.out.println(String.format("Account holder' name for %1$s is %2$s.\nBalance is %3$s.\nAccount description: %4$s\n", Long.toString(accountNumber), accHolderName, Double.toString(balance), accountDescription));
    }

    public long GenerateAccountNumber(){
        //10 digit length
        //using for loop
        long accountNumber = 0;
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            accountNumber = accountNumber * 10 + rnd.nextInt(10);
        }
        return accountNumber;
    }
}

