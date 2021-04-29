package fr.wawa.products.resources;

import fr.wawa.products.dtos.CreateProductDto;
import fr.wawa.products.entities.Product;
import fr.wawa.products.exceptions.NoEnoughProductException;
import fr.wawa.products.exceptions.ProductNotFoundException;
import fr.wawa.products.repositories.HibernateProductRepository;
import fr.wawa.products.repositories.MemoryProductRepository;
import fr.wawa.products.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products/")
public class ProductResource {
    private final ProductRepository productRepository;

    @Inject
    public ProductResource(MemoryProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     *
     * @return The list of products
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(productRepository.getAll()).build();
    }

    /**
     * Add a product
     *
     * @param productDto The product to add
     * @return The added product with its auto generated id
     */
    @POST
    public Response add(CreateProductDto productDto) {
        Product product = productRepository.add(productDto);
        return Response.ok(product).build();
    }

    /**
     * Remove all products
     *
     * @return The list of all deleted products
     */
    @DELETE
    public Response deleteAll() {
        List<Product> products = productRepository.deleteAll();
        return Response.ok(products).build();
    }

    /**
     * Get a product by its id
     *
     * @param id The product's id to find
     * @return The product if found, otherwise a 404 error
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        try {
            return Response.ok(productRepository.getById(id)).build();
        } catch (ProductNotFoundException productNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Add a product with a specified id
     *
     * @param id               The product's id
     * @param createProductDto The product's informations
     * @return The product if added, a 409 error if a product with this id already exists
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWithId(@PathParam("id") long id, CreateProductDto createProductDto) {
        try {
            productRepository.addWithId(id, createProductDto);
            return Response.ok(productRepository.getById(id)).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**
     * Delete a product by it's id
     *
     * @param id The product's id to delete
     * @return The deleted product if found, a 404 error otherwise
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        try {
            Product product = productRepository.delete(id);
            return Response.ok(product).build();
        } catch (ProductNotFoundException productNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * The product to find by a name
     *
     * @param name The product's name
     * @return The product if found, a 404 error otherwise
     */
    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("name") String name) {
        try {
            return Response.ok(productRepository.findByName(name)).build();
        } catch (ProductNotFoundException productNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Buy a product by an amount
     *
     * @param id       The product to buy
     * @param quantity The quantity to buy
     * @return The product with updated quantity
     */
    @POST
    @Path("buy/{id}/{quantity}")
    public Response buy(@PathParam("id") long id, @PathParam("quantity") int quantity) {
        try {
            Product product = productRepository.getById(id);
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                Product updatedProduct = productRepository.save(product);
                return Response.ok(updatedProduct).build();
            } else throw new NoEnoughProductException();
        } catch (ProductNotFoundException productNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (NoEnoughProductException noProductLeftException) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}