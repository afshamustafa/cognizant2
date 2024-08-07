package bankaccount;

public class BankAccountTest {

    static class BankAccount {
        private int balance = 0;

        public synchronized void deposit(int amount) {
            System.out.println(Thread.currentThread().getName() + " is depositing: " + amount);
            int newBalance = balance + amount;
            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
            System.out.println(Thread.currentThread().getName() + " new balance after deposit: " + balance);
        }

        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing: " + amount);
                int newBalance = balance - amount;
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = newBalance;
                System.out.println(Thread.currentThread().getName() + " new balance after withdrawal: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for withdrawal: " + amount);
            }
        }

        public int getBalance() {
            return balance;
        }
    }

    static class Transaction extends Thread {
        private final BankAccount account;
        private final boolean deposit;
        private final int amount;

        public Transaction(BankAccount account, boolean deposit, int amount) {
            this.account = account;
            this.deposit = deposit;
            this.amount = amount;
        }

        @Override
        public void run() {
            if (deposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Create and start multiple transaction threads
        Transaction t1 = new Transaction(account, true, 1000); 
        Transaction t2 = new Transaction(account, false, 50); 
        Transaction t3 = new Transaction(account, true, 2000); 
        Transaction t4 = new Transaction(account, false, 1150); 

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}

