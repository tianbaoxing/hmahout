package org.hmahout.movie.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hmahout.dao.Dao;
import org.hmahout.dao.DaoException;
import org.hmahout.db.DBManager;
import org.hmahout.movie.entity.Movie;

public class ImportMovie {
	
	public static void importMovieDb() throws IOException, DaoException{
		FileReader fileReader=new FileReader("data/movie/movies.dat");
		//可以换成工程目录下的其他文本文件
		BufferedReader br=new BufferedReader(fileReader);
		Movie movie = null;
		Dao dao = new Dao();
		int i=0;
		String line = null;
		while((line = br.readLine())!=null){
			i++;
			if(line==null){
				continue;
			}
			String[] sort = line.split("::");
			if(sort.length==3){
				movie = new Movie();
				String name_ = sort[1];
				int index = name_.indexOf("(");
				if(index<0){
					System.err.println(line);
					continue;
				}
				movie.setId(Integer.parseInt(sort[0]));
				movie.setName(name_.substring(0, name_.length()-6));
				movie.setPublish_year(name_.substring(name_.length()-5,name_.length()-1));
//				if(movie.getPublish_year()!=null && movie.getPublish_year().length()!=4){
//					System.err.println(line);
//					continue;
//				}
				movie.setType(sort[2]);
				dao.insert(movie);
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
