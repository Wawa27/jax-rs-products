package fr.wawa.products.repositories;

import fr.wawa.products.dtos.CreateProductDto;
import fr.wawa.products.entities.Product;
import fr.wawa.products.exceptions.ProductNotFoundException;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * In-memory repository for products
 */
@Singleton
public class MemoryProductRepository implements ProductRepository {
    private final HashMap<Long, Product> products;

    public MemoryProductRepository() {
        this.products = new HashMap<>();
        products.put(1L, new Product(1L, 5, "Rubik's cube", BigDecimal.valueOf(10), "", "", ""));
        products.put(2L, new Product(2L, 3, "Rubik's cube", BigDecimal.valueOf(10), "", "", ""));
        products.put(3L, new Product(3L, 1, "Rubik's cube", BigDecimal.valueOf(10), "", "", ""));
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public Product getById(long id) throws ProductNotFoundException {
        return products.values().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product delete(long id) throws ProductNotFoundException {
        Product product = getById(id);
        products.remove(product);
        return product;
    }

    @Override
    public Product findByName(String name) throws ProductNotFoundException {
        return products.values().stream()
                .filter(product -> product.getName().contains(name))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        return this.products.put(product.getId(), product);
    }

    @Override
    public Product add(CreateProductDto productDto) {
        Long id = products.size() > 0 ?
                products.values().stream().max((o1, o2) -> (int) (o1.getId() - o2.getId())).get().getId() + 1
                : 0;
        Product product = new Product(
                id,
                productDto.getQuantity(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDetail(),
                productDto.getInfo(),
                productDto.getImage()
        );
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product addWithId(long id, CreateProductDto createProductDto) throws Exception {
        if (this.products.get(id) != null) {
            throw new Exception("Product already exists");
        }
        Product product = createProductDto.toProduct();
        product.setId(id);
        this.products.put(id, product);
        return product;
    }

    @Override
    public List<Product> deleteAll() {
        ArrayList<Product> result = new ArrayList<>(this.products.values());
        this.products.clear();
        return result;
    }
}
