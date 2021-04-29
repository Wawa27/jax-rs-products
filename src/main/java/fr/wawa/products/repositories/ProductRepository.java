package fr.wawa.products.repositories;

import fr.wawa.products.dtos.CreateProductDto;
import fr.wawa.products.entities.Product;
import fr.wawa.products.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();

    Product getById(long id) throws ProductNotFoundException;

    Product add(CreateProductDto createProductDto);

    Product addWithId(long id, CreateProductDto createProductDto) throws Exception;

    List<Product> deleteAll();

    Product delete(long id) throws ProductNotFoundException;

    Product findByName(String name) throws ProductNotFoundException;

    Product save(Product product);
}
