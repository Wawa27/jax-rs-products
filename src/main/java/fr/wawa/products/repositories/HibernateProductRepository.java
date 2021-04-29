package fr.wawa.products.repositories;

import fr.wawa.products.dtos.CreateProductDto;
import fr.wawa.products.entities.Product;
import fr.wawa.products.exceptions.ProductNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateProductRepository implements ProductRepository {
    private final EntityManager entityManager;

    public HibernateProductRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("product").createEntityManager();
    }

    public List<Product> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);

        TypedQuery<Product> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Product getById(long id) {
        return entityManager.getReference(Product.class, id);
    }

    @Override
    public Product add(CreateProductDto createProductDto) {
        Product product = createProductDto.toProduct();
        entityManager.persist(product);
        return product;
    }

    @Override
    public Product addWithId(long id, CreateProductDto createProductDto) {
        Product product = createProductDto.toProduct();
        entityManager.persist(product);
        return product;
    }

    @Override
    public List<Product> deleteAll() {
        List<Product> products = entityManager.createQuery("FROM product").getResultList();
        entityManager.createQuery("DELETE from product");
        return products;
    }

    @Override
    public Product delete(long id) throws ProductNotFoundException {
        Product product = entityManager.find(Product.class, id);
        Query query = entityManager.createQuery("DELETE * FROM product WHERE id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return product;
    }

    @Override
    public Product findByName(String name) throws ProductNotFoundException {
        Query query = entityManager.createQuery("SELECT * FROM product " +
                "WHERE name LIKE ?" +
                "OR detail LIKE ?" +
                "OR info LIKE ?");
        query.setParameter(1, name);
        query.setParameter(2, name);
        query.setParameter(3, name);
        Product product = (Product) query.getSingleResult();
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException();
    }

    @Override
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }
}
