package com.javalec.paper.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.paper.dto.CDto;
import com.javalec.paper.util.Constant;

public class CDao {

	DataSource dataSource;
	JdbcTemplate template = null;
	public CDao() {
		// TODO Auto-generated constructor stub
		template = Constant.template;
	}

	public void write(final String bName, final String bTitle, final String bContent) {
		// TODO Auto-generated method stub
		String query = "insert into COMMUNITY (bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (?, ?, ?, 0, LAST_INSERT_ID(), 0, 0 )";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
			}
		});
	}

	public ArrayList<CDto> list() {
		String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from COMMUNITY order by bGroup desc, bStep asc, bId desc";
		return (ArrayList<CDto>)template.query(query, new BeanPropertyRowMapper<CDto>(CDto.class));
	}

	public CDto contentView(String strID) {
		// TODO Auto-generated method stub
		upHit(strID);
		String query = "select * from COMMUNITY where bId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<CDto>(CDto.class));
	}

	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		// TODO Auto-generated method stub
		String query = "update COMMUNITY set bTitle = ?, bContent = ? where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bTitle);
				ps.setString(2, bContent);
				ps.setInt(3, Integer.parseInt(bId));
			}
		});
	}

	public void delete(final String bId, final String bGroup, String bStep) {
		// TODO Auto-generated method stub
		String query = "delete from COMMUNITY where bGroup = ?";
		if (Integer.parseInt(bStep) == 0) {
			template.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setInt(1, Integer.parseInt(bGroup));
				}
			});
		} else {
			query = "delete from COMMUNITY where bId = ?";
			template.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setInt(1, Integer.parseInt(bId));
				}
			});
		}
	}

	public CDto reply_view(String str) {
		// TODO Auto-generated method stub
		String query = "select * from COMMUNITY where bId = " + str;
		return template.queryForObject(query, new BeanPropertyRowMapper<CDto>(CDto.class));
	}

	public void reply(String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep,
			final String bIndent) {
		// TODO Auto-generated method stub
		String query = "insert into COMMUNITY (bName, bTitle, bContent, bGroup, bStep, bIndent) values ( ?, ?, ?, ?, ?, ?)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				// TODO Auto-generated method stub
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt(bGroup));
				preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
				preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			}
		});
	}

	private void replyShape(final String strGroup, final String strStep) {
		// TODO Auto-generated method stub
		String query = "update COMMUNITY set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				// TODO Auto-generated method stub
				preparedStatement.setInt(1, Integer.parseInt(strGroup));
				preparedStatement.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
	
	private void upHit(final String bId) {
		// TODO Auto-generated method stub
		String query = "update COMMUNITY set bHit = bHit + 1 where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				// TODO Auto-generated method stub
				preparedStatement.setString(1, bId);
			}
		});
	}
}
