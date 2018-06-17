

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {


    public static void main(final String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        try {
           Controller controller = new Controller(em);
           controller.init();
           controller.getBetween(40, 150);
           System.out.println("for 1kg");
           controller.getOneKilo();

        }finally {
            em.close();
            emf.close();
        }

    }

}
