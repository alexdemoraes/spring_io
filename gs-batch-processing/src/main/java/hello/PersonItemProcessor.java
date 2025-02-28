package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	@Override
	public Person process(final Person person) throws Exception {
		final String firstName = person.getFirstName();
		final String lastName = person.getLastName();
		
		final Person transformedPerson = new Person(firstName, lastName);
		
		log.info("Converting (" + person + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}

}
