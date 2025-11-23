package com.backend.backend.repository;

import com.backend.backend.model.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehiculoRepository {

    //Simulación de la base de datos
    public static final List<Vehiculo> vehiculos = new ArrayList<>();

    //Contador para generar IDs automáticos
    private static Long nextId = 1L;

    //** Guardar un nuevo cliente en la lista (Base de datos) **//
    public Vehiculo guardar(Vehiculo vehiculo)
    {
        //Solo asignar ID si es nuevo
        if(vehiculo.getId() == null)
        {
            vehiculo.setId(nextId ++); // Asigna el ID actual y luego lo incrementa
            vehiculos.add(vehiculo);
        }

        return vehiculo;
    }

    //Buscar un vehiculo por cliente
    public Optional<Vehiculo> findOneByClienteId(Long clienteId) {
        return vehiculos.stream()
                .filter(v -> v.getClienteid() != null && v.getClienteid().equals(clienteId))
                .findFirst(); // Devuelve Optional.empty() si no lo encuentra.
    }

    //Buscar vehiculo por placa
    public Optional<Vehiculo> findByPlaca(String placa) {
        return vehiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst(); // Devuelve Optional.empty() si no lo encuentra.
    }
}
