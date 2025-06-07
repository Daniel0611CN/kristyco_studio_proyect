import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ColeccionService } from '../../../../../../services/coleccion.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-coleccion-form-update',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './coleccion-form-update.component.html'
})
export class ColeccionFormUpdateComponent {

  fb = inject(FormBuilder);
  coleccionService = inject(ColeccionService);

  formEditar: FormGroup;
  @Input() selectedItem: any = null;
  @Output() successEvent = new EventEmitter<void>();
  @Output() errorEvent = new EventEmitter<void>();
  @Output() listCollections = new EventEmitter<void>();

  constructor() {
    this.formEditar = this.fb.group({
      id: [''],
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      descripcion: ['']
    });
  }

  editarColeccion(): void {
    if (this.formEditar.valid) {
      this.coleccionService.update(this.selectedItem.id, this.formEditar.value).subscribe({
        next: () => {
          this.successEvent.emit();
          this.listCollections.emit();
        },
        error: () => {
          this.errorEvent.emit();
        }
      });
    }
  }

}
