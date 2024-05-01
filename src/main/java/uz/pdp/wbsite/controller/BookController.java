package uz.pdp.wbsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wbsite.dao.BookDao;
import uz.pdp.wbsite.model.Book;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookDao bookDao;

    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }
    @PostMapping("/book/add")
    public String add(@ModelAttribute Book book,Model model){
        bookDao.save(book);
        List<Book> books = bookDao.showAll();
        model.addAttribute("books",books);
        return "home";
    }
    @GetMapping("/book/update/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id,Model model){
        model.addAttribute("bookId",id);
        return "update";
    }
    @PostMapping("/book/update/{id}")
    public String update(@ModelAttribute Book book,@PathVariable("id") Integer id,Model model){
        bookDao.update(id,book);
        List<Book> books = bookDao.showAll();
        model.addAttribute("books",books);
        return "home";
    }
    @GetMapping("/book/delete/{id}")
    public String delete(@PathVariable("id") Integer id,Model model) {
        bookDao.delete(id);
        List<Book> books = bookDao.showAll();
        model.addAttribute("books", books);
        return "home";
    }
}
