package com.backend.backend.service;

import com.backend.backend.model.Cliente;
import com.backend.backend.model.Vehiculo;
import com.backend.backend.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoServiceImpl implements  VehiculoService{

    @Autowired //Relación con el repositorio (base de datos)
    private VehiculoRepository vehiculoRepository;


    //Implementación del metodo de las reglas de unicidad
    @Override
    public void verificarReglasUnicidad(Vehiculo vehiculo) {


    }

    @Override
    public void validarCamposObligatorios(Vehiculo vehiculo) {

    }

    @Override
    public void validarTipoVehiculoPermitido(Vehiculo vehiculo) {

    }

    @Override
    public void validarFormatoPlaca(Vehiculo vehiculo) {

    }


    //Implementación del metodo de registro de vehiculo
    @Override
    public Vehiculo registrarVehiculo(Vehiculo vehiculo)
    {

    }

}
