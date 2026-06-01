package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="compras_productos")
public class CompraProducto {
   @EmbeddedId
   private CompraProductoPK id;

   @ManyToOne
   @JoinColumn(name = "id_compra", insertable = false, updatable = false)
   private Compra compra;


   private Integer cantidad;
   private Double total;
   private Boolean estado;


}
