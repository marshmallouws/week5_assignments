## One-to-one unidirectional: @OneToOne
The reference is made in the class Customer which makes it the owning side
```java
  @OneToOne
  private Address address;
```
This means that Customer is the owning class and since it is a unidirectional relation, Address doesn’t get a reference to Customer. In the database, the table CUSTOMER has a foreign key that references the ADDRESS table. In an OO-language, the reference is created by making an object reference whereas there’s used foreign keys in relational databases.

## One-to-one bidirectional: @OneToOne /  @OneToOne(mappedBy = "address")
In this example, there’s a reference in both entity-classes. Customer is still the owning side and looks like this:
```java
  public class Customer {
    ...
    @OneToOne
    private Address address;
    ...
  }
	
  public class Address {
    ...
    @OneToOne(mappedBy = "address")
    private Customer customer;
  }
```
Bidirectional relationship means that both classes have a reference so both classes would point to each other. In the database, however, 
there is no difference than from the previous exercise; this is because you can still easily find a one-to-one relation without having 
reference in both tables.

## One-to-many unidirectional: @OneToMany
To do this, the object reference is replaced with a list of object references but the only change in the Java code is the 
@OneToMany annotation in the Customer class. The database gets an extra table with the fields: Customer_id and addresses_id. To avoid 
the extra table (which is normally only used for many to many relationships) the annotation @JoinColumn is added before @OneToMany 
in Customer. When doing so, the ADDRESS-table gets a foreign key that references CUSTOMER in the database.

## One-to-Many bidirectional: @OneToMany / @OneToMany(mappedBy = “Customer”)
Note that the owning class is now Address and therefore the mapped-by is located in the Customer class. The Address class has the @ManyToOne annotation. The foreign key is still located in ADDRESS-table in the database.

## Many-to-many: bidirectional @ManyToMany
A many to many relation in a relational database would be split up into a separate table with two foreign keys as a composite key. In Java, a many-to-many relation can be implemented by having a list of objects in both classes.
When generating the tables, there are three where the two entities only have the fields that are defined in the entity class. The third table has two foreign keys that points to the other two tables – the two foreign keys are part of a composite key.
