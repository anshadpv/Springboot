package com.example.springbootbackend;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Anshad");
		employee.setLastName("PV");
		employee.setEmail("anshadpv4@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("Sahaf");
		employee1.setLastName("Hussain");
		employee1.setEmail("sahafhussain@gmail.com");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Ladeed");
		employee2.setLastName("cet");
		employee2.setEmail("ladeedcet@gmail.com");
		employeeRepository.save(employee2);

	}
}
