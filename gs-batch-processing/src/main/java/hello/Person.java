package hello;

public class Person {

	private String lastName;
	private String firstName;
	public String getLastName() {
		return lastName;
	}
	public Person() {
		
	}
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName  = lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}
}
