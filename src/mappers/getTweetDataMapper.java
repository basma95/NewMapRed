package mappers;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import dao.*;

public class getTweetDataMapper extends MapReduceBase implements Mapper<Text, Text, Text,Text>{
	public void map(Text key, Text value, OutputCollector<Text, Text> output,
            Reporter reporter){
		try{
			AllConnects allConnects = new AllConnects();
			ArrayList<String> result = allConnects.sendCommand(value.toString());
			if((result != null) && (result.size() != 0)){
				for(int i =0;i<result.size();i++){
					output.collect(key,new Text("{"+result.get(i)+"}"));
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}