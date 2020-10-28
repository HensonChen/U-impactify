package com.utsc.project_coding_lads.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.utsc.project_coding_lads.domain.Posting;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.exception.ValidationFailedException;
import com.utsc.project_coding_lads.service.PostingService;
import com.utsc.project_coding_lads.service.UserService;

@Component
@Transactional
public class ApplicationValidator implements Validator {

	private User applicant;
	private Posting posting;
	private String email;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PostingService postingService;
	
	public void init(User applicant, Posting posting, String email) {
		this.applicant = applicant;
		this.posting = posting;
		this.email = email;
	}

	@Override
	public void validate() throws ValidationFailedException {
		if (applicant == null)
			throw new EntityNotExistException("The user cannot be null.");
		if (applicant.getId() == null)
			throw new EntityNotExistException("The user id cannot be null.");
		if (!userService.existsById(applicant.getId()))
			throw new EntityNotExistException("The user does not exist in the database.");
		if (posting == null)
			throw new EntityNotExistException("The posting cannot be null.");
		if (posting.getId() == null)
			throw new EntityNotExistException("The posting id cannot be null.");
		if (!postingService.existsById(posting.getId()))
			throw new EntityNotExistException("The posting does not exist in the database.");
		if (email == null)
			throw new EntityNotExistException("The email cannot be null.");
		if (!emailValidate(email))
			throw new EntityNotExistException("The email is not a valid email.");
	}
	
	public Boolean emailValidate(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(email).matches();
	}
	
}
