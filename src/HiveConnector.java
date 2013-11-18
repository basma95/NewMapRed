import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HiveConnector {
	private String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private String driverMySQL = "com.mysql.jdbc.Driver";
	public static void main(String args[]){
		try{
		HiveConnector hv=new HiveConnector();
		hv.getConnection("/home/ubuntu/output13");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean getConnection(String outputFolder) throws Exception {
		Class.forName(driverName);
		Connection con = DriverManager
				.getConnection("jdbc:hive://ec2-50-16-57-196.compute-1.amazonaws.com:10000/default");
		Statement stmt = con.createStatement();
		boolean status = false;
		String filePath = outputFolder + "/part-00000";
		/*String query = "CREATE EXTERNAL TABLE IF NOT EXISTS tweets_topsy (hits INT, firstpost_date STRING, title STRING, "+
		"url STRING, topsy_author_img STRING, trackback_author_url STRING, url_expansions ARRAY<STRUCT <url:STRING,"+
		" expanded_url:STRING, display_url:STRING >>, trackback_author_name  STRING, content STRING, mytype STRING, "+
		"score DOUBLE, trackback_author_nick STRING, highlight STRING, trackback_total STRING, trackback_permalink STRING,"+
		"topsy_author_url STRING, topsy_trackback_url STRING) ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe' LOCATION '"+
		filePath+"';";*/
		String query1 = "add jar /home/ubuntu/hive-serdes-1.0-SNAPSHOT.jar";
		stmt.execute(query1);
		String query = "LOAD DATA INPATH '"+filePath+"' overwrite into table tweets_topsy";
		status = stmt.execute(query);
		System.out.println(status);
		return status;
	}
}
