import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  form: any = {
    username: null,
    apellido1: null,
    apellido2: null,
    email: null,
    telefono: null,
    direccion: null,
    password: null,
    rol: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {

  }

  onSubmit(): void {
    const { username, apellido1, apellido2, email, telefono, direccion, password, rol } = this.form;

    this.authService.register(username, apellido1, apellido2, email, telefono, direccion, password, rol).subscribe({
      next: data => {
        console.log(data);
        this.router.navigate(['/login']).then(
          () => {console.log('Register OK, cargando login...')}
        )
      },
      error: err => {
        this.errorMessage = err.error.message;
      }
    });
  }
}
