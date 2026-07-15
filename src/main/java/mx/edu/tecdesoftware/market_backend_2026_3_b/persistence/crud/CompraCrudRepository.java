package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
    default Optional<List<Compra>> findByIdCliente() {
        return findByIdCliente(null);
    }

    Optional<List<Compra>> findByIdCliente(String idCliente);
}