// import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
// import { ChangeDetectorRef, Component, inject } from '@angular/core';
// import { AuthService } from '../../../services/auth.service';
// import { Router, RouterLink } from '@angular/router';
// import { CommonModule } from '@angular/common';
// import Swal from 'sweetalert2';

// @Component({
//   selector: 'app-register',
//   imports: [CommonModule, ReactiveFormsModule, RouterLink],
//   templateUrl: './register.component.html'
// })
// export class RegisterComponent {

//   authService = inject(AuthService);
//   cd = inject(ChangeDetectorRef);
//   fb = inject(FormBuilder);
//   router = inject(Router);

//   registerForm: FormGroup;

//   constructor() {
//     this.registerForm = this.fb.group({
//       username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
//       apellido1: ['', [Validators.required, Validators.maxLength(20)]],
//       apellido2: ['', [Validators.maxLength(20)]],
//       email: ['', [Validators.required, Validators.pattern("[a-zA-Z0-9!#$%&'*\/=?^_`\{\|\}~\+\-]([\.]?[a-zA-Z0-9!#$%&'*\/=?^_`\{\|\}~\+\-])+@[a-zA-Z0-9]([^@&%$\/\(\)=?¿!\.,:;]|\d)+[a-zA-Z0-9][\.][a-zA-Z]{2,4}([\.][a-zA-Z]{3})?")]],
//       telefono: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]],
//       direccion: ['', [Validators.required]],
//       password: [
//         '',
//         [
//           Validators.required,
//           Validators.maxLength(24),
//           Validators.pattern('^(?=(?:.*[a-z]){3,})(?=(?:.*[A-Z]){2,})(?=(?:.*\\d){1,})(?=(?:.*[^a-zA-Z0-9]){1,}).{8,24}$')
//         ]
//       ],
//       rol: 'User',
//       privacy: [false, Validators.requiredTrue],
//       terms_conditions: [false, Validators.requiredTrue]
//     });
//   }

//   isLoggedIn = false;
//   isLoginFailed = false;
//   showForm: boolean = true;

//   errorMessage = '';
//   roles: string[] = [];

//   showPassword = false;
//   togglePassword() { this.showPassword = !this.showPassword; }

//   formSubmitted = false;

//   onSubmit(): void {
//       this.formSubmitted = true;

//     if (this.registerForm.invalid) {
//       this.registerForm.markAllAsTouched();
//       return;
//     }

//     const formValues = this.registerForm.value;

//     this.authService.register(formValues.username, formValues.apellido1, formValues.apellido2, formValues.email,
//         formValues.telefono, formValues.direccion, formValues.password, formValues.rol).subscribe({
//       next: () => {
//         this.showForm = false;
//         Swal.fire({
//           icon: 'success',
//           title: 'Registro existoso',
//           text: `Redirigiendo a login, consulte su email para poder iniciar sesión.`,
//           showConfirmButton: true
//         }).then(() => {
//           this.isLoggedIn = true;
//           this.router.navigateByUrl("/login");
//         });
//       },
//       error: err => {
//         this.showForm = false;
//         this.isLoginFailed = true;

//         this.errorMessage = err?.error?.message;

//         if (this.errorMessage.includes('usuario')) {
//           Swal.fire({
//             icon: 'error',
//             title: 'Nombre de usuario ya registrado',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         } else if (this.errorMessage.includes('email')) {
//           Swal.fire({
//             icon: 'error',
//             title: 'Correo ya registrado',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         } else if (this.errorMessage.includes('teléfono')) {
//           Swal.fire({
//             icon: 'error',
//             title: 'Teléfono ya registrado',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         } else if (this.errorMessage.includes('rol')) {
//           Swal.fire({
//             icon: 'error',
//             title: 'Rol inválido',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         } else if (this.errorMessage.includes('correo de confirmación')) {
//           Swal.fire({
//             icon: 'error',
//             title: 'Error de envío',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         } else {
//           Swal.fire({
//             icon: 'error',
//             title: 'Error desconocido',
//             text: this.errorMessage,
//             confirmButtonText: 'Aceptar'
//           }).then(() => { this.onReload(); });
//         }
//       }
//     })
//   }

//   onReload() {
//     this.showForm = true;
//     this.isLoginFailed = false;
//     this.cd.detectChanges();
//   }

//   onClose() {
//     this.router.navigateByUrl('/login');
//   }

//   get username() { return this.registerForm.get('username'); }
//   get password() { return this.registerForm.get('password'); }
//   get email() { return this.registerForm.get('email'); }
//   get apellido1() { return this.registerForm.get('apellido1'); }
//   get apellido2() { return this.registerForm.get('apellido2'); }
//   get telefono() { return this.registerForm.get('telefono'); }
//   get direccion() { return this.registerForm.get('direccion'); }

// }


import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SwalService } from '../../shared/services/swal.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  authService = inject(AuthService);
  cd = inject(ChangeDetectorRef);
  fb = inject(FormBuilder);
  router = inject(Router);
  swalService = inject(SwalService);

  showPassword = false;
  showForm = true;
  formSubmitted = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  registerForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
    email: ['', [
      Validators.required,
      Validators.pattern(/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/),
      Validators.minLength(12),
      Validators.maxLength(45)
    ]],
    password: ['', [
      Validators.required,
      Validators.maxLength(24),
      Validators.pattern(/^(?=(?:.*[a-z]){3,})(?=(?:.*[A-Z]){2,})(?=(?:.*\d){1,})(?=(?:.*[^a-zA-Z0-9]){1,}).{8,24}$/)
    ]],
    apellido1: ['', [Validators.required, Validators.maxLength(20)]],
    apellido2: ['', [Validators.maxLength(20)]],
    telefono: ['', [Validators.required, Validators.pattern(/^[0-9]{9}$/)]],
    direccion: ['', [Validators.required]],
    rol: ['User'],
    privacy: [false, Validators.requiredTrue],
    terms_conditions: [false, Validators.requiredTrue]
  });

  onSubmit(): void {
    this.formSubmitted = true;

    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const {
      username,
      apellido1,
      apellido2,
      email,
      telefono,
      direccion,
      password,
      rol
    } = this.registerForm.value;

    this.authService.register(username, apellido1, apellido2, email, telefono, direccion, password, rol).subscribe({
      next: () => {
        this.showForm = false;
        this.swalService.showSuccess('Registro exitoso', 'Redirigiendo al login. Por favor, revise su correo.').then(() => {
          this.isLoggedIn = true;
          this.router.navigateByUrl('/login');
        });
      },
      error: err => {
        this.showForm = false;
        this.isLoginFailed = true;
        this.errorMessage = err?.error?.message || 'Error inesperado';

        if (this.errorMessage.includes('usuario')) {
          this.showError('Nombre de usuario ya registrado', this.errorMessage);
        } else if (this.errorMessage.includes('email')) {
          this.showError('Correo ya registrado', this.errorMessage);
        } else if (this.errorMessage.includes('teléfono')) {
          this.showError('Teléfono ya registrado', this.errorMessage);
        } else if (this.errorMessage.includes('rol')) {
          this.showError('Rol inválido', this.errorMessage);
        } else if (this.errorMessage.includes('correo de confirmación')) {
          this.showError('Error de envío', this.errorMessage);
        } else {
          this.showError('Error desconocido', this.errorMessage);
        }
      }
    });
  }

  showError(title: string, text: string): void {
    this.swalService.showError(title, text).then(() => this.onReload());
  }

  onReload(): void {
    this.showForm = true;
    this.isLoginFailed = false;
    this.formSubmitted = false;
    this.registerForm.reset({
      rol: 'User',
      privacy: false,
      terms_conditions: false
    });
    this.cd.detectChanges();
  }

  onClose(): void {
    this.router.navigateByUrl('/login');
  }

  get hasLength(): boolean {
    const val = this.password?.value || '';
    return val.length >= 8 && val.length <= 20;
  }

  get hasLowercase(): boolean {
    const value = this.password?.value || '';
    return (value.match(/[a-z]/g) || []).length >= 3;
  }

  get hasUppercase(): boolean {
    const value = this.password?.value || '';
    return (value.match(/[A-Z]/g) || []).length >= 2;
  }

  get hasNumber(): boolean {
    const value = this.password?.value || '';
    return (value.match(/[0-9]/g) || []).length >= 2;
  }

  get hasSpecialChar(): boolean {
    const value = this.password?.value || '';
    return /[^A-Za-z0-9]/.test(value);
  }

  get isPasswordStrong(): boolean {
    return (
      this.hasLength &&
      this.hasLowercase &&
      this.hasUppercase &&
      this.hasNumber &&
      this.hasSpecialChar
    );
  }

  get username() { return this.registerForm.get('username'); }
  get password() { return this.registerForm.get('password'); }
  get email() { return this.registerForm.get('email'); }
  get apellido1() { return this.registerForm.get('apellido1'); }
  get apellido2() { return this.registerForm.get('apellido2'); }
  get telefono() { return this.registerForm.get('telefono'); }
  get direccion() { return this.registerForm.get('direccion'); }
}
