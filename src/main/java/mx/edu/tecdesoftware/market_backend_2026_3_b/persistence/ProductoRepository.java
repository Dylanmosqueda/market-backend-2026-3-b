package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Le da acceso a la BD
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    // SELECT * FROM productos
    public List<Product> getAll(){
        // Se castea Iterable a lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    // Obtener productos por categoria
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    // Obtener productos escasos (Optimizado de forma segura usando .map)
    public Optional<List<Product>> getScarceProducts(int quantity){
        Optional<List<Producto>> productos =
                productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    // Obtener un producto dado el ID
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId)
                .map(producto -> productMapper.toProduct(producto));
    }

    // Guardar un producto
    // Guardar un producto
    @Override
    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);

        // 1. Guardamos temporalmente en memoria la categoría que enviaste en tu JSON
        mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Category tempCategory = product.getCategory();

        // 2. Limpiamos la relación de la entidad para evitar el error 500 de Hibernate
        producto.setCategoria(null);
        producto.setIdProducto(null); // Dejamos que PostgreSQL autogenere el ID de forma consecutiva

        // 3. Guardamos el producto en la base de datos
        Producto savedProducto = productoCrudRepository.save(producto);

        // 4. Mapeamos el producto guardado de regreso a nuestro objeto de dominio
        Product savedProduct = productMapper.toProduct(savedProducto);

        // 5. Le volvemos a inyectar la categoría que guardamos en memoria para que la respuesta esté completa
        if (tempCategory != null) {
            savedProduct.setCategory(tempCategory);
        }

        return savedProduct;
    }

    // Eliminar un producto por ID
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}