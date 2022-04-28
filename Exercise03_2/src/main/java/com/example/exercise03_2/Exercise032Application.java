package com.example.exercise03_2;


import com.example.exercise03_2.domain.BidirectionalManyToMany.Course;
import com.example.exercise03_2.domain.BidirectionalManyToMany.Student;
import com.example.exercise03_2.domain.BidirectionalManyToOne.Office;
import com.example.exercise03_2.domain.BidirectionalOneToMany.Department;
import com.example.exercise03_2.domain.BidirectionalOneToMany.Employee;
import com.example.exercise03_2.domain.OptionalUniManyToOne.Book;
import com.example.exercise03_2.domain.OptionalUniManyToOne.Publisher;

import com.example.exercise03_2.domain.UnidirectionalOneToMany.Customer;
import com.example.exercise03_2.domain.UnidirectionalOneToMany.Reservation;
import com.example.exercise03_2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class Exercise032Application {

    private static final SessionFactory sessionFactory;

    static {
        // If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(
                Employee.class,
                Department.class,
                Book.class,
                Publisher.class,
                Student.class,
                Course.class,
                Customer.class,
                Reservation.class,
                com.example.exercise03_2.domain.UnidirectionalManyToOne.Book.class,
                com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation.class,
                com.example.exercise03_2.domain.BidirectionalManyToOne.Employee.class,
                Office.class
        ));
    }

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Department department = new Department("Compro");
            session.persist(department);

            Employee employee1 = new Employee("Sauravi", department);
            employee1.setDepartment(department);
            session.persist(employee1);
            Employee employee2 = new Employee("Manju", department);
            employee2.setDepartment(department);
            session.persist(employee2);

            Publisher publisher = new Publisher("Publisher 1");
            session.persist(publisher);

            Book book1 = new Book("Test with publisher", "Aishwarya Dhakal");
            book1.setPublisher(publisher);
            session.persist(book1);
            Book book2 = new Book("Test without publisher", "Suman Acharya");
            session.persist(book2);
            Book book3 = new Book("Test with publisher", "Test");
            book2.setPublisher(publisher);
            session.persist(book3);

            Course course1 = new Course("SWA");
            Course course2 = new Course("WAP");
            Course course3 = new Course("EA");
            Student student1 = new Student("Ram");
            Student student2 = new Student("John");
            Student student3 = new Student("Haryy");

            student1.setCourse(Arrays.asList(course1));
            student2.setCourse(Arrays.asList(course1, course2));
            student3.setCourse(Arrays.asList(course1, course2, course3));

            session.persist(course1);
            session.persist(course2);
            session.persist(course3);
            session.persist(student1);
            session.persist(student2);
            session.persist(student3);

            Customer customer = new Customer("Rabin");

            Reservation reservation1 = new Reservation(LocalDate.now());
            session.persist(reservation1);
            Reservation reservation2 = new Reservation(LocalDate.now());
            session.persist(reservation2);
            customer.setReservation(Arrays.asList(reservation1, reservation2));
            session.persist(customer);

            com.example.exercise03_2.domain.UnidirectionalManyToOne.Book unibook1 = new com.example.exercise03_2.domain.UnidirectionalManyToOne.Book("Book with publisher", "Jeo High");
            session.persist(unibook1);

            com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation uniM21reservation1 = new com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation(LocalDate.now());
            uniM21reservation1.setBook(unibook1);
            session.persist(uniM21reservation1);
            com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation uniM21reservation2 = new  com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation(LocalDate.now());
            uniM21reservation2.setBook(unibook1);
            session.persist(uniM21reservation2);

            Office office1 = new Office("Hello World");
            Office office2 = new Office("Test");

            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee emp1 = new com.example.exercise03_2.domain.BidirectionalManyToOne.Employee("Test One", office1);
            session.persist(emp1);
            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee emp2 = new  com.example.exercise03_2.domain.BidirectionalManyToOne.Employee("Test Two", office1);
            session.persist(emp2);
            office1.setEmployees(Arrays.asList(emp1, emp2));
            session.persist(office1);

            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee emp3 = new com.example.exercise03_2.domain.BidirectionalManyToOne.Employee("Test Three", office2);
            session.persist(emp3);
            office1.setEmployees(Arrays.asList(emp3));
            session.persist(office2);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
