package hh.palvelinohjelmointi.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.bookstoreproject.domain.Category;
import hh.palvelinohjelmointi.bookstoreproject.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
		
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void findByCategoryname() {
		List<Category> books = crepository.findByName("Fantasia");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getCategoryId()).isEqualTo(3);
	}

}
