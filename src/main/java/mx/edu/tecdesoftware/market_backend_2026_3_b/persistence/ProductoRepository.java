package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import aj.org.objectweb.asm.Opcodes;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import java.util.List;
import java.util.Optional;
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){

      return (List<Producto>)  productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria ){
        return productoCrudRepository.findByCantidadOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscaso(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    }
    public Optional<Producto> getProductoById(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }
    public Producto addProducto (Producto producto){
        return productoCrudRepository.save(producto);
    }
    public void deleteProductoById(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
