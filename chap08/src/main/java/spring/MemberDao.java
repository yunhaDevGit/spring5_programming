package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Member member){

    }

    public void update(Member member){

    }

//    public Member selectByEmail(String email){
//        List<Member> results = jdbcTemplate.query(
//                "select * from MEMBER where EMAIL = ?",
//                new RowMapper<Member>() {
//                    @Override
//                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Member member = new Member(
//                                rs.getString("EMAIL"),
//                                rs.getString("PASSWORD"),
//                                rs.getString("NAME"),
//                                rs.getTimestamp("REGDATE").toLocalDateTime());
//                        member.setId(rs.getLong("ID"));
//                        return member;
//                    }
//                }
//                ,email); // email은 앞의 sql의 파라미터이다.
//
//        return results.isEmpty() ? null : results.get(0);
//        // query() 실행 결과가 존재하지 않으면 길이가 0인 List 리턴
//        // List가 비어 있는지 여부로 결과 존재 여부 확인할 수 있다.
//    }
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                new MemberRowMapper(), email);
        return results.isEmpty() ? null : results.get(0);
    }

//    public List<Member> selectAll(){
//        List<Member> results = jdbcTemplate.query(
//                "select * from MEMBER",
//                new RowMapper<Member>() {
//                    @Override
//                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Member member = new Member(
//                                rs.getString("EMAIL"),
//                                rs.getString("PASSWORD"),
//                                rs.getString("NAME"),
//                                rs.getTimestamp("REGDATE").toLocalDateTime());
//                        member.setId(rs.getLong("ID"));
//                        return member;
//                    }
//                });
//        return results;
//    }
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                new MemberRowMapper());
        return results;
    }
}