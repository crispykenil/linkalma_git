package com.linkalma.bo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.linkalma.bo.ISchoolBO;
import com.linkalma.dao.ISchoolDAO;
import com.linkalma.dao.IUserDAO;
import com.linkalma.dto.School;
import com.linkalma.dto.SchoolDataDTO;
import com.linkalma.dto.SchoolUpdateDTO;
import com.linkalma.dto.StaticCodesDTO;
import com.linkalma.utils.ApplicationConstants;
import com.linkalma.utils.CategoryCodesDAO;

public class SchoolBO implements ISchoolBO 
{
	@Autowired
	private ISchoolDAO schoolDAO;

	@Autowired
	private CategoryCodesDAO categoryCodesDAO;

	@Autowired
	private IUserDAO userDAO;

	@Override
	@Transactional
	public Model createSchool(School schoolDto, Model model) {
		
		long schoolID = schoolDAO.createSchool(schoolDto);
		System.out.println("School Insert Success : "+schoolID);
		schoolDto.setSchoolID(schoolID);
		int updateStatus = schoolDAO.createSchoolCredentials(schoolDto);
		System.out.println("Credentials Update Status: "+updateStatus);
		
		model.addAttribute("successMsg","School Registered");
		return model;
	}

	@Override
	public Model getSchoolList(School schoolDto, Model model) {
		// TODO Auto-generated method stub
		List<School> schoolList = getSchoolDAO().listSchools();
		System.out.println("SchoolList Size : "+schoolList.size());
		model.addAttribute("schoolList",schoolList );
		return model;
	}

	@Override
	public School getSchoolBySchoolEmailID(String emailID, Model model) {

		School school = getSchoolDAO().getSchoolBySchoolEmailID(emailID);
		
		return school;
	}

	/**
	 * @return the schoolDAO
	 */
	public ISchoolDAO getSchoolDAO() {
		return schoolDAO;
	}

	/**
	 * @param schoolDAO the schoolDAO to set
	 */
	public void setSchoolDAO(ISchoolDAO schoolDAO) {
		this.schoolDAO = schoolDAO;
	}

	@Override
	public Model updateSchoolNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getSchoolUpdates(SchoolUpdateDTO schoolUpdateDto, Model model) {
		List<SchoolUpdateDTO> schoolUpdateDtoList = getSchoolDAO().getSchoolUpdates(schoolUpdateDto.getSchoolID(), schoolUpdateDto.getUpdateType());
		model.addAttribute("schoolNewsDtoList", schoolUpdateDtoList);
		return model;
	}

	@Override
	public Model getSchoolData(SchoolUpdateDTO schoolUpdateDto, Model model) {
		List<SchoolDataDTO> schoolDataDtoList = getSchoolDAO().getSchoolData(schoolUpdateDto.getSchoolID(), schoolUpdateDto.getUpdateType());
		model.addAttribute("schoolDataDtoList", schoolDataDtoList);
		return model;
	}

	@Override
	public Model updateSchoolUpdates(SchoolUpdateDTO schoolUpdateDto, Model model) {
		long id = getSchoolDAO().updateSchoolUpdates(schoolUpdateDto, schoolUpdateDto.getUpdateType());
		
		if (id > 0)
			schoolUpdateDto.setSuccessMsg(ApplicationConstants.UPDATE_SUCCESS_MSG);
		
		model.addAttribute("schoolUpdateDto", schoolUpdateDto);
		return model;
	}

	@Override
	public Model updateSchoolData(SchoolDataDTO schoolDataDto, Model model) {
		long id = getSchoolDAO().updateSchoolData(schoolDataDto, schoolDataDto.getDataType());
		
		if (id > 0)
			schoolDataDto.setSuccessMsg(ApplicationConstants.UPDATE_SUCCESS_MSG);
		
		model.addAttribute("schoolDataDto", schoolDataDto);
		
		return model;
	}

	@Override
	public Model getSchoolDataBySchoolID(SchoolUpdateDTO schoolUpdateDto, Model model) {
		
		List<StaticCodesDTO> staticCodesDtoList = getCategoryCodesDAO().getStaticCodesForCategoryID(ApplicationConstants.SCHOOL_DATA_CTGRY_CODE);
		
		Map<String, List<SchoolDataDTO>> schoolDataMap = getSchoolDAO().getSchoolDataByTypeForSchool(staticCodesDtoList, schoolUpdateDto.getSchoolID());
		
		model.addAttribute("schoolDataMap", schoolDataMap);
		return model;
	}

	@Override
	public Model getSchoolUpdatesBySchoolID(SchoolUpdateDTO schoolUpdateDto, Model model) {
		
		List<StaticCodesDTO> staticCodesDtoList = getCategoryCodesDAO().getStaticCodesForCategoryID(ApplicationConstants.SCHOOL_UPDATES_CTGRY_CODE);
		
		Map<String, List<SchoolUpdateDTO>> schoolUpdatesMap  = getSchoolDAO().getSchoolUpdatesByTypeForSchool(staticCodesDtoList, schoolUpdateDto.getSchoolID());
		
		model.addAttribute("schoolUpdatesMap",schoolUpdatesMap);
		
		return model;
	}

	/**
	 * @return the categoryCodesDAO
	 */
	public CategoryCodesDAO getCategoryCodesDAO() {
		return categoryCodesDAO;
	}

	/**
	 * @param categoryCodesDAO the categoryCodesDAO to set
	 */
	public void setCategoryCodesDAO(CategoryCodesDAO categoryCodesDAO) {
		this.categoryCodesDAO = categoryCodesDAO;
	}

	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}