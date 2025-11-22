import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable} from 'rxjs';
import {Cliente} from  './models/cliente.model'; 

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  //URL del endpoint de registro del Backend 
  private apiURL = 'http://localhost:8080/api/clientes/registrar';

  constructor(private http: HttpClient)
  {

  }

  //Envia la solicitud de registro al Backend 
  registrar(clienteData: Cliente): Observable<Cliente>
  {
    return this.http.post<Cliente>(this.apiURL, clienteData); 
  }
  
}
