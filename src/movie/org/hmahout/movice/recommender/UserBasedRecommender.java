package org.hmahout.movice.recommender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.hmahout.dao.Dao;
import org.hmahout.dao.DaoException;
import org.hmahout.db.DBManager;
import org.hmahout.movice.rescorer.FilterRescorer;
import org.hmahout.movie.entity.User;

public class UserBasedRecommender {
	public List<RecommendedItem> userBasedRecommender(long userID,int size) {
		// step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
		List<RecommendedItem> recommendations = null;
		try {
			JDBCDataModel dataModel = null;
			try {
				dataModel = new MySQLJDBCDataModel(DBManager.getDataSource(),"movie_preference", "userId",
						"movieId","preference" , null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			DataModel model = dataModel;//构造数据模型，Database-based
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//用PearsonCorrelation 算法计算用户相似度
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
			Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, similarity));//构造推荐引擎，采用 CachingRecommender 为 RecommendationItem 进行缓存
			recommendations = recommender.recommend(userID, size);//得到推荐的结果，size是推荐接过的数目
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recommendations;
	}
	
	public List<RecommendedItem> userBasedFileRecommender(Long userID,int size) {
		// step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
		List<RecommendedItem> recommendations = null;
		try {
			JDBCDataModel dataModel = null;
			try {
				dataModel = new MySQLJDBCDataModel(DBManager.getDataSource(),"movie_preference", "userId",
						"movieId","preference" , null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
			//用PearsonCorrelation 算法计算用户相似度
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, dataModel);
			//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
			Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(
					dataModel, neighborhood, similarity));
			//构造推荐引擎，采用 CachingRecommender 为 RecommendationItem 进行缓存
			
			Set<Long> movie = getMovieByGender(userID.intValue(), dataModel);
			FilterRescorer filterRescorer = new FilterRescorer(movie);
			recommendations = recommender.recommend(userID, size,filterRescorer);
			//得到推荐的结果，size是推荐接过的数目
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recommendations;
	}
	
	public Set<Long> getMovieByGender(Integer userid,DataModel model) throws DaoException, TasteException{
		Set<Long> movie = new HashSet<Long>();
		Dao dao = new Dao();
		User user = dao.getById(User.class, userid);
		
		List<User> userList = dao.query(User.class, "user") ;
		for(User user2 :userList){
			if(user2.getGender().equals(user.getGender())){
				FastIDSet itemList = model.getItemIDsFromUser(user2.getId());
				LongPrimitiveIterator longPrimitiveIterator = itemList.iterator();
				while(longPrimitiveIterator.hasNext()){
					movie.add(longPrimitiveIterator.next());
				}
			}
		}
		return movie;
	}

	public static void main(String args[]) throws Exception {
		UserBasedRecommender basedRecommender = new UserBasedRecommender();
//		List<RecommendedItem> recommendations = basedRecommender.userBasedRecommender(1, 3);
//		if(recommendations!=null){
//			for(RecommendedItem item :recommendations){
//				System.err.println(item.getItemID());
//			}
//		}
//		2763
//		2571
//		110
		List<RecommendedItem> recommendations = basedRecommender.userBasedFileRecommender(Long.valueOf(1), 3);
		if(recommendations!=null){
			for(RecommendedItem item :recommendations){
				System.err.println(item.getItemID());
			}
		}
	
	}
}