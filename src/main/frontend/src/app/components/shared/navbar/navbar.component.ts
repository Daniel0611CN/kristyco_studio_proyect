import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Enlaces } from '../../../models/interfaces/enlace.interface';
import { StorageService } from '../../../services/storage/storage.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  navItems: Enlaces[] = [
    { label: 'Inicio', link: '/home' },
    { label: 'Colecciones', link: '/colecciones' },
    { label: 'Sobre Mi', link: '/sobre-mi' },
    { label: 'Contacto', link: '/contacto' }
  ];
  dropdownItems: Enlaces[] = [
    { label: 'Registrarse', link: '/register' },
    { label: 'Iniciar Sesión', link: '/login' },
  ]
  dropdownConfigItems: Enlaces[] = [
    { label: 'Configuración', link: '/perfil' },
    { label: 'Cerrar Sesión', link: '/login' }
  ]
  dropdownAdminItems: Enlaces[] = [
    { label: 'Administrador', link: '/admin' },
    { label: 'Listado de Colecciones', link: '/listado/colecciones' },
    { label: 'Listado de Invitaciones', link: '/listado/invitaciones' },
    { label: 'Listado de Pedidos', link: '/listado/pedidos' }
  ];

  constructor(protected storageService: StorageService, private router: Router) {}

  isLoggedIn(): boolean {
    return this.storageService.isLoggedIn();
  }

  logout(): void {
    this.storageService.clean();
    this.router.navigateByUrl('/login');
  }
}
