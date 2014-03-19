package org.hmahout.kpi.app;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.hmahout.kpi.entity.Kpi;
import org.hmahout.kpi.util.KpiUtil;

import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

public class KpiBrowserSimple {

	public static class KpiBrowserSimpleMapper extends MapReduceBase 
		implements Mapper<Object, Text, Text, IntWritable> {
		
		@Override
		public void map(Object key, Text value,
				OutputCollector<Text, IntWritable> out, Reporter reporter)
				throws IOException {
			Kpi kpi = KpiUtil.transformLineKpi(value.toString());
			UASparser parser = new UASparser();
			if(kpi!=null && kpi.getHttP_user_agent_info()!=null){
				UserAgentInfo info = 
				parser.parseBrowserOnly(kpi.getHttP_user_agent_info());
				out.collect(new Text(info.getUaName()), new IntWritable(1));
			}
		}
	}
	
	public static class KpiBrowserSimpleReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		public void reduce(Text key, Iterator<IntWritable> value,
				OutputCollector<Text, IntWritable> out, Reporter reporter)
				throws IOException {
			IntWritable sum = new IntWritable(0);
			while(value.hasNext()){
				sum.set(sum.get()+value.next().get());
			}
			out.collect(key, sum);
		}
	}
	public static void main(String[] args) throws IOException {
		String input = "hdfs://127.0.0.1:9000/user/tianbx/log_kpi/input";
        String output ="hdfs://127.0.0.1:9000/user/tianbx/log_kpi/browerSimple";
        JobConf conf = new JobConf(KpiBrowserSimple.class);
        conf.setJobName("KpiBrowserSimple");
        String url = "classpath:";
        conf.addResource(url+"/hadoop/core-site.xml");
        conf.addResource(url+"/hadoop/hdfs-site.xml");
        conf.addResource(url+"/hadoop/mapred-site.xml");
        
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(IntWritable.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
        conf.setMapperClass(KpiBrowserSimpleMapper.class);
        conf.setCombinerClass(KpiBrowserSimpleReducer.class);
        conf.setReducerClass(KpiBrowserSimpleReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        JobClient.runJob(conf);
        System.exit(0);
	}

}
