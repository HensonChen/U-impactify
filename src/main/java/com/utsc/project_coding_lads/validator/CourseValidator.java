package com.utsc.project_coding_lads.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utsc.project_coding_lads.domain.ClassSession;
import com.utsc.project_coding_lads.domain.Course;
import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.exception.*;
import com.utsc.project_coding_lads.service.ClassSessionService;
import com.utsc.project_coding_lads.service.CourseService;
import com.utsc.project_coding_lads.service.ImpactConsultantService;

@Component
public class CourseValidator implements Validator {
	
	private Integer courseId;
	private String courseName;
	private String courseDesc;
	private ImpactConsultant instructor;
	private List<ClassSession> session;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ImpactConsultantService impactConsultantService;
	
	@Autowired
	ClassSessionService classSessionService;
	
	public CourseValidator() {
		super();
	}
	
	public CourseValidator(Course course) {
		super();
		this.courseId = course.getId();
		this.courseName = course.getCourseName();
		this.courseDesc = course.getCourseDesc();
		this.instructor = course.getInstructor();
		this.session = course.getSessions();
	}

	@Override
	public void validate() throws ValidationFailedException {
		// I assume a course can have no class session
		if (courseName == null || courseDesc == null || instructor == null)
			throw new MissingInformationException("The required field is missing");
		if (instructor.getId() == null)
			throw new EntityNotExistException("The impact consultant id cannot be null");
		if (impactConsultantService.findImpactConsultantById(instructor.getId()) == null)
			throw new UnauthenticatedException("The given impact consultant is not an instructor");
		// Validate class session (sessions always follow the course so no need to validate)
//		if (session != null) {
//			for (ClassSession s : session) {
//				if (classSessionService.findSessionById(s.getId()) == null)
//					throw new UnauthenticatedException("There are some class sessions do not exist");
//			}
//		}
			
	}
	
	public void validateExist() throws ValidationFailedException {
		validate();
		if (!courseService.existById(courseId))
			throw new EntityNotExistException("This course does not exist");
	}

}