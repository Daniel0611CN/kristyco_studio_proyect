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
import { AdminComponent } from './components/content/admin/admin.component';
import { LcoleccionComponent } from './components/content/admin/listado/lcoleccion/lcoleccion.component';
import { LinvitacionComponent } from './components/content/admin/listado/linvitacion/linvitacion.component';
import { LpedidoComponent } from './components/content/admin/listado/lpedido/lpedido.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, title: 'Inicio' },
  { path: 'colecciones', component: ColeccionComponent, title: 'Colecciones' },
  { path: 'sobre-mi', component: SobreMiComponent, title: 'Sobre-Mi' },
  { path: 'contacto', component: ContactoComponent, title: 'Contacto' },
  { path: 'login', component: LoginComponent, title: 'Inicio de Sesión' },
  { path: 'register', component: RegisterComponent, title: 'Registro' },
  { path: 'perfil', component: PerfilComponent, title: 'Perfil' },
  { path: 'invitaciones', component: InvitacionComponent, title: 'Invitaciones' },
  { path: 'admin', component: AdminComponent, title: 'Admin', canActivate: [canActivateAdmin] },
  { path: 'listado/colecciones', component: LcoleccionComponent, title: 'Listado Colecciones', canActivate: [canActivateAdmin] },
  { path: 'listado/invitaciones', component: LinvitacionComponent, title: 'Listado Invitaciones', canActivate: [canActivateAdmin] },
  { path: 'listado/pedidos', component: LpedidoComponent, title: 'Listado Pedidos', canActivate: [canActivateAdmin] },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];
