package com.ssafy.yogart.teachers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.yogart.teachers.model.PtClicked;
import com.ssafy.yogart.teachers.model.PtInfo;

public interface PtClickedRepository extends JpaRepository<PtClicked, Integer> {
	List<PtClicked> findByPtClickedName(PtInfo ptinfo);
}
