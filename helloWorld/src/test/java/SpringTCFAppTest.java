import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringTCFAppTest {
	
	@Autowired
	private Person person;

	private Supplier<Person> personSupplier;

	@Before
	public void setUp() throws Exception {
		personSupplier = UsualPerson.builder()
				.age(35)
				.height(1.78F)
				.isProgrammer(true)
				.name("John Smith")
				.country(Country.builder()
						.id(1)
						.name("Russia")
						.codeName("RU")
						.build())
				.contacts(Arrays.asList("asd@asd.ru", "+7-234-456-67-89"))::build;
	}

	@Test
	public void testInitPerson() {
		assertEquals(personSupplier.get(), person);
	}
}
