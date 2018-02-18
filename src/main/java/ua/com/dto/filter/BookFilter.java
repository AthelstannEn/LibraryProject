package ua.com.dto.filter;

import java.util.ArrayList;
import java.util.List;

import ua.com.entity.Author;

public class BookFilter {

	private String search = "";

	private String min = "";

	private String max = "";

	private int minValue;

	private int maxValue;

	private List<Author> authorId = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public List<Author> getAuthorId() {
		return authorId;
	}

	public void setAuthorId(List<Author> authorId) {
		this.authorId = authorId;
	}

}
