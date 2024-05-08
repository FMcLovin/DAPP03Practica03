package org.uv.DAPP03Practica03;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class EjemploController {

    @GetMapping(path = "/saludo", produces = {"application/json"})
    public String saludo() {
        return "hola mundo";
    }

    @GetMapping(path = "/data/{id}", produces = {"application/json"})
    public Data getData(@PathVariable(value = "id") int id) {
        Data data = null;
        if (id == 1) {
            data = new Data("01", "jabon", "Doble");
        }
        return data;
    }

    @Autowired
    private RepositoryEmpleado repositoryEmpleado;

    @GetMapping(path = "/empleado/{id}", produces = {"application/json"})
    public Empleado getEmpleado(@PathVariable("id") Long id) {
        Optional<Empleado> res = repositoryEmpleado.findById(id);
        if (res.isPresent()) {
            return res.get();
        } else {
            return null;
        }
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Iterable<Empleado> getAllEmpleados() {
        return repositoryEmpleado.findAll();
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        Empleado createdEmpleado = repositoryEmpleado.save(empleado);
        return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") Long id, @RequestBody Empleado empleado) {
        Optional<Empleado> empleadoOptional = repositoryEmpleado.findById(id);
        if (empleadoOptional.isPresent()) {
            Empleado existingEmpleado = empleadoOptional.get();
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setDireccion(empleado.getDireccion());
            existingEmpleado.setTelefono(empleado.getTelefono());
            Empleado updatedEmpleado = repositoryEmpleado.save(existingEmpleado);
            return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable("id") Long id) {
        Optional<Empleado> empleadoOptional = repositoryEmpleado.findById(id);
        if (empleadoOptional.isPresent()) {
            repositoryEmpleado.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
