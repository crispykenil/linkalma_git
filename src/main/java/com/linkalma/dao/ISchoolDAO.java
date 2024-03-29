package com.linkalma.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import com.linkalma.dto.School;
import com.linkalma.dto.SchoolAlbum;
import com.linkalma.dto.SchoolDataDTO;
import com.linkalma.dto.SchoolGallery;
import com.linkalma.dto.SchoolUpdateDTO;
import com.linkalma.dto.Staff;
import com.linkalma.dto.StaffInfo;
import com.linkalma.dto.StaticCodesDTO;
import com.linkalma.dto.UserSchoolDTO;
import com.linkalma.utils.LinkalmaException;

public interface ISchoolDAO {

	/** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);

	   /** 
	    * This is the method to be used to create
	    * a record in the School table.
	    */
	   public long createSchool(School school);

	   /** 
	    * This is the method to be used to create
	    * a record in the Credentials table for school.
	    */
	   public int createSchoolCredentials(School school);

	   /** 
	    * This is the method to be used to create
	    * a record in the Credentials table for school.
	    */
	   public int updateSchoolCredentials(School school);

	   /** 
	    * This is the method to be used to create
	    * a record in the Credentials table for school.
	    */
	   public int updateSchoolCredentialsByEmailID(School schoolDto) throws SQLException, LinkalmaException;

	   /** 
	    * This is the method to be used to list down
	    * a record from the School table corresponding
	    * to a passed School id.
	    */
	   public School getSchool(String schoolName);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the School table.
	    */
	   public List<School> listSchools();
	   /** 
	    * This is the method to be used to delete
	    * a record from the School table corresponding
	    * to a passed School id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the School table.
	    */
	   public long update(Integer id, Integer age);
	   
	   public List<UserSchoolDTO> listLinkedSchools(long userID);

	   public int deleteSchool(long userSchoolID);
	   
	   public School getSchoolBySchoolEmailID(String emailID);
	   
	   // newsType can have 1. Projects, 2.Events, 3. Newsletter 
	   public List<SchoolUpdateDTO> getSchoolUpdates(long schoolID, int updateType);

	   // newsType can have 1. Syllabus, 2. Calendar, 3. Exams 
	   public List<SchoolDataDTO> getSchoolData(long schoolID, int dataType);

	   // newsType can have 1. Projects, 2.Events, 3. Newsletter 
	   public long updateSchoolUpdates(SchoolUpdateDTO schoolUpdateDto, int updateType);

	   // newsType can have 1. Syllabus, 2. Calendar, 3. Exams 
	   public long updateSchoolData(SchoolDataDTO schoolDataDto, int dataType);

	   public Map<String, List<SchoolDataDTO>> getSchoolDataByTypeForSchool(List<StaticCodesDTO> staticCodeList, long schoolID);

	   public Map<String, List<SchoolUpdateDTO>> getSchoolUpdatesByTypeForSchool(List<StaticCodesDTO> staticCodeList, long schoolID);
	   
	   public List<SchoolUpdateDTO> getSchoolAllUpdates(long schoolID);
	   
	   public String checkLinkalmaURL(String linkalmaUrl);
	   
	   public int updateAboutSchoolInfo(SchoolDataDTO schoolDataDto);

	   public boolean checkSchoolExists(String emailAddress);
	   
	   public long createStaff(Staff staff);
	   
	   public long createSchoolGallery(SchoolGallery schoolGallery);
	   
	   public List<SchoolAlbum> getSchoolAlbumsBySchoolId(long schoolId);
	   
	   public List<StaffInfo>  getStaffInfoBySchoolId(long schoolId);
}