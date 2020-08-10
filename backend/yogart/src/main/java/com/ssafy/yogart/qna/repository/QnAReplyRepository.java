package com.ssafy.yogart.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.yogart.qna.model.QnA;
import com.ssafy.yogart.qna.model.QnAReply;

public interface QnAReplyRepository extends JpaRepository<QnAReply, Integer> {
	@Query(value="SELECT * FROM qna_reply WHERE qna_reply_qna_id = ?1", nativeQuery=true)
	List<QnAReply> findByReplyQnaId(@Param("qnaId") int qnaId);
}
