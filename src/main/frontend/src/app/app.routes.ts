import { Routes } from '@angular/router';
import { HomeComponent } from './components/content/home/home.component';
import { PerfilComponent } from './components/content/perfil/perfil.component';
import { ColeccionComponent } from './components/content/coleccion/coleccion.component';
import { SobreMiComponent } from './components/content/sobre-mi/sobre-mi.component';
import { canActivateAdmin } from './security/authguard';
import { ContactoComponent } from './components/content/contacto/contacto.component';
import { LoginComponent } from './components/form/login/login.component';
import { RegisterComponent } from './components/form/register/register.component';
import { InvitacionComponent } from './components/content/invitacion/invitacion.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, title: 'Inicio' },
  { path: 'colecciones', component: ColeccionComponent, title: 'Colecciones' },
  { path: 'sobre-mi', component: SobreMiComponent, title: 'Sobre-Mi' },
  { path: 'contacto', component: ContactoComponent, title: 'Contacto' },
  { path: 'login', component: LoginComponent, title: 'Inicio de Sesión' },
  { path: 'register', component: RegisterComponent, title: 'Registro' },
  { path: 'perfil', component: PerfilComponent, title: 'Perfil', canActivate: [canActivateAdmin] },
  { path: 'invitaciones', component: InvitacionComponent, title: 'Invitaciones' },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];
