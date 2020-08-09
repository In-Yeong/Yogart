package com.ssafy.yogart.aicoach.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ai_course")
public class Aicoach {
	@Id
	@Column(name="ai_course_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="질문아이디")
	private Integer aiCourseId;
	
	@Column(name="ai_course_user_nickname")
    @ApiModelProperty(value="닉네임")
    private String aiCourseUserNickname;
}
