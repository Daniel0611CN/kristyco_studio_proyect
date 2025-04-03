import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, throwError } from 'rxjs';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiAuthUrl = "http://localhost:8080/api/v1/auth/";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private httpClient: HttpClient, private storageService: StorageService) {}

  // login(username: string, password: string): Observable<any> {
  //   return this.httpClient.post(
  //     this.apiAuthUrl + 'login',
  //     {
  //       username,
  //       password
  //     },
  //     this.httpOptions
  //   );
  // }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post<any>(this.apiAuthUrl + 'login', { username, password }, this.httpOptions)
      .pipe(
        tap(response => { // asi funciona
          if (response.token) {
            console.log('✅ Token recibido:', response.token);
            localStorage.setItem('token', response.token);
          } else {
            console.warn('⚠️ No se recibió token en la respuesta.');
          }
        })
      );
  }


  register(username: string, apellido1: string, apellido2: string, email: string, telefono: number, direccion: string, password: string, rol: string) {
    let registerRequest = {
      "username": username,
      "apellido1": apellido1,
      "apellido2": apellido2,
      "email": email,
      "telefono": telefono,
      "direccion": direccion,
      "password": password,
      "roles": [rol]
    };

    return this.httpClient.post(
      this.apiAuthUrl + 'register',
      JSON.stringify(registerRequest),
      this.httpOptions
    );
  }

  logout() {
    this.storageService.clean();
  }

  errorHandler(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => errorMessage);
  }


}
