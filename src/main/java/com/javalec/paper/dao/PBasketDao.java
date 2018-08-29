package com.javalec.paper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.paper.dto.PID;
import com.javalec.paper.util.Constant;

public class PBasketDao {

	DataSource dataSource;
	JdbcTemplate template = null;
	
	public PBasketDao() {
		// TODO Auto-generated constructor stub
		template = Constant.template;
	}
	
	public void write(final int pId, final String userId) {
		// TODO Auto-generated method stub
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into pbasket (pId,userId) values (?, ? )";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, pId);
				pstmt.setString(2, userId);
				return pstmt;
			}
		});	
	}
	
	public ArrayList<PID> list(String userId) {
		String query = "select pId from pbasket where userId = " + "'" + userId + "'";
		return (ArrayList<PID>)template.query(query, new BeanPropertyRowMapper<PID>(PID.class));
	}
	
	public void delete(final String pId, final String userId) {
		// TODO Auto-generated method stub
		String query = "delete from pbasket where pId = ? and userId = ?";
		template.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, pId);
				ps.setString(2, userId);
			}
		});
	}
}
