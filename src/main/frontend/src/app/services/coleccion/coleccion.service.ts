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
  storageService = inject(StorageService);

  private get token(): string {
    return this.storageService.getUser()?.token || '';
  }

  private buildHttpOptions(): { headers: HttpHeaders } {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
    };
  }

  all(): Observable<any> {
    const httpOptions = this.buildHttpOptions();

    return this.httpClient.get<any>(this.apiCategoriaUrl, httpOptions);
  }
}
