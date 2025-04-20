import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../services/storage/storage.service';
import { RolService } from '../services/rol/rol.service';
import { inject } from '@angular/core';

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const rolService = inject(RolService);
  const router = inject(Router);

  return (storageService.isLoggedIn() && rolService.isAdmin()) ||
         router.createUrlTree(storageService.isLoggedIn() ? ['/home'] : ['/login']);
};
