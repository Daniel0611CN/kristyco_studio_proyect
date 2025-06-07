import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColeccionService } from '../../../../services/coleccion.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { ColeccionFormCreateComponent } from "./forms/create/coleccion-form-create.component";
import { ColeccionFormUpdateComponent } from "./forms/update/coleccion-form-update.component";
import { SwalService } from '../../../shared/services/swal.service';

@Component({
  selector: 'app-colecciones',
  imports: [CommonModule, ReactiveFormsModule, ColeccionFormCreateComponent, ColeccionFormUpdateComponent],
  templateUrl: './colecciones.component.html'
})
export class ListadoColeccionComponent {

  fb = inject(FormBuilder);
  swalService = inject(SwalService);
  coleccionService = inject(ColeccionService);

  title: string = 'Listado de Colecciones';
  columnas: { key: string, label: string }[] = [
    { key: 'id', label: 'ID' },
    { key: 'nombre', label: 'Nombre' },
    { key: 'descripcion', label: 'Descripción' }
  ];
  data: any[] = [];
  searchText: string = '';
  selectedItem: any = null;

  ngOnInit(): void {
    this.listarColecciones();
  }

  listarColecciones() {
    this.coleccionService.all().subscribe({
      next: res => this.data = res,
      error: err => console.error('Error al obtener las colecciones:', err)
    });
  }

  // abrirEditar(item: any) {
  //   this.selectedItem = item;
  //   this.formEditar.setValue({
  //     id: item.id,
  //     nombre: item.nombre,
  //     descripcion: item.descripcion
  //   });
  // }

  abrirEliminar(item: any) {
    this.selectedItem = item;
  }

  eliminarColeccion() {
    // this.coleccionService.delete(this.selectedItem.id).subscribe(() => this.listarColecciones());
  }

  closeModalCrear(): void {
    const closeBtn1 = document.querySelector('#exampleModalCrear .btn-close') as HTMLElement;
    if (closeBtn1) closeBtn1.click();
  }

  closeModalEditar(): void {
    const closeBtn2 = document.querySelector('#exampleModalEditar .btn-close') as HTMLElement;
    if (closeBtn2) closeBtn2.click();
  }

}
