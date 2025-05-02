import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class InvitacionService {
  private apiProductoUrl = environment.apiUrl +  '/productos';

  httpClient = inject(HttpClient);

  private buildParams(page: number, size: number): HttpParams {
    return new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
  }

  all(page: number = 0, size: number = 10): Observable<any> {
    const params = this.buildParams(page, size);

    return this.httpClient.get<any>(this.apiProductoUrl, { params });
  }

}
