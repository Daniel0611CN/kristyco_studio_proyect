import { Component, inject, OnInit } from '@angular/core';
import { UsuarioService } from '../../../services/usuario/usuario.service';
import { StorageService } from '../../../services/storage.service';
import { Usuario } from '../../../models/interfaces/entities/usuario.interface';

@Component({
  selector: 'app-perfil',
  imports: [],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent implements OnInit {
  usuarioService = inject(UsuarioService);
  storageService = inject(StorageService);

  usuario: Usuario = {
    id: '',
    nombre: '',
    apellido1: '',
    apellido2: '',
    email: '',
    telefono: 0,
    direccion: ''
  };

  ngOnInit(): void {
    this.usuarioService.get(this.storageService.getUser().id).subscribe((usuario: Usuario) => {
      this.usuario = usuario;
      console.log(this.usuario);
    });
  }

}
