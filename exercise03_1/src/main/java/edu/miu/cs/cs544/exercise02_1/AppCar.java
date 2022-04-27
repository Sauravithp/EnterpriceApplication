package edu.miu.cs.cs544.exercise02_1;

import java.util.Arrays;
import java.util.List;

import edu.miu.cs.cs544.exercise02_1.domain.Car;
import edu.miu.cs.cs544.exercise02_1.domain.Owner;
import edu.miu.cs.cs544.exercise02_1.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;	

public class AppCar {

	private static final SessionFactory sessionFactory;

    static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Car.class,Owner.class));
	}

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Owner owner=new Owner();
            owner.setName("Sauravi Thapa");
            owner.setAddress("Fairfield");

            // Create new instance of Car and set values in it
            Car car1 = new Car("BMW", "SDA231", 30221.00);
            car1.setOwner(owner);
            // save the car
            session.persist(car1);
            // Create new instance of Car and set values in it
            Car car2 = new Car("Mercedes", "HOO100", 4088.00);
            car2.setOwner(owner);
            // save the car
            session.persist(car2);

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

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all cars
            @SuppressWarnings("unchecked")
            List<Car> carList = session.createQuery("FROM Car").list();
            for (Car car : carList) {
                System.out.println("brand= " + car.getBrand() + ", year= "
                        + car.getYear() + ", price= " + car.getPrice() +",owner= "+car.getOwner().toString());
            }
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
