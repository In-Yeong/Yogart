package com.ssafy.yogart.mypage.model;

import java.io.Serializable;
import java.time.LocalDate;

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

import com.ssafy.yogart.user.model.User;

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
@Table(name = "pt_clicked")
public class PtClicked implements Serializable {
	@Id
	@Column(name="pt_clicked_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="아이디")
	private Integer ptClickedId;
	
	@Column(name="pt_day")
	@ApiModelProperty(value="일")
	private Integer ptDay;
	
	@Column(name="pt_time")
	@ApiModelProperty(value="시간")
	private Integer ptTime;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name="pt_student_id", referencedColumnName="id")
	@ApiModelProperty(value="강사아이디")
	private User ptStudentId;
	
	@Column(name="is_attend")
	@ApiModelProperty(value="참석여부")
	private Boolean isAttend;
	
	@Column(name="date_time", columnDefinition="datetime")
	@ApiModelProperty(value="예약날짜")
	private LocalDate dateTime;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name="pt_clicked_name", referencedColumnName="pt_name")
	@ApiModelProperty(value="강사아이디")
	private PtInfo ptClickedName;
}
