import { Component, signal, OnInit } from '@angular/core';
import { Router, RouterOutlet, NavigationEnd, RouterLink} from '@angular/router';
import { filter, map} from 'rxjs';
import { CommonModule, DatePipe } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true, 
  imports: [RouterOutlet,RouterLink, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
  providers: [DatePipe]
})
export class App {
  protected readonly title = signal('Sistema de Gestión de Estacionamiento');
  currentDate!: Date; 
  currentTime!: Date; 

  //Variable para saber si estamos en la ruta de registro 
  isRegisterRoute: boolean = false; //Inicializar variable 

  constructor(private router: Router) {
    this.router.events.subscribe(event => {

      if(event instanceof NavigationEnd)
      {
        this.isRegisterRoute = event.urlAfterRedirects === '/registro'; 
      }

    });
  } //Inyectar Router

  ngOnInit(): void
  {
    //Logica de Fecha / Hora 
    this.updateDateTime(); 
    setInterval(() =>  this.updateDateTime(), 1000); 

    //Logica de deteccion de ruta 
    //Detecta cada cambio de navegacion 
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(event => event as NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      //Verifica si la URL actual termina en '/registro'
      this.isRegisterRoute = event.urlAfterRedirects === '/registro'; 
    })
  }

  updateDateTime(): void{
    this.currentDate = new Date(); 
    this.currentTime = new Date(); 
  }

}
