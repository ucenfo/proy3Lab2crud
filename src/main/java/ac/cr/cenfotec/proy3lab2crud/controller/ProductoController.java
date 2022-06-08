package ac.cr.cenfotec.proy3lab2crud.controller;

import ac.cr.cenfotec.proy3lab2crud.entity.Producto;
import ac.cr.cenfotec.proy3lab2crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;

    @PostMapping("producto")
    public String addProducto(@RequestBody Producto producto){
        productoRepository.save(producto);
        return "Producto guardado satisfactoriamente";
    }
    @GetMapping("productos")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    @GetMapping("producto/{id}")
    public Optional<Producto> getProducto(@PathVariable(value = "id") Integer id){
        return productoRepository.findById(id);
    }
    @PutMapping("producto/{id}")
    public ResponseEntity<Producto> modicarProducto(@PathVariable(value = "id") Integer id,
                                          @RequestBody Producto productoPorModificar) {
        Optional<Producto> producto = productoRepository.findById(id);
        producto.get().setNombre(productoPorModificar.getNombre());
        producto.get().setDescripcion(productoPorModificar.getDescripcion());
        producto.get().setCantidad(productoPorModificar.getCantidad());
        producto.get().setPrecio(productoPorModificar.getPrecio());
        Producto productoModificado = productoRepository.save(producto.get());
        return ResponseEntity.ok(productoModificado);
    }
    @DeleteMapping("producto/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable(value = "id") Integer id) {
        productoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("nombreProducto/{nombre}")
    public List<Producto> getProductoByNombre(@PathVariable(value = "nombre") String nombre){
        return productoRepository.findProductoByName(nombre);
    }
}
