package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsualPerson implements Person {

    @Id
    @Column
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @Resource
    private Country country;

    private int age;
    private float height;
    private boolean isProgrammer;

    @Resource
    private List<String> contacts;

    @Override
    public void sayHello(Person person) {
        System.out.println(
                Stream.of(UsualPerson.class.getConstructors()[0].getParameters())
                .map(java.lang.reflect.Parameter::getName)
                .collect(Collectors.joining(", "))
        );
    }
}