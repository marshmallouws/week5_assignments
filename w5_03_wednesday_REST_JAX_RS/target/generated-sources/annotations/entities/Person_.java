package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-19T23:22:58")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> firstname;
    public static volatile SingularAttribute<Person, String> phone;
    public static volatile SingularAttribute<Person, Date> created;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, Date> lastEdited;
    public static volatile SingularAttribute<Person, String> lastname;

}