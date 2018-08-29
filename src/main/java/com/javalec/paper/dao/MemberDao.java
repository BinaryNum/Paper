package com.javalec.paper.dao;

// DataSource 활용 
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.paper.dto.*;
import com.javalec.paper.util.Constant;

public class MemberDao {
  DataSource ds;
  JdbcTemplate template = null;
  public MemberDao() {
		// TODO Auto-generated constructor stub
	  	template = Constant.template;
	}

  public void insert(final String name,final String password,final String email,final String id,final String interested,final String phonenumber,final String gender) throws Exception  { 
	  String sql ="INSERT INTO MEMBER(NAME,PASSWORD,EMAIL,ID,INTERESTED,PHONENUMBER,GENDER) VALUES (?,?,?,?,?,?,?)";
	  template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement stmt) throws SQLException {
			// TODO Auto-generated method stub
		      stmt.setString(1, name);
		      stmt.setString(2, password);
		      stmt.setString(3, email);
		      stmt.setString(4, id);
		      stmt.setString(5, interested);
		      stmt.setString(6, phonenumber);
		      stmt.setString(7, gender);
		}
	});
  }

  public void delete(final int no)  {  
    String sql = "DELETE FROM MEMBERS WHERE MNO= ?";
    template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setInt(1, no);
		}
	});
  }

  public int selectOne(final String id) throws Exception { 
	  String sql = "SELECT * FROM MEMBER WHERE ID = '"+id+"'";
	  try {
		  template.queryForObject(sql, new BeanPropertyRowMapper<Member>(Member.class));
	  }catch(EmptyResultDataAccessException e) {
		  return 0;
	  }
	 return 1;
  }

  public Member exist(String id, String password) {
	  String sql = null;
	  Member m = null;
	  if(password==null) {
		  sql = "SELECT * FROM MEMBER WHERE ID= '" + id + "'";
	  } else {
		  sql = "SELECT * FROM MEMBER WHERE ID='"+id+"' AND PASSWORD = '" + password + "'" ;
	  }
	  try {
		  m = template.queryForObject(sql, new BeanPropertyRowMapper<Member>(Member.class));
	  }catch(EmptyResultDataAccessException e) {
		  return null;
	  }
	  m.setInterested(m.getInterested());
	  return m;
    }
}
