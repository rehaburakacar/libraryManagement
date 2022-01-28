import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class MicrosoftSQLConnection {
    private static final String ID = null;
	// Connect to your database.
	Connection connection;
	String userType;
	String Username;
	public String GetUserType()
	{
		return this.userType;
		
	}
	public String GetUsername()
	{
		return this.Username;
		
	}
	
	
	
	public ArrayList<String> GetUserTypes()throws SQLException
	{
		String SQL="SELECT * FROM USER_TYPE";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new ArrayList<String>(); 
		while ( RESULT.next()) {
			String DESCRIPTION = RESULT.getString("DESCRIPTION").toString();
			a.add(DESCRIPTION);	
        }
		System.out.println(a);
        return a;

	}
	public clsResult getAvailability() throws SQLException{
		String SQL="SELECT * FROM BOOK_TRACK_NUMBERS WHERE BOOK=1 AND ID NOT IN (select BOOK_TRACK_NUMBER from RESERVATIONS)";
		Statement cmd = this.connection.createStatement();
		ResultSet RESULT = cmd.executeQuery(SQL);
		if (RESULT == null) {
			return clsResult(false);
		}
		else { 
			return clsResult(true);
		}
	}
	
	private clsResult clsResult(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean deleteReservation(int userID, String bookID)throws SQLException {
		String SQL="DELETE FROM RESERVATIONS WHERE BOOK_TRACK_NUMBER = " + bookID + " AND LIBRARY_USER = " + userID ; 
		Statement cmd=this.connection.createStatement();
		
		try {
			int aaa = cmd.executeUpdate(SQL);
			return true;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	
	
	public boolean addTrackNumber(String bookID, String trackNumber)throws SQLException {
		String SQL="INSERT INTO BOOK_TRACK_NUMBERS(BOOK, TRACK_NUMBER)"+
				"VALUES('" + bookID + "', '" + trackNumber + "')";; 
		Statement cmd=this.connection.createStatement();
		
		try {
			int aaa = cmd.executeUpdate(SQL);
			return true;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean addBook(String name, String author, String date)throws SQLException{
		String SQL="INSERT INTO BOOKS(NAME, AUTHOR, PUBLISH_DATE)"+
				"VALUES('" + name + "', '" + author + "', '" + date + "')";; 
		Statement cmd=this.connection.createStatement();
		
		try {
			int aaa = cmd.executeUpdate(SQL);
			return true;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public String getReservations()throws SQLException {
		String SQL="select U.USERNAME,O.NAME,B.TRACK_NUMBER,R.EXPIRE_DATE\r\n" + 
				"from \r\n" + 
				"	RESERVATIONS R,BOOK_TRACK_NUMBERS B,BOOKS O,USERS U\r\n" + 
				"where R.BOOK_TRACK_NUMBER=B.ID and B.BOOK=O.ID and R.LIBRARY_USER=U.ID\r\n" + 
				"order by R.EXPIRE_DATE";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		String a  = "";
		while ( RESULT.next()) {
			String USERNAME = RESULT.getString("USERNAME").toString();
			String NAME = RESULT.getString("NAME").toString();
			String TRACK_NUMBER = RESULT.getString("TRACK_NUMBER").toString(); 
			String DATE = RESULT.getString("EXPIRE_DATE".toString());
			a += "Username: " + USERNAME + " Book Name: " + NAME + " Track Number: " + TRACK_NUMBER + " Expire Date: " + DATE  + "\r\n";	
		}
		return a;
	}
	
	public ArrayList<String> getReservedBooks(int ID)throws SQLException {
		String SQL="SELECT BOOK_TRACK_NUMBER FROM RESERVATIONS WHERE LIBRARY_USER = " + ID;
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new  ArrayList<String>(); 
		while ( RESULT.next()) {
			String TRACK_NUMBER = RESULT.getString("BOOK_TRACK_NUMBER").toString();
			a.add(TRACK_NUMBER);	
	    }
		Collections.sort(a);
		for(String temp: a) {
			System.out.println(temp);
		}
		return a;
		
	}
	
	public String getID(String search, String date)throws SQLException {
		String SQL="SELECT ID from BOOKS where NAME = '" + search + "' AND PUBLISH_DATE = '" + date + "'";
		Statement cmd=this.connection.createStatement();
		
		System.out.println("Name: " + search + " Date: " + date);
		ResultSet  RESULT = cmd.executeQuery(SQL);
		String ID = "";
		while ( RESULT.next()) {
			ID = RESULT.getString("ID").toString();	
	    }
		System.out.print("ID: " + ID);
		return ID;
	}
	public String getPublishDate(String search)throws SQLException {
		String SQL="SELECT PUBLISH_DATE, NAME from BOOKS where NAME like '%" + search + "%' ORDER BY NAME";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		String DATE = RESULT.getString("PUBLISH_DATE").toString();
		return DATE;
	}
	
	public ArrayList<String> getIDandName(int ID)throws SQLException {
		String SQL="SELECT \r\n" + 
				"B.TRACK_NUMBER,\r\n" + 
				"BB.NAME\r\n" + 
				"\r\n" + 
				"  FROM \r\n" + 
				"  RESERVATIONS R,\r\n" + 
				"  BOOK_TRACK_NUMBERS B,\r\n" + 
				"  BOOKS BB\r\n" + 
				"  where R.BOOK_TRACK_NUMBER=B.ID and B.BOOK=BB.ID and R.LIBRARY_USER = " + ID + "";
		Statement cmd=this.connection.createStatement();
		String ID1 = "";
		String NAME = "";
		ArrayList<String> a=new  ArrayList<String>(); 
		ResultSet  RESULT = cmd.executeQuery(SQL);
		while ( RESULT.next()) {
			ID1 = RESULT.getString("TRACK_NUMBER").toString();
			NAME = RESULT.getString("NAME").toString();
			a.add(ID1 + "   , Name: " + NAME);
	    }
		return a;
	}
	
	public boolean takeBook(String bookTrackNumber, int day, int userID)throws SQLException {
		String SQL="INSERT INTO RESERVATIONS(BOOK_TRACK_NUMBER,EXPIRE_DATE,LIBRARY_USER)\r\n" + 
				"VALUES(" + bookTrackNumber + ",dateadd(day," + day + ",GETDATE())," + userID + ")"; 
		Statement cmd=this.connection.createStatement();
		
		try {
			int aaa = cmd.executeUpdate(SQL);
			return true;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public String getIDFromTrackNum(String trackNum)throws SQLException {
		String SQL="SELECT * from BOOK_TRACK_NUMBERS where TRACK_NUMBER = " + trackNum + "";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		String ID = "";
		while ( RESULT.next()) {
			ID = RESULT.getString("ID").toString();
	    }
		
		return ID;
		
	}
	
	public ArrayList<String> getNotReserved(String ID)throws SQLException
	{
		String SQL="select A.*, B.NAME from BOOK_TRACK_NUMBERS as A, BOOKS B\r\n" + 
				"				where \r\n" + 
				"				A.BOOK=B.ID AND\r\n" + 
				"				A.ID NOT IN (select BOOK_TRACK_NUMBER from RESERVATIONS) AND\r\n" + 
				"				B.ID= '" + ID + "'"; 
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new  ArrayList<String>(); 
		while ( RESULT.next()) {
			String TRACK_NUMBER = RESULT.getString("TRACK_NUMBER").toString();
			a.add(TRACK_NUMBER);	
	    }
		Collections.sort(a);
		for(String temp: a) {
			System.out.println(temp);
		}
		return a;
		
		
	}
	
	public int deleteTrackNumber(String bookNumber, String trackNumber)throws SQLException {
		String SQL="DELETE from BOOK_TRACK_NUMBERS WHERE BOOK = '" + bookNumber + "' AND TRACK_NUMBER = '" + trackNumber + "'";
		Statement cmd=this.connection.createStatement();
		int aa = 0;
		try {
			int aaa = cmd.executeUpdate(SQL);
			aa = aaa;
			return aa;
		}catch(SQLException e) {
			e.printStackTrace();
			clsGeneral.infoBox("An error occured!");
			return aa;
		}
	}
	
	public ArrayList<String> getBooks(String search)throws SQLException
	{
		String SQL="SELECT ID, PUBLISH_DATE, NAME from BOOKS where NAME like '%" + search + "%' ORDER BY NAME";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new  ArrayList<String>(); 
		while ( RESULT.next()) {
			String ID = RESULT.getString("ID").toString();
			String DESCRIPTION = RESULT.getString("NAME").toString();
			String PUBLISH_DATE = RESULT.getString("PUBLISH_DATE").toString();
			a.add(ID + "      , " + DESCRIPTION + ", Publish Date: " + PUBLISH_DATE);	
	    }
		Collections.sort(a);
		for(String temp: a) {
			System.out.println(temp);
		}
		return a;
		
		
	}
	
	
	
	public boolean Login(String Username,String Password) throws SQLException
	{
        System.out.println("Þifre:"+Password);
		
		String SQL="SELECT       dbo.USERS.ID,dbo.USERS.USERNAME, dbo.USERS.PASSWORD, dbo.USER_TYPE.DESCRIPTION as USER_TYPE_DESCRIPTION\r\n" + 
				"FROM            dbo.USERS INNER JOIN\r\n" + 
				"                         dbo.USER_TYPE ON dbo.USERS.USER_TYPE = dbo.USER_TYPE.ID\r\n" + 
				"WHERE        (dbo.USERS.PASSWORD = '" + Password + "') AND (dbo.USERS.USERNAME = '" + Username + "')";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		if(!RESULT.next())
		{					
			return false;
		}
		
		
//		RESULT.first();
		 
		Integer userID=0;
		userID=RESULT.getInt("ID");
		this.Username = RESULT.getString("USERNAME").toString();
        //String password = RESULT.getString("PASSWORD").toString();
        this.userType = RESULT.getString("USER_TYPE_DESCRIPTION").toString();            
        //System.out.println(Username+" "+Password+" "+userType);
		
		SQL="INSERT INTO USER_LOGIN_LOGS(USER_ID,LOGIN_DATE,LOGIN_HOSTNAME) VALUES("+userID.toString()+",GETDATE(),HOST_NAME())";
        System.out.println(SQL);
        cmd.executeUpdate(SQL);
        System.out.println("After user login logs");
		return true;
	}
	
	
	
	
    public void openConnection(){
        String connectionUrl =
                "jdbc:sqlserver://127.0.0.1:1433;"
                        + "database=LibraryDatabase;"
                        + "user=user1;"
                        + "password=1;"
                        + "loginTimeout=30;";

       
        try
        {
            this.connection = DriverManager.getConnection(connectionUrl); 
            System.out.println("Connection OK");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    



public clsResult register(String userName, String password, int userType)throws SQLException {
	String SQL = "INSERT INTO USERS(USERNAME, PASSWORD, USER_TYPE)" +
			"VALUES('" + userName + "', '" + password + "', '" + userType + "')";
	Statement cmd = this.connection.createStatement();
	try {
		int aaa = cmd.executeUpdate(SQL);
		return new clsResult(true,"SUCCESFULL!");
	}catch(SQLException e) {
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
	
}


public int getUserID(String userName)throws SQLException {
	String SQL = "SELECT ID FROM USERS WHERE USERNAME = '" + userName + "'"; 
	Statement cmd = this.connection.createStatement();
	
	ResultSet RESULT = cmd.executeQuery(SQL);
	if (!RESULT.next()) {
		System.out.println("There is no such user!");
		return 0;
	}

	try {
		int a = RESULT.getInt("ID");
		return a;
	}catch (SQLException e){
		e.printStackTrace();
		return 0;
	}
}



public void closeConnection() {
    try
    {
        this.connection.close(); 
        System.out.println("Connection closed!");
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
}



public clsResult UpdateUsertype(String Username, int Usertype)throws SQLException {	
	Statement cmd = this.connection.createStatement();
	String SQL = "UPDATE USERS SET USER_TYPE ='" + Usertype + "' WHERE USERNAME ='"+Username+"'";
	try {
		int aaa = cmd.executeUpdate(SQL);
		if(aaa==0)
		{
			return new clsResult(false, "NO USER FOUND!");
		}
		return new clsResult(true, "USERTYPE WAS SUCCESFULLY CHANGED!");
	}catch (SQLException e){
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
}
}


