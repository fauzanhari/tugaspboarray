public class Bank {

  private Customer[] customerList;
  private int totalCustomers;

  public Bank() {
      customerList = new Customer[15];
      totalCustomers = 0;
  }

  public void addCustomer(String first, String last) {

      if (totalCustomers >= customerList.length) {
          System.out.println("Kapasitas customer sudah penuh.");
          return;
      }

      Customer newCustomer = new Customer(first, last);

      customerList[totalCustomers] = newCustomer;
      totalCustomers++;
  }

  public int getNumOfCustomers() {
      return totalCustomers;
  }

  public Customer getCustomer(int index) {
      return customerList[index];
  }
}