import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class SimpleAppTest {
	
	protected static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

	private AbstractApplicationContext context;

	private Supplier<Person> personSupplier;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				APPLICATION_CONTEXT_XML_FILE_NAME);
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
		Person person = context.getBean(Person.class);
		assertEquals(personSupplier.get(), person);
        personSupplier.get().sayHello(personSupplier.get());
	}
}
