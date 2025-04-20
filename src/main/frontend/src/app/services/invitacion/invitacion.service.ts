import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InvitacionService {
  private apiProductoUrl = '/productos/';

  httpClient = inject(HttpClient);
  storageService = inject(StorageService);

  private get token(): string {
    return this.storageService.getUser()?.token || '';
  }

  private buildParams(page: number, size: number): HttpParams {
    return new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
  }

  private buildHttpOptions(params: HttpParams): { headers: HttpHeaders, params: HttpParams } {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }),
      params
    };
  }

  all(page: number = 0, size: number = 10): Observable<any> {
    if (!this.token) {
      return new Observable(observer => {
        observer.error('No hay token disponible.');
        observer.complete();
      });
    }

    const params = this.buildParams(page, size);
    const httpOptions = this.buildHttpOptions(params);

    return this.httpClient.get<any>(this.apiProductoUrl, httpOptions);
  }

}
