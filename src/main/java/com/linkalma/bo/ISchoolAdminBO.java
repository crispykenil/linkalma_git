package com.linkalma.bo;

import org.springframework.ui.Model;

import com.linkalma.dto.School;

public interface ISchoolAdminBO {

	public Model updateAdminProfile(School schoolDto, Model model);
	
	public Model getSchoolList(School schoolDto, Model model);
	
	public School getSchoolBySchoolEmailID(String emailID, Model model);
	
}
