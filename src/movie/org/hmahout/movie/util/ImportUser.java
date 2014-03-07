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
import org.hmahout.movie.entity.User;

public class ImportUser {
	
	public static void importMovieDb() throws IOException, DaoException{
		FileReader fileReader=new FileReader("data/movie/users.dat");
		//可以换成工程目录下的其他文本文件
		BufferedReader br=new BufferedReader(fileReader);
		User user = null;
		Dao dao = new Dao();
		int i=0;
		String line = null;
		while((line = br.readLine())!=null){
			i++;
			if(line==null){
				continue;
			}
			String[] sort = line.split("::");
			if(sort.length==5){
				user = new User();
				user.setId(Integer.parseInt(sort[0]));
				user.setGender(sort[1]);
				user.setAge(Integer.parseInt(sort[2]));
				user.setJob(sort[3]);
				user.setZipcode(sort[4]);
				dao.insert(user);
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
