package org.hmahout.movice.web.action;


import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.hmahout.movice.recommender.UserBasedRecommender;


public class RecommenderMovieAction extends Action {

	private static final long serialVersionUID = 9181814190393062618L;

	public String userBase(){
		String userid = request.getParameter("userid");
		String item_num = request.getParameter("item_num");
		UserBasedRecommender basedRecommender = new UserBasedRecommender();
		List<RecommendedItem> list = basedRecommender.userBasedRecommender(Integer.parseInt(userid),
					Integer.parseInt(item_num));
		request.setAttribute("listRecommendedItem", list);
		return "userBase";
	}
}
