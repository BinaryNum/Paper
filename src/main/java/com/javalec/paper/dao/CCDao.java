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

import com.javalec.paper.dto.CCDto;
import com.javalec.paper.util.Constant;

public class CCDao {

	DataSource dataSource;
	JdbcTemplate template = null;
	
	public CCDao() {
		// TODO Auto-generated constructor stub	
		template = Constant.template;
	}
	
	public void write(final int bId, final String userId, final String contents) {
		// TODO Auto-generated method stub
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into comments (bId,userId, contents) values (?, ?, ? )";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, bId);
				pstmt.setString(2, userId);
				pstmt.setString(3, contents);
				return pstmt;
			}
		});	
	}
	
	public ArrayList<CCDto> list(int bId) {
		String sql = "select * from comments where bId = "+ bId
				+ " order by cId asc";
		return (ArrayList<CCDto>)template.query(sql, new BeanPropertyRowMapper<CCDto>(CCDto.class));
	}
	
	public void delete(final String cId) {
		// TODO Auto-generated method stub
		String query = "delete from comments where cId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(cId));
			}
		});
	}
}
