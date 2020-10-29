package com.utsc.project_coding_lads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.repository.ImpactLearnerRepository;
import com.utsc.project_coding_lads.service.ImpactLearnerService;

@Service
@Transactional
public class ImpactLearnerServiceImpl implements ImpactLearnerService {

	@Autowired
	ImpactLearnerRepository impactLearnerRepo;
	
	@Override
	public Integer storeImpactLearner(ImpactLearner impactLearner) throws Exception {
		return impactLearnerRepo.save(impactLearner).getId();
	}

	@Override
	public Course addCourse(Integer courseId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCourse(Integer courseId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getAllCoursesByLearner(ImpactLearner learnerId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}