public class Account {

  protected double balance;

  public Account(double initialBalance) {
      balance = initialBalance;
  }

  public double getBalance() {
      return balance;
  }

  public boolean deposit(double nominal) {
      if (nominal <= 0) {
          return false;
      }

      balance += nominal;
      return true;
  }

  public boolean withdraw(double nominal) {
      if (nominal <= 0 || nominal > balance) {
          return false;
      }

      balance -= nominal;
      return true;
  }
}