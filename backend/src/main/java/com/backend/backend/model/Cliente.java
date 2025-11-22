package com.backend.backend.model;

public class Cliente {

    //Atributos o características del cliente
    private Long id; //Clave para identificar al cliente

    //Credenciales de Acceso
    private String usuario;
    private String email;
    private String contrasena;
    private String confirmarContrasena;

    //Datos Personales
    private String cedula;
    private String nombre;
    private String apellido;
    private String tipodePersona;

    //Contactos y Ubicación
    private String telefono;
    private String direccion;

    //---- Constructor -----
    //Para inicializar los objetos
    public Cliente(Long id, String usuario, String email, String contrasena, String confirmarContrasena, String cedula, String nombre, String apellido, String tipodePersona, String telefono, String direccion) {
        //Clave para identificar al cliente
        this.id = id;

        //Credenciales de Acceso
        this.usuario = usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.confirmarContrasena = confirmarContrasena;

        //Datos Personales
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipodePersona = tipodePersona;

        //Contacto y Ubicación
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //--- Getters y Setters ---
    // Métodos para acceder y modificar los atributos


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipodePersona() {
        return tipodePersona;
    }

    public void setTipodePersona(String tipodePersona) {
        this.tipodePersona = tipodePersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
