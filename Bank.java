import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Bank {
    private ArrayList<Bank> bankList;
    private ArrayList<BankAccount> bankAccountList = new ArrayList<>();

    private String bankName;
    public String getBankName() {
        return bankName;
    }
    public ArrayList<BankAccount> getBankAccountList() {
        return bankAccountList;
    }
    public Bank(String bankName) {
        this.bankName = bankName;
        bankList = new ArrayList<Bank>();
    }

    public void AddBankAccount(BankAccount bankAccount_){
        bankAccountList.add(bankAccount_);
        bankAccount_.assignedToBank = true;
    }

    public void RemoveBankAccount(BankAccount bankAccount){
        bankAccountList.remove(bankAccount);
    }

    public double GetTotalBalance() {
        double totalBalance = 0;
        for (BankAccount bank : bankAccountList) {
            totalBalance += bank.GetBalance();
        }
        return totalBalance;
    }
}
