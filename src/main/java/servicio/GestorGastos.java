package servicio;

import modelo.Gasto;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GestorGastos {

    public void agregarGasto(Gasto gasto) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(gasto);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        }

    }

    public List<Gasto> obtenerGastos() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM Gasto", Gasto.class).list();

        }

    }

    public void eliminarGastoSeleccionado(int indice) {

        List<Gasto> gastos = obtenerGastos();

        if (indice >= 0 && indice < gastos.size()) {

            Transaction transaction = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                transaction = session.beginTransaction();

                session.remove(session.merge(gastos.get(indice)));

                transaction.commit();

            } catch (Exception e) {

                if (transaction != null) {
                    transaction.rollback();
                }

                e.printStackTrace();

            }

        }

    }

    public double calcularTotal() {

        List<Gasto> gastos = obtenerGastos();

        double total = 0;

        for (Gasto gasto : gastos) {

            total += gasto.getMonto();

        }

        return total;

    }

}