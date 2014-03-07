package org.hmahout.movie.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hmahout.dao.Dao;
import org.hmahout.dao.DaoException;
import org.hmahout.db.DBManager;
import org.hmahout.movie.entity.Movie;
import org.hmahout.movie.entity.Moviepreference;

public class ImportMoviePreference {
	
	public static void importMovieDb() throws IOException, DaoException{
		FileReader fileReader=new FileReader("data/movie/ratings.dat");
		//可以换成工程目录下的其他文本文件
		BufferedReader br=new BufferedReader(fileReader);
		Moviepreference moviepreference = null;
		Dao dao = new Dao();
		int i=0;
		String line = null;
		while((line = br.readLine())!=null){
			i++;
			if(line==null){
				continue;
			}
			//1::1193::5::978300760
			String[] sort = line.split("::");
			if(sort.length==4){
				moviepreference = new Moviepreference();
				moviepreference.setUserId(Integer.parseInt(sort[0]));
				moviepreference.setMovieId(Integer.parseInt(sort[1]));
				moviepreference.setPreference(Integer.parseInt(sort[2]));
				moviepreference.setTime(Integer.parseInt(sort[3]));
				dao.insert(moviepreference);
			}else{
				System.err.println(line);
			}
		}
		System.err.println(i);
		DBManager.closeConnection();
		br.close();
	}
	
	public static void main(String[] args) throws IOException, DaoException {
		importMovieDb();
	}
}
