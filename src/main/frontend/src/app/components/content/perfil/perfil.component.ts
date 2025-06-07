import { Component, inject, OnInit } from '@angular/core';
import { StorageService } from '../../shared/services/storage.service';
import { Usuario } from '../../../models/interfaces/entities/usuario.interface';
import { UsuarioService } from '../../../services/usuario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-perfil',
  imports: [CommonModule],
  templateUrl: './perfil.component.html'
})
export class PerfilComponent implements OnInit {
  usuarioService = inject(UsuarioService);
  storageService = inject(StorageService);

  usuario: Usuario = {
    id: '',
    nombre: '',
    apellido1: '',
    apellido2: '',
    email: '',
    telefono: '',
    direccion: ''
  };

  ngOnInit(): void {
    this.usuarioService.get(this.storageService.getUser().id).subscribe((usuario: Usuario) => {
      this.usuario = usuario;
    });
  }


  // Implementar en el Perfil:

      // <div class="form-group mb-2">
      //   <label for="oldPassword" class="form-label">Antigua Contraseña</label>
      //   <div class="input-group">
      //     <input id="oldPassword" [type]="showPassword ? 'text' : 'password'" class="form-control"
      //       (blur)="validateOldPassword()" formControlName="oldPassword"
      //       [ngClass]="{ 'is-invalid': oldPassword?.invalid && (oldPassword?.touched || oldPassword?.dirty) }" />
      //     <button class="btn text-secondary bg-primary border-0" type="button" (click)="togglePassword()"
      //       aria-label="Mostrar u ocultar contraseña">
      //       <i class="fa-solid" [ngClass]="showPassword ? 'fa-eye' : 'fa-eye-slash'"></i>
      //     </button>
      //   </div>
      //   <div *ngIf="oldPassword?.invalid && (oldPassword?.touched || oldPassword?.dirty)"
      //     class="invalid-feedback d-block">
      //     <div *ngIf="oldPassword?.errors?.['required']">
      //       La contraseña es obligatoria.
      //     </div>
      //     <div *ngIf="oldPassword?.errors?.['incorrect']">
      //       La contraseña introducida no es correcta.
      //     </div>
      //   </div>
      // </div>

        // validateOldPassword(): void {
        //   const oldPassword = this.oldPassword?.value;

        //   if (!oldPassword) return;

        //   this.http.post<{ valid: boolean, message?: string }>(
        //     'https://kristyco-studio-proyect.onrender.com/api/v1/confirmation_token/validate-old-password',
        //     {
        //       token: this.token,
        //       oldPassword: oldPassword
        //     }
        //   ).subscribe({
        //     next: (res) => {
        //       this.oldPasswordValid = res.valid;
        //       if (!res.valid) {
        //         this.resetForm.get('oldPassword')?.setErrors({ incorrect: true });
        //       } else {
        //         // Limpia el error si es válido
        //         this.resetForm.get('oldPassword')?.setErrors(null);
        //       }
        //     },
        //     error: () => {
        //       this.oldPasswordValid = false;
        //       this.resetForm.get('oldPassword')?.setErrors({ incorrect: true });
        //     }
        //   });
        // }


}
