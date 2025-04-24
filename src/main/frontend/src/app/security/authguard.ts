import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../services/storage/storage.service';
import { inject } from '@angular/core';

export const canActivateAdmin: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);

  return storageService.isAdmin() || router.createUrlTree(['/home']);
}

export const canActivateUser: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);

  return storageService.isLoggedIn() || router.createUrlTree(['/home']);
}

/*
  # Más adelante si se añaden más usuarios a la aplicación, si no todos pueden entrar al perfil,
  (caso extraño), limitar los roles en el storageService, creando un nuevo método;
  # Opciones a implementar, cuando eres user y entras en una ruta admin, poner sweetalert para
  explicar que el acceso es denegado;
*/
