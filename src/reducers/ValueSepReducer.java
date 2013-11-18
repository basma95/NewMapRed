package reducers;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ValueSepReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, NullWritable> {

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, NullWritable> output, Reporter reporter)
			throws IOException {
		while(values.hasNext()){
			System.out.println("inside reducer");
			output.collect(values.next(), NullWritable.get());
		}
	}
}
