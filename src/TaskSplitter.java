import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.TextInputFormat;
import mappers.TopsyMapper;
import mappers.getTweetDataMapper;
import reducers.ValueSepReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class TaskSplitter extends Configured implements Tool {
	public int run(String[] args) throws Exception {
		/*if (args.length < 2) {
			System.out.println("jar_name class_name <inDir> <outDir> expected");
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}*/
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]+" \n");
		}
		Path tempDir =null;
		
		JobConf tweetJob = new JobConf(getConf(),TaskSplitter.class);
		try{
		tweetJob.setJobName("tweet-collect");
		FileInputFormat.setInputPaths(tweetJob, args[0]);
		tweetJob.setInputFormat(TextInputFormat.class);
		tweetJob.setMapperClass(TopsyMapper.class);
		tempDir = new Path("/home/ubuntu/temp/temp_"+Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
		//FileOutputFormat.setOutputPath(tweetJob, new Path(args[1]));
		FileOutputFormat.setOutputPath(tweetJob,tempDir );
		tweetJob.setOutputKeyClass(Text.class);
		tweetJob.setOutputValueClass(Text.class);
		JobClient.runJob(tweetJob);
		//second job for getting the actual tweet lines
		JobConf getTweet = new JobConf(getConf(),TaskSplitter.class);
		getTweet.setJobName("collect-tweets");
		FileInputFormat.setInputPaths(getTweet,tempDir);
		getTweet.setInputFormat(KeyValueTextInputFormat.class);
		getTweet.setMapperClass(getTweetDataMapper.class);
		getTweet.setReducerClass(ValueSepReducer.class);
		FileOutputFormat.setOutputPath(getTweet, new Path(args[1]));
		getTweet.setOutputKeyClass(Text.class);
		getTweet.setOutputValueClass(Text.class);
		JobClient.runJob(getTweet);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			FileSystem.get(tweetJob).delete(tempDir, true);
		}
		return 0;
	}
	public static void main(String args[]){
		try{
		int res = ToolRunner.run(new Configuration(), new TaskSplitter(), args);
		if(res == 0){
			System.out.println("reached mapreduce out!!!!!");
			getConnection(args[3]);
			}else{
				System.out.println("have to exit******************");
			System.exit(res);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean getConnection(String outputFolder) throws Exception {
		String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
		String driverMySQL = "com.mysql.jdbc.Driver";
		Class.forName(driverName);
		Connection con = DriverManager
				.getConnection(
						"jdbc:hive://ec2-50-16-57-196.compute-1.amazonaws.com:10000/default",
						"", "");
		Statement stmt = con.createStatement();
		boolean status = false;
		String filePath = outputFolder + "/part-00000";
		/*String query = "CREATE EXTERNAL TABLE IF NOT EXISTS tweets_topsy (hits INT, firstpost_date STRING, title STRING, "+
		"url STRING, topsy_author_img STRING, trackback_author_url STRING, url_expansions ARRAY<STRUCT <url:STRING,"+
		" expanded_url:STRING, display_url:STRING >>, trackback_author_name  STRING, content STRING, mytype STRING, "+
		"score DOUBLE, trackback_author_nick STRING, highlight STRING, trackback_total STRING, trackback_permalink STRING,"+
		"topsy_author_url STRING, topsy_trackback_url STRING) ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe' LOCATION '"+
		filePath+"';";*/
		System.out.println("before add jar statement");
		String query1 = "add jar /home/ubuntu/hive-serdes-1.0-SNAPSHOT.jar";
		Boolean abc=stmt.execute(query1);
		System.out.println("after jar add!!!!!!!!!!!!! "+abc);
		String query = "LOAD DATA INPATH '"+filePath+"' overwrite into table tweets_topsy";
		status = stmt.execute(query);
		
		System.out.println("loading table "+status);
		return status;
	}

}
