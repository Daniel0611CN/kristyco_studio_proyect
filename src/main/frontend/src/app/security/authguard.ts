import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { inject } from '@angular/core';
import { StorageService } from '../services/storage/storage.service';
import { ERol } from '../models/enums/rol.enum';

export const canActivateAdmin: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);

  if (!storageService.isLoggedIn()) {
    router.navigate(['/login']);
    return false;
  }

  const user = storageService.getUser();
  if (user?.rol === ERol.ROL_ADMIN) {
    router.navigate(['/perfil']);
    return false;
  }

  return true;
};
