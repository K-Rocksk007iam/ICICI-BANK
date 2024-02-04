package userinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import dao.AccountDao;
import entity.Account;
import validation.AccountValidation;
import operation.AccountOperation;

class TestAccount1 {
	public static void sample(Connection con) throws SQLException {
		Scanner sp = new Scanner(System.in);
		System.out.println("Enter balance");
		int inp1 = sp.nextInt();
		System.out.println("Enter bettween");
		int inp2 = sp.nextInt();
		System.out.println("Enter bettween");
		int inp3 = sp.nextInt();

		String res = "select * from account where accBal>? and accNo between ? and ?";

		PreparedStatement ps = con.prepareStatement(res);
		ps.setDouble(1, inp1);
		ps.setInt(2, inp2);
		ps.setInt(3, inp3);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) { // column

			// System.out.println("---------------------------------------------------");
			System.out.println("no is " + rs.getInt("accNo"));
			System.out.println("name is " + rs.getString("accHolderName"));
			System.out.println("salary is " + rs.getDouble("accBal"));
			System.out.println("---------------------------------------------------");
		}
	}
}

public class TestAccount {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt", "root", "password");
		TestAccount1 account = new TestAccount1();
		TestAccount1.sample(con);

		Account account1 = new Account();
		Account account2 = new Account(2, "ismail bhai", 30000);
		AccountOperation accountoperation = new AccountOperation();
		AccountValidation accountvalidation = new AccountValidation();
		AccountDao accountDao = new AccountDao();

		Scanner sc = new Scanner(System.in);
		int num;
		String choice;

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt", "root", "password");
		System.out.println("connection success\n-----------------------");

		do {
			System.out.println("WelCome To ICICI Bank");
			System.out.println("1.Accept details...");
			System.out.println("2.Display details...");
			System.out.println("3.Deposit Amount...");
			System.out.println("4.Withdraw Amount...");
			System.out.println("5.Transfer Amount...");
			System.out.println("6.Change the Name...");
			System.out.println("7.Chack Balance...");
			System.out.println("8.Delete Record...");
			System.out.println("9.update Record...");
			System.out.println("10.all Record...");
			System.out.println("11....Exit...");
			System.out.println("-------------------------------------------");
			System.out.println("Enter Number...");
			num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("1.Accept details...");
				System.out.println("AccNo,AccountHolders Name,Account Balance");
				int accNo = sc.nextInt();
				String accHolderName = sc.next();
				double balance = sc.nextDouble();
				boolean result = accountvalidation.checkAll(accNo, accHolderName, balance);
				if (result == true) {
					System.out.println("Valid Inputs");
					account1.setAccNo(accNo);
					account1.setAccHolderName(accHolderName);
					account1.setAccBal(balance);
					accountDao.insertRecord(con, account1);

				} else {
					System.out.println("invalid Inputs....");
				}
				System.out.println("-------------------------------------------");
				break;
			case 2:
				System.out.println("Displayling the details...");
				// System.out.println("-------------------------------------------");
				// accountDao.allRecordsArrayList(con);
//				System.out.println("Account No is:- " + account1.getAccNo());
//				System.out.println("Account Holder Name is:- " + account1.getAccHolderName());
//				System.out.println("Account Balance is:- " + account1.getAccBal());
//				System.out.println("-------------------------------------------");

				ArrayList<Account> arrayAccount = new ArrayList<Account>();
				arrayAccount = accountDao.allRecordsArrayList(con);
				for (int i = 0; i < arrayAccount.size(); i++) {
					System.out.println(arrayAccount.get(i));
					System.out.println("-------------------------------------------");
				}

				break;
			case 3:

				System.out.println("Deposit Amount...");
				System.out.println("Enter the Amount for the deposit");
				double amt = sc.nextDouble();
				System.out.println("Enter the Account number");
				int ano = sc.nextInt();
				accountoperation.Diposit(con, amt, ano);
				System.out.println("Deposit is Successful");

//				if (accountoperation.Diposit(con, account1, accountDao, amt)) {
//					System.out.println("Diposit is Successful");
//					System.out.println("New Balance" + account1.getAccBal());
//				}
				System.out.println("-------------------------------------------");
				break;

			case 4:

				System.out.println("Withdraw Amount...");
				System.out.println("Enter Amount for Withdraw");
				double amt1 = sc.nextDouble();
				System.out.println("Enter the Account number");
				int ano1 = sc.nextInt();
				accountoperation.withdraw(con, account1, amt1, ano1);
				System.out.println("withdaw is Successful");

//				double amt1 = sc.nextDouble();
//
//				boolean result2 = accountoperation.withdraw(account2, amt1);
//
//				if (result2 == true) {
//					System.out.println("Withdraw is Successful");
//					System.out.println("New Balance" + account2.getAccBal());
//				} else {
//					System.out.println("There is an error");
//				}
//				System.out.println("-------------------------------------------");
				break;

			case 5:

				System.out.println("Transfer Amount...");
				System.out.println("Enter receivers account number ..");
				int accNoM = sc.nextInt();
				System.out.println("Enter Senders account number ..");
				int accNoS = sc.nextInt();
				System.out.println("Enter Amount to transfer..");
				double amt3 = sc.nextDouble();

				accountoperation.Transfar(con, account1, account2, amt3, accNoM, accNoS);
				boolean result3 = accountoperation.Transfar(con, account1, account2, amt3, accNoM, accNoS);

				if (result3 == true) {
					System.out.println("New Balance" + account2.getAccBal());
					System.out.println("Current Account Balance is..." + account1.getAccBal());
					System.out.println("Current Account2 Balance is..." + account2.getAccBal());
				} else {
					System.out.println("There is an error");
				}
				System.out.println("-------------------------------------------");
				break;

			case 6:

				System.out.println("Change the Name...");
				System.out.println("Enter New Name");
				String name = sc.next();
				boolean result5 = accountvalidation.checkName(name);
				if (result5 == true) {
					account2.setAccHolderName(name);
				}
				System.out.println(account2);
				System.out.println("-------------------------------------------");
				break;
			case 7:

				System.out.println("Chack Balance...");
				System.out.println("Enter the account number");
				int accno = sc.nextInt();
				if (accno == 1) {
					System.out.println("Account Balance is:-" + account1.getAccBal());
				} else if (accno == 2) {
					System.out.println("Account Balace is:-" + account2.getAccBal());
				} else {
					System.out.println("Invalid Input...");
				}
				System.out.println("-------------------------------------------");
				break;

			case 8:
				System.out.println("Enter the account number to be deleted");
				int accno1 = sc.nextInt();
				accountDao.deleteRecord(con, accno1);
				break;

			case 9:
				System.out.println("Enter the increment and account Holder name to be updated");
				int incre = sc.nextInt();
				String holderName = sc.next();
				accountDao.updateRecord(con, incre, holderName);
				break;

			case 10:
				System.out.println("All records are as follow");
				accountDao.allRecords(con, account1);
				break;

			case 11:
				System.exit(0);

				break;
			default:
				System.out.println("..*..Invalid Input..*..");
			}
			System.out.println("Do you want to continoue ?(Y/y)");
			choice = sc.next();
		} while (choice.equals("Y") || choice.equals("y"));

		System.out.println("..*..Thanks for visiting..*..");
	}

}
