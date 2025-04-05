import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Enlaces } from '../../../models/interfaces/enlace.interface';
import { StorageService } from '../../../services/storage/storage.service';

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
    { label: 'Administración', link: '/admin' },
    { label: 'Configuración', link: '/perfil' },
    { label: 'Cerrar Sesión', link: '/login' }
  ]
  adminItems: Enlaces[] = [
    { label: 'Administrador', link: '/admin' },
    { label: 'Listado de Colecciones', link: '/listado/colecciones' },
    { label: 'Listado de Invitaciones', link: '/listado/invitaciones' },
    { label: 'Listado de Pedidos', link: '/listado/pedidos' }
  ];

  constructor(protected storageService: StorageService, private router: Router) {}

  isLoggedIn(): boolean {
    return this.storageService.isLoggedIn();
  }

  isAdminRoute(): boolean {
    const url = this.router.url;
    const isAdmin = this.adminItems.some(item => url.startsWith(item.link));

    return this.isLoggedIn() &&
           this.storageService.getUser()?.roles?.includes('ROL_ADMIN') &&
           isAdmin;
  }

  logout(): void {
    this.storageService.clean();
    this.router.navigateByUrl('/login');
  }
}
