import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ColeccionService } from '../../../../../../services/coleccion.service';

@Component({
  selector: 'app-coleccion-form-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './coleccion-form-create.component.html'
})
export class ColeccionFormCreateComponent {

  fb = inject(FormBuilder);
  coleccionService = inject(ColeccionService);

  formCrear: FormGroup;
  @Output() successEvent = new EventEmitter<void>();
  @Output() errorEvent = new EventEmitter<void>();

  constructor() {
    this.formCrear = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      descripcion: ['']
    });
  }

  crearColeccion(): void {
    if (this.formCrear.valid) {
      this.coleccionService.create(this.formCrear.value).subscribe({
        next: () => this.successEvent.emit(),
        error: () => this.errorEvent.emit()
      });
    }
  }

  get nombreCrear() { return this.formCrear.get('nombre'); }

}
