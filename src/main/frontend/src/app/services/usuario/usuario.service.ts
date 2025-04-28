import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUsuarioUrl = environment.apiUrl + '/usuarios';

  httpClient = inject(HttpClient);
  storageService = inject(StorageService);

  


}
