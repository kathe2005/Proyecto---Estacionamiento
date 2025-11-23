package com.backend.backend.model;

public class Vehiculo {

    //Atributos o características del Vehiculo
    private Long id; //Clave para identificar el vehiculo

    //Tipo de Vechiulo (Ej: Moto / Carro / Bicicleta)
    private String tipodeVehiculo;

    //Marca del Vehiculo (Ej: Toyota, Ford)
    private String marca;

    // Placa del vehículo (Matrícula)
    private String placa;

    // Modelo específico (Ej: Corolla, F-150)
    private String modelo;

    // Color del vehículo
    private String color;

    //Relación con el cliente
    private Long clienteid;  //Id del cliente para la relación

    // Constructor Vacío (sigue siendo útil para la serialización JSON)
    public Vehiculo() {
    }

    //---- Constructor -----
    //Para inicializar los objetos
    public Vehiculo(Long id, String tipodeVehiculo, String marca, String placa, String modelo, String color)
    {
        //Clave para identificar al vehiculo
        this.id = id;

        this.tipodeVehiculo = tipodeVehiculo;
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
    }

    //--- Getters y Setters ---
    // Métodos para acceder y modificar los atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipodeVehiculo() {
        return tipodeVehiculo;
    }

    public void setTipodeVehiculo(String tipodeVehiculo) {
        this.tipodeVehiculo = tipodeVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getClienteid() {
        return clienteid;
    }

    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }
}
