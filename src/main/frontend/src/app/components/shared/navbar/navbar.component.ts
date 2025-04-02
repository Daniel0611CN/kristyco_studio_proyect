import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Enlaces } from '../../../models/interfaces/enlace.interface';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  @Input({ required: true }) isLoggedIn!: boolean;
  @Input({ required: true }) logout!: () => void;

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
  dropdownLoginItems: Enlaces[] = [
    { label: 'Configuración', link: '/perfil' },
    { label: 'Cerrar Sesión', link: '/login' }
  ]
}
