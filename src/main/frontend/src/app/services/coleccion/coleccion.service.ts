import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ColeccionService {
  private apiCategoriaUrl = environment.apiUrl + '/categorias';

  httpClient = inject(HttpClient);

  all(): Observable<any> {
    return this.httpClient.get<any>(this.apiCategoriaUrl);
  }
}
