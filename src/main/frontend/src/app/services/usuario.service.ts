import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Usuario } from '../models/interfaces/entities/usuario.interface';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUsuarioUrl = environment.apiUrl + '/usuarios';

  httpClient = inject(HttpClient);

  get(id: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(`${this.apiUsuarioUrl}/${id}`);
  }

}
