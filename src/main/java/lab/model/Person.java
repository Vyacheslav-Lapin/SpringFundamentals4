package lab.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

public class Person {
    public String name;
    public Company company;
}

class IvanovPerson extends Person {
    private static Person ivanovPerson = new Person();
    public static Person create() {
        ivanovPerson.name = "Иван Иванов";
        ivanovPerson.company = LuxoftCompany.create();
        return ivanovPerson;
    }
}

class Company {
    public String name;
}

class LuxoftCompany extends Company {
    private static Company luxoftCompany = new Company();
    public LuxoftCompany() {
        luxoftCompany.name = "Luxoft";
    }
    public static Company create() {
        return luxoftCompany;
    }
}

@Data
class BankApplication {
    @Autowired
    private CompanyReport companyReport;
}
