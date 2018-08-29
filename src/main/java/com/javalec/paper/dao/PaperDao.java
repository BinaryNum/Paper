package com.javalec.paper.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.paper.dto.PID;
import com.javalec.paper.dto.Paper;
import com.javalec.paper.util.Constant;

public class PaperDao {
	DataSource dataSource;
	JdbcTemplate template = null;
	public PaperDao() {
		// TODO Auto-generated constructor stub
		template = Constant.template;
	}
	
public ArrayList<Paper> list(String country, String field) {
	String query = "select * from paper where country= "+ "'"+country+"'"+" and field= "+"'"+field+"'"+" order by pId desc";
	return (ArrayList<Paper>)template.query(query, new BeanPropertyRowMapper<Paper>(Paper.class));	
	}

	
	public ArrayList<Paper> selectrandom(String interest) {
		String query =  "SELECT * FROM paper WHERE field = "+"'"+interest+"'";
		return (ArrayList<Paper>)template.query(query, new BeanPropertyRowMapper<Paper>(Paper.class));	
	}
	
	public Paper contentView(String strID) {
		// TODO Auto-generated method stub
		String query = "select * from paper where pId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<Paper>(Paper.class));
	}
	
	
	public ArrayList<Paper> select() {
		String query = "SELECT * FROM paper";
		return (ArrayList<Paper>)template.query(query, new BeanPropertyRowMapper<Paper>(Paper.class));	
	}

public ArrayList<Paper> basket(ArrayList<PID> pIds) {
		Paper dto = new Paper();
		ArrayList<Paper> dtos = new ArrayList<Paper>();
		for(int i = 0; i<pIds.size();i++) {
			int pId = pIds.get(i).getpId();
			String query = "select * from paper where pId = " + pId;
			dto = template.queryForObject(query, new BeanPropertyRowMapper<Paper>(Paper.class));
			dtos.add(dto);
		}
		return dtos;
	}
	
public ArrayList<Paper> search(String search) {
	String query = "select * from paper where name like '%"+search+"%'";
	return (ArrayList<Paper>)template.query(query, new BeanPropertyRowMapper<Paper>(Paper.class));	
}

	
	public void write(final String name,final String image,final String download,final String field, final String country, final String institution, final String author) {
		// TODO Auto-generated method stub
		String query = "insert into paper (Name, image, download, field, country, institution, author) values ( ?, ?, ?, ?, ?, ?, ? )";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				// TODO Auto-generated method stub
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, image);
				preparedStatement.setString(3, download);
				preparedStatement.setString(4, field);
				preparedStatement.setString(5, country);
				preparedStatement.setString(6, institution);
				preparedStatement.setString(7, author);
			}
		});	
	}
	
}
