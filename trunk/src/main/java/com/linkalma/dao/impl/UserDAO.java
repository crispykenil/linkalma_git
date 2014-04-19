package com.linkalma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.linkalma.dao.IUserDAO;
import com.linkalma.dto.User;
import com.linkalma.utils.ApplicationConstants;

public class UserDAO implements IUserDAO {

	@Autowired
	private DataSource dataSource;
   
	@Autowired
   private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	   private KeyHolder keyHolder;
		
	public long createUser(final User alumni) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplateObject().update(new PreparedStatementCreator() {
	    	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	    		PreparedStatement ps =  connection.prepareStatement(ApplicationConstants.INSERT_USER_QUERY, new String[] {"id"});
		    	  	
	    	  	ps.setString(1, alumni.getUserFirstName());
	    	  	ps.setString(2, alumni.getUserMiddleName());
	    	  	ps.setString(3, alumni.getUserLastName());
	    	  	ps.setString(4, alumni.getAddres1());
	    	  	ps.setString(5, alumni.getAddress2());
	    	  	ps.setLong(6, alumni.getPhone1());
	    	  	ps.setLong(7, alumni.getPhone2());
	    	  	ps.setLong(8, alumni.getPhone3());
	    	  	ps.setLong(9, alumni.getPhone4());
	    	  	ps.setString(10, alumni.getGender());
	    	  	ps.setString(11, alumni.getCity());
	    	  	ps.setString(12, alumni.getState());
	    	  	ps.setString(13, alumni.getCountry());
	    	  	ps.setString(14, alumni.getEmailAddress());
	    	  	ps.setDate(15, alumni.getDob());
	    	  	ps.setString(16, alumni.getAboutMe());
	    	  	ps.setString(17, alumni.getPhoto());
	    	  	ps.setString(18, alumni.getApproved());
	    	  	ps.setDate(19, alumni.getCreateDttm());
	    	  	ps.setDate(20, alumni.getUpdateDttm());
	    	  	
	    	  	return ps;
	    	  	}
	    }, keyHolder);
	      
	      Long newPersonId = keyHolder.getKey().longValue();
	      return newPersonId;
	}
 
 public int createCredentials(User alumni) {
	      
	      return getJdbcTemplateObject().update( ApplicationConstants.INSER_CREDENTIALS_QUERY, 
	    		  alumni.getUserID(), alumni.getPassword());
	      
	   }

/**
 * @return the jdbcTemplateObject
 */
public JdbcTemplate getJdbcTemplateObject() {
	return jdbcTemplateObject;
}

/**
 * @param jdbcTemplateObject the jdbcTemplateObject to set
 */
public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
	this.jdbcTemplateObject = jdbcTemplateObject;
}

/**
 * @return the dataSource
 */
public DataSource getDataSource() {
	return dataSource;
}

/**
 * @param dataSource the dataSource to set
 */
public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
}
}