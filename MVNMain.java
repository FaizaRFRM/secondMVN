
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.lang.ClassNotFoundException;
import com.google.gson.Gson;

public class MVNMain {

	public static void main(String[] args) throws Exception {

		try {
			boolean exit = true;
			while (exit) {
				Scanner sc = new Scanner(System.in);
				System.out.println("\t \tChoose One Option:\t \t");
				System.out.println("\t\t 1.read JSON file in concol ");
				System.out.println("\t\t 2.ReadOrderly ");
				System.out.println("\t\t 3. createTable ");
				System.out.println("\t\t 4. insert ");
				System.out.println("\t\t 5. readFromTable ");
				System.out.println("\t\t 6. updateById ");
				System.out.println("\t\t 7. deleteById ");
				System.out.println(" *********************************************** ");
				Scanner scanner = new Scanner(System.in);

				int option = sc.nextInt();
				switch (option) {

				case 1:
					MVNMain.ReadJsonFile();
					break;

				case 2:
					MVNMain.ReadOrderly();
					break;

				case 3:
					MVNMain.createTable();

					break;
				case 4:
				MVNMain.insert();

					break;
				case 5:
				MVNMain.readFromTable();
					break;
				case 6:
				MVNMain.updateById();
					break;
				case 7:
				MVNMain.deleteById();
					break;
				}
			}
			exit = false;
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void ReadJsonFile() throws Exception {

		String jsonUrl = "https://restcountries.com/v3.1/all";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

	}

	public static void ReadOrderly() throws Exception {
		String jsonUrl = "https://restcountries.com/v3.1/all";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		BasicClass[] useGson = gn.fromJson(response.body(), BasicClass[].class);
		for (BasicClass RAC : useGson) {
			System.out.println("cca2" + RAC.getCca2());
			System.out.println("ccn3" + RAC.getCcn3());
			System.out.println("cca3" + RAC.getCca3());
			System.out.println("cioc" + RAC.getCioc());
			System.out.println("status" + RAC.getStatus());
			System.out.println("altSpellings" + RAC.getAltSpellings());
			System.out.println("region" + RAC.getRegion());
			System.out.println("subregion" + RAC.getSubregion());
			System.out.println("languages" + RAC.getLanguages());
			System.out.println("area" + RAC.getArea());
			System.out.println("flag" + RAC.getFlag());
			System.out.println("population" + RAC.getPopulation());
			System.out.println("fifa" + RAC.getFifa());
			System.out.println("timezones" + RAC.getTimezones());
			System.out.println("continents" + RAC.getContinents());
			System.out.println("startOfWeek" + RAC.getStartOfWeek());
		}
	}
	public static void createTable() throws Exception {

		final String url = "jdbc:mysql://localhost:3306/maven2";

		final String user = "root";
		final String pass = "root";
		Connection conn = null;
		try {

			String sql = ("CREATE TABLE FirstTable(" + "id int Primary Key AUTO_INCREMENT," + "cca2 varchar(225)," + "ccn3 Integer," + "cca3 varchar (225)," + "cioc varchar(225),"
					+ "status varchar(225)," + "altSpellings varchar(225),"
					+ "region varchar(225)," + "subregion varchar(225)," + "languages varchar(225)," + "area double,"
					+ "flag varchar(225)," + "population Integer," + "fifa varchar(225)," + "timezones varchar(225),"
					+ "continents varchar(225)," + "startOfWeek varchar(225))");
			
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();

			int m = st.executeUpdate(sql);
			if (m >= 0)
				{System.out.println("Created table in given database...");

				}else {
				System.out.println("failed to create");
				}
			conn.close();
		} catch (Exception ex) {

			System.err.println(ex);
		}
	}

	public static void insert() throws Exception {
		String jsonUrl = "https://restcountries.com/v3.1/all";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		BasicClass[] useGson = gn.fromJson(response.body(), BasicClass[].class);
		for (BasicClass RAC : useGson) {
			final String url = "jdbc:mysql://localhost:3306/Maven2";

			final String user = "root";
			final String pass = "root";
			Connection conn = null;
			try {
				
			
				String sql = "insert into firsttable (cca2,ccn3,cca3,cioc,status,capital,altSpellings,region,subregion,languages"
						+ ",area,flag,population,fifa,timezones,continents,startOfWeek)values ('"
						+ RAC.getCca2() + "','" + RAC.getCcn3() + "','" + RAC.getCca3()+ "','" + RAC.getCioc()+ "','" 
						+ RAC.getStatus() + "','" + RAC.getAltSpellings()[0]+ "','" + RAC.getRegion() 
						+ "','" + RAC.getSubregion()+ "','" + RAC.getLanguages()+ "','" + RAC.getArea()+ "','" + RAC.getFlag()
						+ "','" + RAC.getPopulation()+ "','" + RAC.getFifa()+ "','" + RAC.getTimezones()[0]+ "','" + RAC.getContinents()[0]
								+ "','" + RAC.getStartOfWeek()+"')";

				Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

				DriverManager.registerDriver(driver);

				conn = DriverManager.getConnection(url, user, pass);
				Statement st = conn.createStatement();
		
				int m = st.executeUpdate(sql);
				if (m >= 0) 
					{System.out.println("inserted in given database..." + sql);
					}
				else {
					System.out.println("failed");
				}
				
				conn.close();
			}catch (Exception ex) {

				System.err.println(ex);
			}

		}
	}
	
			public static void readFromTable(){

				final String url = "jdbc:mysql://localhost:3306/Maven2";
				   final String user = "root";
				   final String pass = "root";
				   
				   
				   
				  String QUERY = "SELECT * FROM FirstTable";

				      Connection conn=null;
				      
				 try {
					 conn = DriverManager.getConnection(url, user, pass);
				 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		         Statement stmt = conn.createStatement();
			     DriverManager.registerDriver(driver);
			     ResultSet rs=stmt.executeQuery(QUERY);
					 while(rs.next()) {

						int id=rs.getInt("id");
						String cca2=rs.getString("cca2");
						int ccn3=rs.getInt("ccn3");
						String cca3=rs.getString("cca3");
						String cioc=rs.getString("cioc");
						String status=rs.getString("status");
						String altSpellings=rs.getString("altSpellings");
						String region=rs.getString("region");
						String subregion=rs.getString("subregion");
						String languages=rs.getString("languages");
						double area=rs.getDouble("area");
						String flag=rs.getString("flag");
						int population=rs.getInt("population");
						String fifa=rs.getString("fifa");
						String timezones=rs.getString("timezones");
						String continents=rs.getString("continents");
						String startOfWeek=rs.getString("startOfWeek");
						
						
						
					     System.out.println("id :" + id);
					     System.out.println("cca2 :" +cca2);
					     System.out.println("ccn3" +ccn3);
					     System.out.println("cca3" +cca3);
					     System.out.println("cioc" +cioc);
					     System.out.println("status" +status);
					     System.out.println("altSpellings"+altSpellings);
					     System.out.println("region"+region);
					     System.out.println("subregion"+subregion);
					     System.out.println("languages"+languages);
					     System.out.println("area"+area);
					     System.out.println("flag"+flag);
					     System.out.println("population"+population);
					     System.out.println("fifa"+fifa);
					     System.out.println("timezones"+timezones);
					     System.out.println("continents"+continents);
					     System.out.println("startOfWeek"+startOfWeek);
					     System.out.println("===========================================================");
					   
					 }
					 conn.close() ;
				 }  catch (Exception ex) {
			           
			            System.err.println(ex);
		   }
		    }
			
			public static void updateById(){
				
				final String url = "jdbc:mysql://localhost:3306/Maven2";

				 String user = "root";
				 String pass = "root";
				 Connection conn = null;

				 try {
				 Scanner scanner = new Scanner(System.in);

				  	System.out.println ("input id you want to update");
				      Integer id=scanner.nextInt();
				      String sql = "update firsttable set cioc='GRN' where id="+id;
						 
				      
				 
					 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					   
				     DriverManager.registerDriver(driver);

				     conn = DriverManager.getConnection(url, user,
				             pass);

				     Statement st = conn.createStatement();

				     int m = st.executeUpdate(sql);
				     if (m >=0)
				         {System.out.println("update is successful of " +id);
				         } else {
				         System.out.println("failed");
				     }conn.close() ;
				 }

					  catch (Exception ex) {
			           
			            System.err.println(ex);
		   }
			    }
			
			public static void deleteById() {
				final String url = "jdbc:mysql://localhost:3306/Maven2";

				 String user = "root";
				 String pass = "root";
				 try {
				 Scanner scanner = new Scanner(System.in);

				  	System.out.println ("inter id uou want to delete");
				      Integer id=scanner.nextInt();
				      String sql = "delete from  firsttable where id="+id;
						 
						 Connection conn = null;
				      
				 
					 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					   
				     DriverManager.registerDriver(driver);

				     conn = DriverManager.getConnection(url, user,
				             pass);

				     Statement st = conn.createStatement();

				     int m = st.executeUpdate(sql);
				     if (m >=0)
				         System.out.println("deleted is successful of " +id);
				     else
				         System.out.println("failed");

				     conn.close();
				 }

					  catch (Exception ex) {
			           
			            System.err.println(ex);
		   }
			    }
			

}
