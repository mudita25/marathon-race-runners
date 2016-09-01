package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mudita
 *
 * This class reads the runners' information from derby database.
 */
public class RunnerDB implements MarathonDAO {
	
	private ArrayList<Runner> runners = null;

	/**
	 * Connects to Derby database
	 */
	private Connection connect(){
		Connection connection = null;
		try {
			System.out.println("Connecting to database...");
			String dbDirectory = "Resources";
			System.setProperty("derby.system.home", dbDirectory);
			
			String url = "jdbc:derby:FinalDB";
            String user = "";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e)
        {
            System.err.println("Error loading database driver: " + e);
            System.exit(0);
        }
        return connection;
	}

	/**
	 * Provides the list of Runners from database
	 */
	public ArrayList<Runner> getRunners(){
		try {
			Connection connection = connect();
			
			runners = new ArrayList<>();
			String query = "SELECT Name, RunnersSpeed, RestPercentage "
							+ "FROM Runners";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("Name");
                int speed = rs.getInt("RunnersSpeed");
                int restPercent = rs.getInt("RestPercentage");
                
                Runner r = new Runner(name, speed, restPercent);
                runners.add(r);
			}
			rs.close();
            ps.close();
            connection.close();          
		}
		catch(SQLException sqle){
			System.out.println("Error while getting runners from database");
		}
		return runners;
	}
}
