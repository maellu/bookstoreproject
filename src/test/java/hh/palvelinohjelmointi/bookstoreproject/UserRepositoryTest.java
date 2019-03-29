package hh.palvelinohjelmointi.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.bookstoreproject.domain.User;
import hh.palvelinohjelmointi.bookstoreproject.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
		
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByUsername() {
		User testUser = urepository.findByUsername("user");
		assertThat(testUser.getId()).isEqualTo(1);
	}

}