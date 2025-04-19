import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { inject } from '@angular/core';
import { StorageService } from '../services/storage/storage.service';
import { ERol } from '../models/enums/rol.enum';

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);
  const roles = storageService.getUser()?.roles;

  return (storageService.isLoggedIn() && roles?.includes(ERol.ROL_ADMIN)) ||
          router.createUrlTree(storageService.isLoggedIn() ? ['/home'] : ['/login']);

};
