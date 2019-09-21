/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Annika
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer createCustomer(Customer customer) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    public Customer findCustomer(int id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Customer> getCustomers() {
        EntityManager em = getEntityManager();
        
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        Customer c = new Customer("Annika", "a@mail.dk");
        facade.createCustomer(c);
        Customer ca  =facade.findCustomer(1);
        System.out.println(ca.getName());
        List<Customer> cus = facade.getCustomers();
        for(Customer ci: cus) {
            System.out.println(ci.getName());
        }
    }
    
    

}
