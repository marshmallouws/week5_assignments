package entities;

import entities.Teacher;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-20T23:11:09")
@StaticMetamodel(Semester.class)
public class Semester_ { 

    public static volatile SingularAttribute<Semester, String> name;
    public static volatile SingularAttribute<Semester, String> description;
    public static volatile SingularAttribute<Semester, Long> id;
    public static volatile CollectionAttribute<Semester, Teacher> teacherCollection;

}