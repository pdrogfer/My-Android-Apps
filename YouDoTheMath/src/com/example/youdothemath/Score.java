package com.example.youdothemath;

// This is to classify all scores. Comparable allows to compare instances of this same class
// with one another
public class Score implements Comparable<Score> {

	// variables
	private String scoreDate;
	public int scoreNum;

	// constructors
	public Score(String date, int num) {
		scoreDate = date;
		scoreNum = num;
	}

	// methods
	@Override
	public int compareTo(Score sc) {
		// method to compare instances of Score
		// return 0 if equal, 1 if passed is greater than this, -1 on the
		// contrary
		return sc.scoreNum > scoreNum? 1 : sc.scoreNum < scoreNum? -1 : 0;
	}
	
	public String getScoreText() {
		return scoreDate + " - " + scoreNum;
	}

}
