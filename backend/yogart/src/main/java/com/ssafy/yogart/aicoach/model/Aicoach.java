package com.ssafy.yogart.aicoach.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ai_cource")
public class Aicoach {
	@Id
	@Column(name="ai_course_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="질문아이디")
	private Integer qnaId;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ai_course_user_nickname", referencedColumnName = "user_email")
    @ApiModelProperty(value="email")
    private User userEmail;
	
	@Column(name="qna_title")
    @ApiModelProperty(value="제목")
	private String qnaTitle;
	
	@Column(name="qna_content", columnDefinition = "text")
    @ApiModelProperty(value="내용")
	private String qnaContent;
}
