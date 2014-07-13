package com.linkalma.bo.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.linkalma.bo.ISchoolBO;
import com.linkalma.dao.ISchoolDAO;
import com.linkalma.dao.IUserDAO;
import com.linkalma.dto.School;
import com.linkalma.dto.SchoolAlbum;
import com.linkalma.dto.SchoolDataDTO;
import com.linkalma.dto.SchoolGallery;
import com.linkalma.dto.SchoolUpdateDTO;
import com.linkalma.dto.Staff;
import com.linkalma.dto.StaffInfo;
import com.linkalma.dto.StaticCodesDTO;
import com.linkalma.helper.FileHelperImpl;
import com.linkalma.utils.ApplicationConstants;
import com.linkalma.utils.CategoryCodesDAO;
import com.linkalma.utils.LinkalmaConstants;
import com.linkalma.utils.LinkalmaException;
import com.linkalma.utils.LinkalmaUtil;

public class SchoolBO implements ISchoolBO 
{
	@Autowired
	private ISchoolDAO schoolDAO;

	@Autowired
	private CategoryCodesDAO categoryCodesDAO;

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private FileHelperImpl fileHelperImpl;
	
	@Autowired
	private LinkalmaUtil linkalmaUtil;
	public LinkalmaUtil getLinkalmaUtil() {
		return linkalmaUtil;
	}

	public void setLinkalmaUtil(LinkalmaUtil linkalmaUtil) {
		this.linkalmaUtil = linkalmaUtil;
	}

	public FileHelperImpl getFileHelperImpl() {
		return fileHelperImpl;
	}

	public void setFileHelperImpl(FileHelperImpl fileHelperImpl) {
		this.fileHelperImpl = fileHelperImpl;
	}

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
	@Transactional
	public Model updateSchoolData(SchoolDataDTO schoolDataDto, Model model) {

		try {

			MultipartFile multipartFile = schoolDataDto.getUploadedFile();

			if (multipartFile != null
					&& !StringUtils.isEmpty(multipartFile.getOriginalFilename())) 
			{
				schoolDataDto.setDocumentName(multipartFile.getOriginalFilename());
				
				long id = getSchoolDAO().updateSchoolData(schoolDataDto,schoolDataDto.getDataType());
				// If DB Insert successful then go ahead for FileUpload...
				if (id > 0) 
				{
					String schoolParentDir = schoolDataDto.getSchoolName()+ "_" + schoolDataDto.getSchoolID();

					fileHelperImpl.writeFile(multipartFile,
									linkalmaUtil.getCurriculumFileUploadPath(schoolParentDir, multipartFile.getOriginalFilename(),
											String.valueOf(schoolDataDto.getDataType())));
				}
				schoolDataDto
						.setSuccessMsg(ApplicationConstants.UPDATE_SUCCESS_MSG);
			}
		} catch (Exception e) {
			schoolDataDto.setSuccessMsg("Failed to Update");
			e.printStackTrace();
		}

		model.addAttribute("schoolDataDto", schoolDataDto);

		return model;
	}

	@Override
	public Model getSchoolDataBySchoolID(SchoolUpdateDTO schoolUpdateDto, Model model) {
		
		List<StaticCodesDTO> staticCodesDtoList = getCategoryCodesDAO().getStaticCodesForCategoryID(ApplicationConstants.SCHOOL_DATA_CTGRY_CODE);
		
		Map<String, List<SchoolDataDTO>> schoolDataMap = getSchoolDAO().getSchoolDataByTypeForSchool(staticCodesDtoList, schoolUpdateDto.getSchoolID());
		
		String schoolParentDir=schoolUpdateDto.getSchoolName()+"_"+schoolUpdateDto.getSchoolID();
		
		
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

	@Override
	public void updateAboutSchoolInfo(SchoolDataDTO schoolDataDto) throws FileNotFoundException, IOException ,LinkalmaException
	{
			this.validateForSchoolAboutInfo(schoolDataDto);
			MultipartFile multipartFile=schoolDataDto.getUploadedFile();
			
			if(multipartFile!=null && !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
			{
				fileHelperImpl.writeFile(multipartFile, linkalmaUtil.prepareFileUploadPath(multipartFile.getOriginalFilename()));
			}
			schoolDAO.updateAboutSchoolInfo(schoolDataDto);
	}
	
	private void validateForSchoolAboutInfo(SchoolDataDTO schoolDataDto) throws LinkalmaException
	{

		if(StringUtils.isEmpty(StringUtils.trim(schoolDataDto.getSchoolName())))
		{
			throw new LinkalmaException("School Name not found");
		}
		if(StringUtils.isEmpty(StringUtils.trim(schoolDataDto.getWebsiteAddress())))
		{
			throw new LinkalmaException("Website Address not found");
		}
		if(StringUtils.isEmpty(StringUtils.trim(schoolDataDto.getLinkalmaUrl())))
		{
			throw new LinkalmaException("Linkalma URL not found");
		}
		if(StringUtils.isEmpty(StringUtils.trim(schoolDataDto.getSchoolHistory())))
		{
			throw new LinkalmaException("School History not found");
		}
		if(StringUtils.isEmpty(StringUtils.trim(schoolDataDto.getSchoolContact())))
		{
			throw new LinkalmaException("School Contact not found");
		}
		
	}

	@Override
	public boolean checkSchoolExists(String emailAddress, Model model) {
		return getSchoolDAO().checkSchoolExists(emailAddress);
		
	}

	@Override
	public long createStaff(Staff staff,School school)  throws FileNotFoundException, IOException ,LinkalmaException{
		
		MultipartFile multipartFile=staff.getUploadedFile();
		String schoolParentDir=school.getSchoolName()+"_"+school.getSchoolID();
		if(multipartFile!=null && !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
		{
			staff.setPhotoName(multipartFile.getOriginalFilename());
			fileHelperImpl.writeFile(multipartFile, linkalmaUtil.getStaffImageFileUploadPath(schoolParentDir,multipartFile.getOriginalFilename()));
		}
		Long pkStaffID=schoolDAO.createStaff(staff);
		return pkStaffID;
	}

	@Override
	public void createSchoolGallery(SchoolGallery schoolGallery)throws FileNotFoundException, IOException, LinkalmaException {
		
		List<MultipartFile> multipartFiles=schoolGallery.getUploadedFileList();
		String schoolParentDir;
		for(MultipartFile multipartFile:  multipartFiles)
		{
			schoolGallery.setPhotoName(multipartFile.getOriginalFilename());
			schoolParentDir=schoolGallery.getSchoolName()+"_"+schoolGallery.getSchoolID();
			fileHelperImpl.writeFile(multipartFile, linkalmaUtil.getAlbumFileUploadPath(schoolParentDir
																				 ,schoolGallery.getAlbumName()
																				 ,multipartFile.getOriginalFilename()));
			schoolDAO.createSchoolGallery(schoolGallery);
		}
		
		return ;
	}

	@Override
	public void getSchoolAlbums(School school, Model model) {
		
		List<SchoolAlbum> schoolAlbumList = schoolDAO.getSchoolAlbumsBySchoolId(school.getSchoolID());
		model.addAttribute("schoolAlbumList", schoolAlbumList);
		
		String schoolParentDir=school.getSchoolName()+"_"+school.getSchoolID();
		model.addAttribute("IMAGE_HOST_PATH", linkalmaUtil.getAlbumServePath(schoolParentDir));
	}

	@Override
	public void getSchoolStaff(School school, Model model) {

		List<StaffInfo>	staffInfoList=schoolDAO.getStaffInfoBySchoolId(school.getSchoolID());
		model.addAttribute("staffInfoList", staffInfoList);
		
		String schoolParentDir=school.getSchoolName()+"_"+school.getSchoolID();
		model.addAttribute("IMAGE_HOST_PATH", linkalmaUtil.getStaffImageServePath(schoolParentDir));
		
	}
	
}
