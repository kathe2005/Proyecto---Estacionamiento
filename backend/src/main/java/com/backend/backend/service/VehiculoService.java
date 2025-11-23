package com.backend.backend.service;

import com.backend.backend.model.Cliente;
import com.backend.backend.model.Vehiculo;

public interface VehiculoService {

    //--------------------------------- Metodos de Validacion -----------------------------------------------------------------

    //Verifica las reglas de unicidad: Placa única y 1 vehiculo por cliente
    void verificarReglasUnicidad(Vehiculo vehiculo);

    // Valida que los campos obligatorios (placa, tipo, marca, modelo, color) no estén vacíos.
    void validarCamposObligatorios(Vehiculo vehiculo);

    // Valida que el 'tipo de vehículo' (Carro, Moto, Bici) sea uno de los permitidos.
    void validarTipoVehiculoPermitido(Vehiculo vehiculo);

    // Valida el formato de la placa (ej: longitud, caracteres).
    void validarFormatoPlaca(Vehiculo vehiculo);

    //Metodo de registro que usará controller
    Vehiculo registrarVehiculo(Vehiculo vehiculo);

}
