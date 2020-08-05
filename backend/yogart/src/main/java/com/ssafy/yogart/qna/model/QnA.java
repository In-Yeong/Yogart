package com.ssafy.yogart.qna.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "qna")
public class QnA implements Serializable {
	@Id
	@Column(name="qna_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value="질문아이디")
	private Integer qnaId;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="qna_user_email", referencedColumnName = "user_email")
    @ApiModelProperty(value="email")
    private User userEmail;
	
	@Column(name="qna_title")
    @ApiModelProperty(value="제목")
	private String qnaTitle;
	
	@Column(name="qna_content", columnDefinition = "text")
    @ApiModelProperty(value="내용")
	private String qnaContent;
	
	@CreationTimestamp
	@Column(insertable = false, updatable = false)
    @ApiModelProperty(value="생성일")
	private LocalDateTime createDate;
	
//	@Column(name="qna_user_id")
//    @ApiModelProperty(value="이름")
//	private String qnaUserid;
	
//	@Column(insertable = false, updatable = false)
//    @ApiModelProperty(value="질문생성일")
//	private Date qnaDate;
//	
//	@Column(name="qna_reply_content")
//    @ApiModelProperty(value="이름")
//	private String qnaReplyContent;
//	
//	@Column(insertable = false, updatable = false)
//    @ApiModelProperty(value="댓글생성일")
//	private Date qnaReplyDate;
//	
//	@Column(name="qna_reply_user_id")
//    @ApiModelProperty(value="댓글아이디")
//	private String qnaReplyUserid;
}
