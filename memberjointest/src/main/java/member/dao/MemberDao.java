package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import member.dto.MemberDto;

public class MemberDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberDto save(MemberDto memberDto) {
		
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("name", memberDto.getName());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
		memberDto.setId(key.intValue());
		return memberDto;
	} // 자동으로 id 값을올림하면서 넣어주는 역할
		
	public Optional<MemberDto> findById(int id){
		List<MemberDto> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return  result.stream().findAny();
	}
	
	public Optional<MemberDto> findByName(String name){
		List<MemberDto> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	}
	
	public Optional<MemberDto> findByGender(String gender){
		List<MemberDto> result = jdbcTemplate.query("select * from member where gender = ?", memberRowMapper(), gender);
	}
	
	public List<MemberDto> findByAll(){
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}
	
	private RowMapper<MemberDto> memberRowMapper(){
		return new RowMapper<MemberDto>() {
			@Override
			public MemberDto mapRow(ResultSet rs , int rowNum) throws SQLException {
				MemberDto member = new MemberDto();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				return member;
			}
		};
	}
}
