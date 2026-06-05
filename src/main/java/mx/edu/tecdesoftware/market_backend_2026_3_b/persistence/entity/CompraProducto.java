package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="compras_productos")
public class CompraProducto {
   @EmbeddedId
   private CompraProductoPK id;

   @ManyToOne
   @JoinColumn(name = "id_compra", insertable = false, updatable = false)
   private Integer cantidad;
   private Double total;
   private Boolean estado;

   public CompraProductoPK getId() {
      return id;
   }


   public void setId(CompraProductoPK id) {
      this.id = id;
   }

   public Integer getCantidad() {
      return cantidad;
   }

   public void setCantidad(Integer cantidad) {
      this.cantidad = cantidad;
   }

   public Double getTotal() {
      return total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

   public Boolean getEstado() {
      return estado;
   }

   public void setEstado(Boolean estado) {
      this.estado = estado;
   }
}
