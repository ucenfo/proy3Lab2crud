package ac.cr.cenfotec.proy3lab2crud.repository;

import ac.cr.cenfotec.proy3lab2crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("select p from Producto p where p.nombre like %?1%")
    List<Producto> findProductoByName(String nombre);
}
