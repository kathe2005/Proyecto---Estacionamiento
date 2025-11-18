package com.backend.backend.controller;

import com.backend.backend.model.Cliente;
import com.backend.backend.service.ClienteService;
import com.backend.backend.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Indica que es un Controller REST
@RequestMapping("/api/clientes") //URL base: localhost:8080/api/clientes
public class ClienteController {

    ////Relación con los metodos (Service)
    @Autowired
    private ClienteService clienteService;

    //POST /api/clientes/registrar
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente)
    {
        try {

            //Llama al service, que ejecuta todas las validaciones y guarda el cliente
            Cliente nuevoCliente = clienteService.registrarCliente(cliente);

            //Si es exitoso, devuelve el cliente registrado con estado 201 (Creado)
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);

        }catch (RuntimeException e)
        {
            //Capturamos cualquier error de validación lanzado por el service
            //Devolvemos el mensaje de error con el estado HTTP 400 (Bad Resquest)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
