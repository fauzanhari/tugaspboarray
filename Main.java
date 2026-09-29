import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Bank myBank = new Bank();

        // Data awal customer
        myBank.addCustomer("Rizky", "Ramadhan");
        myBank.addCustomer("Nadia", "Putri");
        myBank.addCustomer("Fajar", "Hidayat");

        // Memberikan account kepada customer pertama
        Customer rizky = myBank.getCustomer(0);
        rizky.setAccount(new Account(750000));
        rizky.setAccount(new Account(1250000));

        // Customer kedua
        Customer nadia = myBank.getCustomer(1);
        nadia.setAccount(new Account(1800000));

        // Customer ketiga
        Customer fajar = myBank.getCustomer(2);
        fajar.setAccount(new Account(950000));

        int menu;

        do {

            System.out.println();
            System.out.println("===============================");
            System.out.println("|SELAMAT DATANG DI FAUZAN BANK|");
            System.out.println("===============================");
            System.out.println("1. Data Customer");
            System.out.println("2. Tambah Customer");
            System.out.println("3. Tambah Account");
            System.out.println("4. Informasi Account");
            System.out.println("5. Setor Uang");
            System.out.println("6. Tarik Uang");
            System.out.println("0. Selesai");
            System.out.println("===============================");
            System.out.print("Masukkan pilihan pelinggih: ");

            menu = scanner.nextInt();

            switch (menu) {

                case 1:
                    showCustomers(myBank);
                    break;

                case 2:
                    scanner.nextLine();

                    System.out.println("\n--- Tambah Customer ---");

                    System.out.print("Nama depan    : ");
                    String first = scanner.nextLine();

                    System.out.print("Nama belakang : ");
                    String last = scanner.nextLine();

                    myBank.addCustomer(first, last);

                    System.out.println("Data customer telah ditambahkan.");
                    break;

                case 3:
                    Customer selectedCustomer =
                            selectCustomer(myBank, scanner);

                    if (selectedCustomer != null) {

                        System.out.print(
                                "Masukkan saldo awal: Rp"
                        );

                        double initialBalance =
                                scanner.nextDouble();

                        if (initialBalance >= 0) {

                            Account newAccount =
                                    new Account(initialBalance);

                            selectedCustomer.setAccount(
                                    newAccount
                            );

                            System.out.println(
                                    "Account baru berhasil ditambahkan."
                            );

                        } else {

                            System.out.println(
                                    "Saldo awal tidak boleh negatif."
                            );
                        }
                    }

                    break;

                case 4:
                    Customer accountOwner =
                            selectCustomer(myBank, scanner);

                    if (accountOwner != null) {
                        showAccounts(accountOwner);
                    }

                    break;

                case 5:
                    Customer depositOwner =
                            selectCustomer(myBank, scanner);

                    if (depositOwner != null) {

                        Account selectedAccount =
                                selectAccount(
                                        depositOwner,
                                        scanner
                                );

                        if (selectedAccount != null) {

                            System.out.print(
                                    "Nominal setor: Rp"
                            );

                            double amount =
                                    scanner.nextDouble();

                            if (selectedAccount.deposit(amount)) {

                                System.out.println(
                                        "Setor uang berhasil."
                                );

                                System.out.println(
                                        "Saldo terbaru: Rp"
                                        + selectedAccount
                                                .getBalance()
                                );

                            } else {

                                System.out.println(
                                        "Nominal setor tidak valid."
                                );
                            }
                        }
                    }

                    break;

                case 6:
                    Customer withdrawOwner =
                            selectCustomer(myBank, scanner);

                    if (withdrawOwner != null) {

                        Account selectedAccount =
                                selectAccount(
                                        withdrawOwner,
                                        scanner
                                );

                        if (selectedAccount != null) {

                            System.out.print(
                                    "Nominal penarikan: Rp"
                            );

                            double amount =
                                    scanner.nextDouble();

                            if (selectedAccount.withdraw(amount)) {

                                System.out.println(
                                        "Penarikan berhasil."
                                );

                                System.out.println(
                                        "Sisa saldo: Rp"
                                        + selectedAccount
                                                .getBalance()
                                );

                            } else {

                                System.out.println(
                                        "Penarikan gagal."
                                );

                                System.out.println(
                                        "Periksa nominal atau saldo account."
                                );
                            }
                        }
                    }

                    break;

                case 0:
                    System.out.println();
                    System.out.println(
                            "Program Bank Mini selesai."
                    );
                    break;

                default:
                    System.out.println(
                            "Menu yang dipilih tidak tersedia."
                    );
            }

        } while (menu != 0);

        scanner.close();
    }


    // Menampilkan semua customer
    public static void showCustomers(Bank bank) {

        System.out.println();
        System.out.println("--- DATA CUSTOMER ---");

        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada customer.");
            return;
        }

        for (int index = 0;
             index < bank.getNumOfCustomers();
             index++) {

            Customer data = bank.getCustomer(index);

            System.out.println(
                    (index + 1) + ". "
                    + data.getFirstName()
                    + " "
                    + data.getLastName()
                    + " | Account: "
                    + data.getNumOfAccounts()
            );
        }
    }


    // Menampilkan account milik customer
    public static void showAccounts(Customer customer) {

        System.out.println();
        System.out.println(
                "--- ACCOUNT "
                + customer.getFirstName().toUpperCase()
                + " ---"
        );

        if (customer.getNumOfAccounts() == 0) {
            System.out.println(
                    "Customer belum mempunyai account."
            );
            return;
        }

        for (int index = 0;
             index < customer.getNumOfAccounts();
             index++) {

            Account data =
                    customer.getAccount(index);

            System.out.println(
                    "Account "
                    + (index + 1)
                    + " -> Saldo Rp"
                    + data.getBalance()
            );
        }
    }


    // Memilih customer
    public static Customer selectCustomer(
            Bank bank,
            Scanner scanner) {

        showCustomers(bank);

        if (bank.getNumOfCustomers() == 0) {
            return null;
        }

        System.out.print(
                "Nomor customer: "
        );

        int choice = scanner.nextInt();

        int index = choice - 1;

        if (index < 0 ||
            index >= bank.getNumOfCustomers()) {

            System.out.println(
                    "Nomor customer tidak valid."
            );

            return null;
        }

        return bank.getCustomer(index);
    }


    // Memilih account
    public static Account selectAccount(
            Customer customer,
            Scanner scanner) {

        showAccounts(customer);

        if (customer.getNumOfAccounts() == 0) {
            return null;
        }

        System.out.print(
                "Nomor account: "
        );

        int choice = scanner.nextInt();

        int index = choice - 1;

        if (index < 0 ||
            index >= customer.getNumOfAccounts()) {

            System.out.println(
                    "Nomor account tidak valid."
            );

            return null;
        }

        return customer.getAccount(index);
    }
}