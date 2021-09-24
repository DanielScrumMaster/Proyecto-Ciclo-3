package com.dmaktech.tienda.productos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Collection<Producto> getProductos() {
        return productoService.getProductos();
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long codigo) {
        Producto producto = productoService.getProducto(codigo);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public void guardarProducto(@RequestParam("productos") MultipartFile productosCSV) {
        if(!productosCSV.getContentType().equals("text/csv"))
                throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Sólo se aceptan archivos de tipo CSV");

        productoService.guardarProducto(productosCSV);
    }
}
