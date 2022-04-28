package com.example.exercise03_2;


import com.example.exercise03_2.domain.BidirectionalManyToMany.Course;
import com.example.exercise03_2.domain.BidirectionalManyToMany.Student;
import com.example.exercise03_2.domain.BidirectionalManyToOne.Office;
import com.example.exercise03_2.domain.BidirectionalOneToMany.Department;
import com.example.exercise03_2.domain.BidirectionalOneToMany.Employee;
import com.example.exercise03_2.domain.OptionalUnidirectionalManyToOne.Book;
import com.example.exercise03_2.domain.OptionalUnidirectionalManyToOne.Publisher;

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

    private static final SessionFactory  sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(
            Student.class,
            Course.class,
            Customer.class,
            Reservation.class,
            Office.class,
            Department.class,
            Book.class,
            Publisher.class,
            Employee.class,
            com.example.exercise03_2.domain.UnidirectionalManyToOne.Book.class,
            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee.class,
            com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation.class));


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

            Book book1 = new Book("Book1", "Aishwarya Dhakal");
            book1.setPublisher(publisher);
            session.persist(book1);
            Book book2 = new Book("Book2", "Test");
            book2.setPublisher(publisher);
            session.persist(book2);

            Student student1 = new Student("Ram");
            Student student2 = new Student("John");
            Student student3 = new Student("Haryy");

            Course course1 = new Course("SWA");
            Course course2 = new Course("WAP");
            Course course3 = new Course("EA");

            session.persist(course1);
            session.persist(course2);
            session.persist(course3);

            student1.setCourse(Arrays.asList(course1));
            student2.setCourse(Arrays.asList(course1, course2));
            student3.setCourse(Arrays.asList(course1, course2, course3));

            session.persist(student1);
            session.persist(student2);
            session.persist(student3);

            Customer customer = new Customer("Rabin");

            Reservation reservation1 = new Reservation(LocalDate.now());
            session.persist(reservation1);
            customer.setReservation(Arrays.asList(reservation1));
            session.persist(customer);

            com.example.exercise03_2.domain.UnidirectionalManyToOne.Book unibook1 = new com.example.exercise03_2.domain
                    .UnidirectionalManyToOne.Book("Book with publisher", "Jeo High");
            session.persist(unibook1);

            com.example.exercise03_2.domain.UnidirectionalManyToOne.Reservation uniM21reservation1 = new com.example.
                    exercise03_2.domain.UnidirectionalManyToOne.Reservation(LocalDate.now());
            uniM21reservation1.setBook(unibook1);
            session.persist(uniM21reservation1);

            Office office1 = new Office("Office 1 Test");

            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee emp1 = new com.example.exercise03_2.domain
                    .BidirectionalManyToOne.Employee("Test One", office1);
            session.persist(emp1);
            com.example.exercise03_2.domain.BidirectionalManyToOne.Employee emp2 = new  com.example.exercise03_2.domain
                    .BidirectionalManyToOne.Employee("Test Two", office1);
            session.persist(emp2);
            office1.setEmployees(Arrays.asList(emp1, emp2));
            session.persist(office1);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        sessionFactory.close();
    }
}
