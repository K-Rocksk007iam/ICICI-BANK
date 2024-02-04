package dao;

import java.sql.*;
import java.util.ArrayList;

import entity.Account;

public class AccountDao {

	public void insertRecord(Connection con, Account account) throws SQLException {

		String str = "insert into account values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, account.getAccNo());
			ps.setString(2, account.getAccHolderName());
			ps.setDouble(3, account.getAccBal());

			int i = ps.executeUpdate();
			if (i > 0) {

				System.out.println("record are inserted");
			} else {
				System.out.println("reacord are not inserted");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRecord(Connection con, int accNO) throws SQLException {

		String str = "delete from account where accNo=? ";
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, accNO);
			int i = ps.executeUpdate();
			if (i > 0) {

				System.out.println("record are deleted");
			} else {
				System.out.println("reacord are not deleted");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRecord(Connection con, int incr, String accHolderName) throws SQLException {

		String str = "update account set accBal=accBal+? where accHolderName=?";
		try {

			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, incr);
			ps.setString(2, accHolderName);

			int i = ps.executeUpdate();
			if (i >= 0) {

				System.out.println("record updated");
			} else {
				System.out.println("not updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDeposit(Connection con, Account account) throws SQLException {
		double accBal = account.getAccBal();
		int accNo = account.getAccNo();
		String str = "update account set accBal=? where accNo=?";
		try {

			PreparedStatement ps = con.prepareStatement(str);
			ps.setDouble(1, accBal);
			ps.setInt(2, accNo);

			int i = ps.executeUpdate();
			if (i >= 0) {

				System.out.println("record updated");
			} else {
				System.out.println("not updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Account> allRecordsArrayList(Connection con) throws SQLException {
		ArrayList<Account> arrayAccount = new ArrayList<Account>();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("select * from account");

		while (rs.next()) {
			int accNo = rs.getInt(1);
			String accHolderName = rs.getString(2);
			double accBal = rs.getDouble(3);

			Account account = new Account(accNo, accHolderName, accBal);
			arrayAccount.add(account);

			System.out.println("---------------------------------------------------");
		}
		rs.close();
		stmt.close();
		return arrayAccount;
	}

	public void allRecords(Connection con, Account account) throws SQLException {
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("select * from account");

		while (rs.next()) { // column

			// System.out.println("---------------------------------------------------");
			System.out.println("accno is " + rs.getInt("accNo"));
			System.out.println("name is " + rs.getString("accHolderName"));
			System.out.println("balance is " + rs.getInt("accBal"));
			System.out.println("---------------------------------------------------");
		}
		rs.close();
		stmt.close();
	}
}
