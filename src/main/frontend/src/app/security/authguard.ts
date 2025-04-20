import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../services/storage/storage.service';
import { inject } from '@angular/core';

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);

  return storageService.isLoggedIn() || router.createUrlTree(['/login']);
}
