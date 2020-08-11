package com.ssafy.yogart.mypage.model;

import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class GraphResult {
	private Map<Integer,Integer> timeCount;
	private Map<LocalDate, Integer> attendance;
	private GraphBodyPart tags;
	public GraphResult() {
		for(int i = 6; i < 30; i++) {
			timeCount.put(i, 0);
		}
	}
	
}
