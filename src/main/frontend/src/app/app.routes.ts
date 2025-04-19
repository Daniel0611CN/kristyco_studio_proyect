import { LinvitacionComponent } from './components/content/admin/listado/linvitacion/linvitacion.component';
import { LcoleccionComponent } from './components/content/admin/listado/lcoleccion/lcoleccion.component';
import { LpedidoComponent } from './components/content/admin/listado/lpedido/lpedido.component';
import { InvitacionComponent } from './components/content/invitacion/invitacion.component';
import { ColeccionComponent } from './components/content/coleccion/coleccion.component';
import { ContactoComponent } from './components/content/contacto/contacto.component';
import { SobreMiComponent } from './components/content/sobre-mi/sobre-mi.component';
import { RegisterComponent } from './components/form/register/register.component';
import { PerfilComponent } from './components/content/perfil/perfil.component';
import { AdminComponent } from './components/content/admin/admin.component';
import { HomeComponent } from './components/content/home/home.component';
import { LoginComponent } from './components/form/login/login.component';
import { canActivate } from './security/authguard';
import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, title: 'Inicio' },
  { path: 'colecciones', component: ColeccionComponent, title: 'Colecciones' },
  { path: 'sobre-mi', component: SobreMiComponent, title: 'Sobre-Mi' },
  { path: 'contacto', component: ContactoComponent, title: 'Contacto' },
  { path: 'login', component: LoginComponent, title: 'Inicio de Sesión' },
  { path: 'register', component: RegisterComponent, title: 'Registro' },
  { path: 'perfil', component: PerfilComponent, title: 'Perfil' },
  { path: 'invitaciones', component: InvitacionComponent, title: 'Invitaciones' },
  { path: 'admin', component: AdminComponent, title: 'Admin', canActivate: [canActivate] },
  { path: 'listado/colecciones', component: LcoleccionComponent, title: 'Listado Colecciones', canActivate: [canActivate] },
  { path: 'listado/invitaciones', component: LinvitacionComponent, title: 'Listado Invitaciones', canActivate: [canActivate] },
  { path: 'listado/pedidos', component: LpedidoComponent, title: 'Listado Pedidos', canActivate: [canActivate] },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];
