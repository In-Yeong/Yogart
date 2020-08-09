package com.ssafy.yogart.aicoach.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ai_course_detail")
public class AicoachCourse {
	@Id
	@Column(name="ai_course_detail_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="아이디")
	private Integer aiCourseDetailId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name="ai_course_detail_no", referencedColumnName="ai_course_id")
    @ApiModelProperty(value="유저번호")
    private Aicoach aiCourseDetailNo;
	
	@Column(name="ai_course_detail_name")
	@ApiModelProperty(value="코스이름")
	private String aiCourseDetailName;
	
	@Column(name="c1")
	@ApiModelProperty(value="코스1")
	private Integer c1;
	
	@Column(name="c2")
	@ApiModelProperty(value="코스2")
	private Integer c2;
	
	@Column(name="c3")
	@ApiModelProperty(value="코스3")
	private Integer c3;
	
	@Column(name="c4")
	@ApiModelProperty(value="코스4")
	private Integer c4;
	
	@Column(name="c5")
	@ApiModelProperty(value="코스5")
	private Integer c5;
	
	@Column(name="c6")
	@ApiModelProperty(value="코스6")
	private Integer c6;
	
	@Column(name="c7")
	@ApiModelProperty(value="코스7")
	private Integer c7;
	
}
