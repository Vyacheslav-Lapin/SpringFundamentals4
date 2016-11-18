import lab.model.Country;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SimpleAppTest {
	
	protected static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

	private AbstractApplicationContext context;

	private UsualPerson expectedPerson;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				APPLICATION_CONTEXT_XML_FILE_NAME);
		expectedPerson = getExpectedPerson();
	}

	@Test
	public void testInitPerson() {
		UsualPerson person = (UsualPerson) context.getBean("person");
//		FYI: Another way to achieve the bean
//		person = context.getBean(UsualPerson.class);
		assertEquals(expectedPerson, person);
		System.out.println(person);
	}

	private UsualPerson getExpectedPerson() {
		return UsualPerson
				.builder()
				.age(35)
				.height(1.78F)
				.isProgrammer(true)
				.name("John Smith")
				.country(new Country()
						.setId(1)
						.setName("Russia")
						.setCodeName("RU"))
				.contacts(Arrays.asList("asd@asd.ru", "+7-234-456-67-89"))
				.build();
	}
}
