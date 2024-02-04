package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.AccountDao;
import entity.Account;

public class AccountOperation {

	public boolean withdraw(Connection con, Account account, double amt, int accNo) throws SQLException {
		if (amt < 0) {
			System.out.println("Invalid Amount Enter...");
			return false;
		} else if (amt > account.getAccBal() && accNo == account.getAccNo()) {
			System.out.println("insufficient Balance...");
			return false;
		} else {
			String str = "update account set accBal=accBal-? where accNo=?";

			PreparedStatement ps = con.prepareStatement(str);
			ps.setDouble(1, amt);
			ps.setInt(2, accNo);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("withdraw Process Completed..");
			} else {
				System.out.println("withdraw not completed");

//			double accBalace = account.getAccBal() - amt;
//			account.setAccBal(accBalace);
//			System.out.println("Withdraw proccess is Successful...");

			}
		}
		return true;

	}

	public boolean Diposit(Connection con, double amt, int accNo) throws SQLException {
		if (amt < 0) {
			System.out.println("Invalid Amount Enter...");
			return false;
		} else {
			String str = "update account set accBal=accBal+? where accNo=?";

			PreparedStatement ps = con.prepareStatement(str);
			ps.setDouble(1, amt);
			ps.setInt(2, accNo);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Deposit Process Completed..");
			} else {
				System.out.println("Deposit not completed");
			}
//			double accBalace = account.getAccBal() + amt;
//			account.setAccBal(accBalace);
//			// jdbc code
//			accountDao.updateDeposit(con, account);
//
//			System.out.println("Deposit Process Completed..");
//			System.out.println("your current account balance is ..." + account.getAccBal());
//			return true;
		}
		return true;

	}

	public boolean Transfar(Connection con, Account account1, Account account2, double amt, int accNo1, int accNo2)
			throws SQLException {
		if (amt < 0) {
			System.out.println("Invalid Amount Enter...");
			return false;
	} 
//			else if (amt > account1.getAccBal()) {
//			System.out.println("insufficient Balance...");
//			return false;
//		} 
			else {
			String str1 = "update account set accBal=accBal+? where accNo=?";
			String str2 = "update account set accBal=accBal-? where accNo=?";

			PreparedStatement ps1 = con.prepareStatement(str1);
			PreparedStatement ps2 = con.prepareStatement(str2);
			ps1.setDouble(1, amt);
			ps1.setInt(2, accNo1);

			ps2.setDouble(1, amt);
			ps2.setInt(2, accNo2);

			int i1 = ps1.executeUpdate();
			int i2 = ps2.executeUpdate();

			if (i1 > 0 && i2 > 0) {
				System.out.println("Transfer Process Completed..");
			} else {
				System.out.println("Transfer not completed");
			}

//			double accBalace = account1.getAccBal() - amt;
//			account1.setAccBal(accBalace);
//
//			double balance = account2.getAccBal();
//			double newBalance = balance + amt;
//			account2.setAccBal(newBalance);
//			System.out.println("Transfer Amount Successfuly..");
			return true;

		}

	}

}
