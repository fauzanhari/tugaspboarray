public class Customer {

  private String firstName;
  private String lastName;

  private Account[] accountList;
  private int totalAccounts;

  public Customer(String first, String last) {
      firstName = first;
      lastName = last;

      accountList = new Account[5];
      totalAccounts = 0;
  }

  public String getFirstName() {
      return firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setAccount(Account newAccount) {
      if (totalAccounts >= accountList.length) {
          System.out.println("Jumlah account sudah mencapai batas.");
          return;
      }

      accountList[totalAccounts] = newAccount;
      totalAccounts++;
  }

  public Account getAccount(int index) {
      return accountList[index];
  }

  public int getNumOfAccounts() {
      return totalAccounts;
  }
}