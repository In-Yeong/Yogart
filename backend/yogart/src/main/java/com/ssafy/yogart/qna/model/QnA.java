package com.ssafy.yogart.qna.model;

import java.time.LocalDateTime;


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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
public class QnA {
	@Id
	@Column(name="qna_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="아이디")
	private Integer qnaId;
	
	@Column(name="qna_title")
    @ApiModelProperty(value="제목")
	private String qnaTitle;
	
	@Column(name="qna_content")
    @ApiModelProperty(value="이름")
	private String qnaContent;
	
	@Column(insertable = false, updatable = false)
    @ApiModelProperty(value="생성일")
	private LocalDateTime createDate;
}
