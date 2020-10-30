package com.utsc.project_coding_lads.service;

import java.util.List;

import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.exception.EntityNotExistException;

public interface ImpactConsultantService {
	
	public boolean existById(Integer id);

	public Integer storeImpactConsultantService(ImpactConsultant impactConsultant) throws Exception;

	public ImpactConsultant findImpactConsultantById(Integer id);

	public List<Course> findAllCoursesByInstructorId(Integer id) throws Exception;
	
}
