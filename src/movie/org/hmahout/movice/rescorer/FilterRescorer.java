package org.hmahout.movice.rescorer;

import java.util.Set;

import org.apache.mahout.cf.taste.recommender.IDRescorer;

public class FilterRescorer implements IDRescorer {

	final private Set<Long> movie;// 女性或者男性看过的电影

	public FilterRescorer(Set<Long> movie) {
		this.movie = movie;
	}

	@Override
	public double rescore(long id, double originalScore) {
		return isFiltered(id) ? originalScore : Double.NaN;
	}

	@Override
	public boolean isFiltered(long id) {
		//判断这个电影是否包含在上面movie电影集合中
		return movie.contains(id);
	}

}
