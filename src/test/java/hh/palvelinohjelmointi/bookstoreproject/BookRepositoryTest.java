package hh.palvelinohjelmointi.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.bookstoreproject.domain.Book;
import hh.palvelinohjelmointi.bookstoreproject.domain.BookRepository;
import hh.palvelinohjelmointi.bookstoreproject.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
		
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitle() {
		List<Book> books = repository.findByTitle("Taru sormusten herrasta");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Tolkien");
	}
	
	@Test
    public void addNewBook() {
    	Book book = new Book("Narnian tarinat", "Lewis", 2009, "951120730x", 24.50, new Category("Fantasia"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }   
	
	@Test
    public void deleteBook() {
    	Book book = new Book("Narnian tarinat", "Lewis", 2009, "951120730x", 24.50, new Category("Fantasia"));
    	repository.save(book);
    
    	repository.deleteById(book.getId());
    	Optional<Book> book1 = repository.findById(book.getId());
    	assertThat(book1).isEmpty();
    }   
}
