import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import { ERol } from '../../models/enums/rol.enum';

@Injectable({
  providedIn: 'root'
})
export class RolService {
  constructor(private storageService: StorageService) {}

  isUser(): boolean {
    const user = this.storageService.getUser();
    return user.rol === ERol.ROL_USER;
  }

  isAdmin(): boolean {
    const user = this.storageService.getUser();
    return user.rol === ERol.ROL_ADMIN;
  }

}
