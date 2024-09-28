package com.example.D288.bootstrap;

        import com.example.D288.dao.CustomerRepository;
        import com.example.D288.dao.DivisionRepository;
        import com.example.D288.entities.Customer;
        import com.example.D288.entities.Division;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;

        import java.util.Arrays;
        import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) {

        Division division = divisionRepository.findById(2L).orElse(null);

        if (division == null) {
            throw new IllegalStateException("Division with ID 2L not found in the database.");
        }


        Customer nathan = new Customer("Nathan", "Meagher", "123 Red Willow Road", "12019", "(250)3456774");
        nathan.setDivision(division);

        Customer liv = new Customer("Liv", "Bryson", "111 Starcrest Lane", "22268", "(212)333453");
        liv.setDivision(division);

        Customer amy = new Customer("Amy", "Feather", "3444 Summerville Drive", "51337", "(214)5645454");
        amy.setDivision(division);

        Customer rick = new Customer("Rick", "Anderson", "13464 Turnpike Ave", "38483", "(444)6753933");
        rick.setDivision(division);

        Customer jasper = new Customer("Jasper", "Bark", "Woof Hollow Road", "22233", "(100)2340022");
        jasper.setDivision(division);


        customerRepository.saveAll(Arrays.asList(nathan, liv, amy, rick, jasper));




        List<Customer> savedCustomers = customerRepository.findAll();
        savedCustomers.forEach(System.out::println);
    }
}

