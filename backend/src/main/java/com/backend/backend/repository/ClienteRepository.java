package com.backend.backend.repository;

import com.backend.backend.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //Spring lo reconozca para el acceso a la base de datos
public class ClienteRepository {

    //Simulación de la base de datos
    public static final List<Cliente> clientes = new ArrayList<>();

    //Contador para generar IDs automáticos
    private static Long nextId = 1L;

    //** Guardar un nuevo cliente en la lista (Base de datos) **//
    public Cliente guardar(Cliente cliente)
    {
        //Asignar el ID antes de guardarlo
        cliente.setId(nextId++);

        clientes.add(cliente);
        return cliente; //Retorna el cliente con el ID asignado
    }

    /* Busca un cliente por su nombre de usuario
    * Cliente si se encuentra, o null si no existe */

    public Cliente findByUsuario(String usuario)
    {
        for (Cliente c: clientes)
        {
            if(c.getUsuario().equals(usuario))
            {
                return c; //Esta en la base de datos
            }
        }
        return null; //No se encontró el cliente
    }

    /* Busca un cliente por su email
     * Cliente si se encuentra, o null si no existe */
    public Cliente findByEmail(String email)
    {
        for (Cliente c: clientes)
        {
            if(c.getUsuario().equals(email))
            {
                return c; //Esta en la base de datos
            }
        }
        return null; //No se encontró el cliente
    }

    /* Busca un cliente por su cedula
     * Cliente si se encuentra, o null si no existe */
    public Cliente findByCedula(String cedula)
    {
        for (Cliente c: clientes)
        {
            if(c.getUsuario().equals(cedula))
            {
                return c; //Esta en la base de datos
            }
        }
        return null; //No se encontró el cliente
    }

    /* Busca un cliente por su cedula
     * Cliente si se encuentra, o null si no existe */
    public Cliente findByTelefono(String telefono)
    {
        for (Cliente c: clientes)
        {
            if(c.getUsuario().equals(telefono))
            {
                return c; //Esta en la base de datos
            }
        }
        return null; //No se encontró el cliente
    }


}
