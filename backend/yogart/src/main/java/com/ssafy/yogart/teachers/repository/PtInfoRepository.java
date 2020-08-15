package com.ssafy.yogart.teachers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.user.model.User;

public interface PtInfoRepository extends JpaRepository<PtInfo, Integer> {
	List<PtInfo> findByPtTeacherId(User user);
	PtInfo findByPtId(Integer ptId);
	PtInfo findByPtName(String ptName);
}
