package uz.pdp.wbsite.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.wbsite.model.Person;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Person person){
        String insertQuery="INSERT INTO Person (firstname,lastname,email,username, password) values (?,?,?,?,?)";
        jdbcTemplate.update(insertQuery,
                person.getFirstname(),person.getLastname(),person.getEmail(), person.getUsername(), person.getPassword());
    };
    public Person getByUsername(String username){
        /*String query = "select * from Person where username = " + username;
        return jdbcTemplate.queryForObject(query,Person.class);*/
        String readQuery = "SELECT u.id, u.email, u.username, u.password FROM Person u WHERE u.username=? LIMIT 1";
        RowMapper<Person> mapper = (rs, n) -> Person.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
        Person user = jdbcTemplate.queryForObject(readQuery, mapper, username);
        return user;
    }
}
