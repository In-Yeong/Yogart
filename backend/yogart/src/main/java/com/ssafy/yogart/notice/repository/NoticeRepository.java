package com.ssafy.yogart.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.notice.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	Notice findByNoticeId(Integer noticeid);
}
