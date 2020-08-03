package com.ssafy.yogart.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.qna.model.QnA;

public interface QnARepository extends JpaRepository<QnA, Integer> {
	QnA findByQnaId(Integer qnaid);
}
