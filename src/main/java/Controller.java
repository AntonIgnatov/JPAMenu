import net.biggmir.venzor.DishEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final EntityManager em;

    public Controller(EntityManager em) {
        this.em = em;
    }

    public void init() {
        List<DishEntity> tempList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DishEntity temp = new DishEntity("name" + i, 20 * i, (int) (Math.random() * 1000));
            if (i % 2 == 0) {
                temp.setDiscount(30);
            }
            tempList.add(temp);
        }
        this.addList(tempList);
    }

    public void add(DishEntity dishEntity) {
        em.clear();
        em.getTransaction().begin();
        try {
            em.persist(dishEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void addList(List<DishEntity> list) {
        em.clear();
        em.getTransaction().begin();
        try {
            for (DishEntity temp : list) {
                em.persist(temp);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void getBetween(double min, double max) {
        try {
            Query query = em.createQuery("SELECT c FROM DishEntity c WHERE c.price < :max AND c.price > :min", DishEntity.class);
            query.setParameter("max", max);
            query.setParameter("min", min);
            List<DishEntity> list = query.getResultList();
            if (list.isEmpty()) {
                System.out.println("no sach dishes");
                return;
            }
            for (DishEntity temp : list) {
                System.out.println(temp.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getOneKilo() {
        try {
            Query query = em.createQuery("SELECT c FROM DishEntity c", DishEntity.class);
            List<DishEntity> listAll = query.getResultList();
            double sum = 0;
            List<DishEntity> listFinal = new ArrayList<>();
            for (DishEntity dish : listAll) {
                if (sum + dish.getWeight() < 1000) {
                    sum += dish.getWeight();
                    listFinal.add(dish);
                }
            }
            if (listFinal.isEmpty()) {
                System.out.println("Dishes are too heavy))");
            }
            for (DishEntity d : listFinal) {
                System.out.println(d.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
