import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ColeccionComponent } from './components/coleccion/coleccion.component';
import { SobreMiComponent } from './components/sobre-mi/sobre-mi.component';
import { ContactoComponent } from './components/contacto/contacto.component';
import { InvitacionComponent } from './components/invitacion/invitacion.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, title: 'Inicio' },
  { path: 'colecciones', component: ColeccionComponent, title: 'Colecciones' },
  { path: 'sobre-mi', component: SobreMiComponent, title: 'Sobre-Mi' },
  { path: 'contacto', component: ContactoComponent, title: 'Contacto' },
  { path: 'login', component: LoginComponent, title: 'Inicio de Sesión' },
  { path: 'register', component: RegisterComponent, title: 'Registro' },
  { path: 'perfil', component: PerfilComponent, title: 'Perfil' },
  { path: 'invitaciones', component: InvitacionComponent, title: 'Invitaciones' },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];
