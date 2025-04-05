import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ColeccionService {
  private apiCategoriaUrl = 'http://localhost:8080/api/v1/categorias/';

  constructor(private httpClient: HttpClient, private storageService: StorageService) {}

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
