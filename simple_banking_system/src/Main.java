import main.Accounts;
import main.BankSystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankSystem system = new BankSystem();
        system.loadFromCSV("accounts.csv");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n银行系统菜单:");
            System.out.println("1. 创建账户");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("4. 转账");
            System.out.println("5. 保存并退出");
            System.out.print("选择一个选项: ");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    System.out.println("输入账户名称: ");
                    String name = scanner.nextLine();
                    System.out.println("输入初始余额：");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();
                    if (system.createAccount(name, balance)){
                        System.out.println("创建账号成功");
                    }else{
                        System.out.println("创建失败，账号已存在");
                    }break;
                case "2":
                    System.out.println("输入账户名称:");
                     name = scanner.nextLine();
                    System.out.println("输入存款金额:");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Accounts account = system.getAccount(name);
                    if (account != null && account.deposit(depositAmount)){
                        System.out.println("存款成功");
                    } else {
                        System.out.println("存款失败");
                    }break;
                case "3":
                    System.out.println("输入账户名称: ");
                    name = scanner.nextLine();
                    System.out.println("输入取款金额: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    account = system.getAccount(name);
                    if (account != null && account.withdraw(withdrawAmount)){
                        System.out.println("取款成功");
                    }else{
                        System.out.println("取款失败");
                    }break;
                case "4":
                    System.out.println("输入你的账户名称: ");
                    String fromName = scanner.nextLine();
                    System.out.println("输入目标账户名称: ");
                    String toName = scanner.nextLine();
                    System.out.print("输入转账金额: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Accounts fromAccount = system.getAccount(fromName);
                    Accounts toAccount = system.getAccount(toName);
                    if (fromAccount != null && toAccount != null && fromAccount.transfer(toAccount,transferAmount)){
                        System.out.println("转账成功");
                    } else {
                        System.out.println("转账失败");
                    }break;
                case "5":
                    try{
                        system.saveToCSV("account.csv");
                        System.out.println("已保存并退出");
                    }
                        catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("保存失败。");

                    }
                    return;
                default:
                    System.out.println("无效选项。请重试。");
            }
        }
    }
}
