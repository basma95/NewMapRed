package mappers;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper.Context;
import topsy.*;

public class TopsyMapper<K> extends MapReduceBase implements Mapper<Object, Text, Text, Text>{
		//Mapper<Object, Text, Text, IntWritable> {

	public void map(Object key, Text value, OutputCollector<Text, Text> output,
            Reporter reporter){
		int startTime=1366065000;
		String tweetURL = "";
		String strToken = "";
		//while (itr.hasMoreTokens()) {
			//while(startTime < 1366141800){
			while(startTime < 1366066800){
				for(int i=0;i<=10;i++){
					//strToken = itr.nextToken();
					tweetURL = new TopsyTest().test(value.toString(),i,startTime);
					try{
					output.collect(new Text(value.toString()),new Text(tweetURL));
					}catch(Exception e){
						e.printStackTrace();
						System.exit(0);
					}
				}
				startTime = startTime + 600;
			}
			
		//}
	}

}
