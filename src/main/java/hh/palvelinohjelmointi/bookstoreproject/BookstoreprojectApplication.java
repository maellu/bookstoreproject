package hh.palvelinohjelmointi.bookstoreproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.palvelinohjelmointi.bookstoreproject.domain.Book;
import hh.palvelinohjelmointi.bookstoreproject.domain.BookRepository;
import hh.palvelinohjelmointi.bookstoreproject.domain.Category;
import hh.palvelinohjelmointi.bookstoreproject.domain.CategoryRepository;
import hh.palvelinohjelmointi.bookstoreproject.domain.User;
import hh.palvelinohjelmointi.bookstoreproject.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreprojectApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreprojectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreprojectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of categories");
			crepository.save(new Category("Tiede"));
			crepository.save(new Category("Draama"));
			crepository.save(new Category("Fantasia"));
			
			log.info("save a couple of books");
			repository.save(new Book("Taru sormusten herrasta", "Tolkien", 2007, "9789510333372", 42.50, crepository.findByName("Fantasia").get(0)));
			repository.save(new Book("Hobitti", "Tolkien", 2017, "9789510427927", 22.50, crepository.findByName("Fantasia").get(0)));
			
			User user1 = new User("user", "$2a$10$nmc5t/ZD3XTtORCbukoZ1uw42FXwq5Z9vX.MKqepNnKx0pJTUeNrS", "martta@martta.fi", "USER");
			User user2 = new User("admin", "$2a$10$WUv9iZz5lG6Tk1Eg/LuggeU3C.2ZGRECoBh6em7nwstjMx9x17LeC", "pertti@pertti.fi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}

