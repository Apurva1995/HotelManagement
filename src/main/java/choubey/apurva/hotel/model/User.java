package choubey.apurva.hotel.model;


public class User {
	
	private String userName;
	private String password;
	private String email;
	private long mobileNumber;
	private short isAdmin;
	private String sex;
	private double age;
	private String aadharNumber;
	
	public User() {
		super();
	}

	public User(String userName, String password, String email, long mobileNumber, short isAdmin, String sex,
			double age, String aadharNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.isAdmin = isAdmin;
		this.sex = sex;
		this.age = age;
		this.aadharNumber = aadharNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(short isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
}
