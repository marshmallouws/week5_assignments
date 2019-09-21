/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Semester;
import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Annika
 */
public class StudentFacade {
    private static StudentFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private StudentFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StudentFacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            return (long) em.createQuery("SELECT COUNT(s) FROM Student s").getSingleResult();  
        } finally {
            em.close();
        }
    }
    
    public List<Student> getStudentsByName() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByFirstname").setParameter("firstname", "Anders").getResultList();
        } finally {
            em.close();
        }
    }
    
    public Student addStudent(Student s) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }
    
    // Doesn't work - couldn't figure out how to do it, so here's my thoughts
    public void updateStudentsSemester(Long studid, Long semid) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Student> query
                    = em.createQuery("UPDATE Student s SET s.semester.id = :semid WHERE s.id = :studid", Student.class);
            query.setParameter("semid", semid);
            query.setParameter("studid", studid);
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<Student> findStudentByLastname() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByLastname").setParameter("lastname", "And").getResultList();
        } finally {
            em.close();
        }
    }
  
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        StudentFacade facade = StudentFacade.getStudentFacade(emf);
        
        System.out.println(facade.getStudentCount());
        System.out.println(facade.getStudentsByName().size());
        /*Student s = new Student();
        s.setFirstname("Peter");
        s.setLastname("Petersen");
        System.out.println(facade.addStudent(s));*/
        //facade.updateStudentsSemester(7L, 3L);
        System.out.println(facade.findStudentByLastname().size());
        
    }
}
