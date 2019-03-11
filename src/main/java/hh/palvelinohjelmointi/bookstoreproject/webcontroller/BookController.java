package hh.palvelinohjelmointi.bookstoreproject.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.bookstoreproject.domain.Book;
import hh.palvelinohjelmointi.bookstoreproject.domain.BookRepository;
import hh.palvelinohjelmointi.bookstoreproject.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// etusivu
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getBook(Model model) {
		return "bookstore";
	}
	
	// login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// kaikki kirjat listalla
	@RequestMapping(value="/booklist")
	public String listBook(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//REST kaikki kirjat
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//REST hakee kirjan id:n perusteella
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	
	// lisää uuden kirjan
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	// tallentaa uuden kirjan/päivittää muuttuneet tiedot
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	// deletoi kirjan id:n perusteella
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
}
