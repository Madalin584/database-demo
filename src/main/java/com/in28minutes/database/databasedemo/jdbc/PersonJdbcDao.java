package com.in28minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getDate("birth_date"));
			
			return person;
		}		
	}
	
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}
	
	public Person findById(int id) {
		String sql = "select * from person where id=?";
		
		return jdbcTemplate.queryForObject(sql, new PersonRowMapper(), new Object[] {id});
	}
	
	public List<Person> findByName(String name) {
		String sql = "select * from person where name=?";
		
		return jdbcTemplate.query(sql, new PersonRowMapper(), new Object[] {name});
	}
	
	public List<Person> findByLocation(String location) {
		String sql = "select * from person where location=?";
		
		return jdbcTemplate.query(sql, new PersonRowMapper(), new Object[] {location});
	}
	
	public Integer deleteById(int id) {
		String sql = "delete from person where id=?";
		
		return jdbcTemplate.update(sql, id);
	}
	
	public Integer deleteByIdAndLocation(int id, String location) {
		String sql = "delete from person where id=? and location=?";
		
		return jdbcTemplate.update(sql, id, location);
	}
	
	public Integer deleteByIdOrName(int id, String name) {
		String sql = "delete from person where id=? or name=?";
		
		return jdbcTemplate.update(sql, id, name);
	}
	
	public Integer insert(Person person) {
		String sql = "insert into person(id, name, location, birth_date) values(?,?,?,?)";
		
		return jdbcTemplate.update(sql, person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
	}
	
	public Integer update(Person person) {
		String sql = "update person set name=?, location=?, birth_date=? where id=?";
		
		return jdbcTemplate.update(sql, person.getName(), person.getLocation(), person.getBirthDate(), person.getId());
	}
}




