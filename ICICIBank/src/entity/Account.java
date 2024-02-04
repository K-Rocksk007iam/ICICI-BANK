package entity;

public class Account {
	private int accNo;
	private String accHolderName;
	private double accBal;

	public Account() {
		accNo = 0;
		accHolderName = " ";
		accBal = 0;
	}

	public Account(int accNo, String accHolderName, double accBal) {
		this.accNo = accNo;
		this.accHolderName = accHolderName;
		this.accBal = accBal;
	}
	public void accept()
	{
		
		
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public double getAccBal() {
		return accBal;
	}

	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}

	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", accHolderName=" + accHolderName + ", accBal=" + accBal + "]";
	}

}
