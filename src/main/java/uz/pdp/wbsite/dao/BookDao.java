package uz.pdp.wbsite.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.wbsite.model.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Book book){
        String insertQuery="INSERT INTO Book (name,author,genre) values (?,?,?)";
        jdbcTemplate.update(insertQuery,
                book.getName(),book.getAuthor(),book.getGenre());
    }

    public List<Book> showAll(){
        String readQuery = "SELECT * FROM Book";
        RowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(readQuery, rowMapper);}
    public Book getBookById(Integer id){
        String sql = "select * from Book where id=" + id;
        return jdbcTemplate.queryForObject(sql, Book.class);
    }
    public void update(Integer id,Book book) {
        String updateQuery = "update Book set name = ?,author = ?,genre = ? where id = ?";
        jdbcTemplate.update(updateQuery,book.getName(),book.getAuthor(),book.getGenre(),id);
    }

    public void delete(Integer id) {
        String delete = "delete from Book where id = ?";
        jdbcTemplate.update(delete,id);
    }
}
