package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class AllConnects {
		
	public ArrayList<String> sendCommand(String urlStr) {
		try{
		URL url = new URL(urlStr);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
		return(parseResult(in.readLine()));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	private ArrayList<String> parseResult(String result){
		int index=0;
		boolean enter = true;
		ArrayList num = new ArrayList();
		ArrayList<String> tweets = new ArrayList<String>();
		ArrayList<String> tweetsFinal = new ArrayList<String>();
		ArrayList<String> tweetsLat = new ArrayList<String>();
		System.out.println("reach 1");
		if(result.contains("\"response\":")){
			while(index != -1){
				if(enter){
					num.add(result.indexOf("\"hits\":"));
					index = (Integer)num.get(num.size()-1);
					System.out.println(index);
					enter = false;
				}else{
					num.add(result.indexOf("\"hits\":",(Integer)num.get(num.size()-1)+1));
					index = (Integer)num.get(num.size()-1);
					System.out.println("arraylist "+index);
				}
				//System.out.println(num[arrayCount]);
			}
			for(int i=0;i<num.size()-2;i++){
				tweets.add(result.substring((Integer)num.get(i), (Integer)num.get(i+1)-4));
			}
			for(int i=0;i<tweets.size();i++){
				String temp = tweets.get(i);
				int lastFieldReqd = temp.indexOf("\"target_birth_date\"");
				temp = temp.substring(0, lastFieldReqd-2);
				tweetsFinal.add(temp);
				
			}
			for(int i=0;i<tweetsFinal.size();i++){
				String temp = tweetsFinal.get(i);
				int field = temp.indexOf("\"firstpost_date\": ");
				String timeStamp = temp.substring(field+18, field+28);
				int ts = Integer.parseInt(timeStamp);
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis((long)ts*1000);
				SimpleDateFormat dtFmt= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dtFmt.format(gc.getTime());
				temp=temp.replace(timeStamp, "\""+dtFmt.format(gc.getTime()).toString()+"\"");
				tweetsLat.add(temp);
			}
		}
		
	return tweetsLat;
	}

}
