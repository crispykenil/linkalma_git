package com.linkalma.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkalma.dto.School;

public class SchoolMapper implements RowMapper<School> {
	   public School mapRow(ResultSet rs, int rowNum) throws SQLException {
		      School school = new School();
		      school.setSchoolID(rs.getLong("SchoolID"));
		      school.setSchoolName(rs.getString("SchoolName"));
		      school.setAddress1(rs.getString("Address1"));
		      school.setAddress2(rs.getString("Address2"));
		      school.setBranch(rs.getString("Branch"));
		      school.setWebsiteAddress(rs.getString("WebsiteURL"));
		      school.setLinkalmaAddress(rs.getString("LinkAlmaURL"));
		      school.setApproved(rs.getString("APPROVED"));
		      school.setActive(rs.getString("ACTIVEYN"));
		      school.setCreateDttm(rs.getDate("CreateDttm"));
		      return school;
		   }
	   
	   public SchoolMapper() {
		// TODO Auto-generated constructor stub
	}
		}