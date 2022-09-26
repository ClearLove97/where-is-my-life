package membertest.dao;

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
import org.springframework.stereotype.Repository;

import membertest.dto.MemberDto;

@Repository
public class MemberDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberDto insert(MemberDto memberDto){
//		(id,name,password,numbersum,resident,address)
		String sql = "insert into member values (?,?,?,?,?,?)";
		int numbersum = memberDto.getNumber1() + memberDto.getNumber2() + memberDto.getNumber3();
		Map<String ,Object> parameter = new HashMap<>();
		parameter.put("id", memberDto.getId());
		parameter.put("name", memberDto.getName());
		parameter.put("numbersum", numbersum);
		parameter.put("password", memberDto.getPassword());
		parameter.put("resident", memberDto.getResident());
		parameter.put("address", memberDto.getAddress());
		Number result = jdbcTemplate.update(sql,memberDto.getId(),memberDto.getName(),numbersum,memberDto.getPassword(),memberDto.getResident(),memberDto.getAddress() );
		System.out.println(result);
		return memberDto;
	}
	public Optional<MemberDto> findById(String id){
		List<MemberDto> result = jdbcTemplate.query("select * from member where id = ?", MemberDtoRowMapper(), id);
		return result.stream().findAny();
	}
	public List<MemberDto> findByAll(){
		List<MemberDto> result = jdbcTemplate.query("select * from member", MemberDtoRowMapper());
		return result;
	}
	
	private RowMapper<MemberDto> MemberDtoRowMapper(){
		return new RowMapper<MemberDto>() {
			@Override
			public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDto memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setNumbersum(rs.getInt("numbersum"));
				memberDto.setResident(rs.getString("resident"));
				memberDto.setAddress(rs.getString("address"));
				return memberDto;
			}
		};
	}
}
