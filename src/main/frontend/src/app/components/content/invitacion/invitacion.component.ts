import { Component } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';

@Component({
  selector: 'app-invitacion',
  imports: [CommonModule, NgClass],
  templateUrl: './invitacion.component.html',
  styleUrl: './invitacion.component.css'
})
export class InvitacionComponent {

  invitaciones = [
    { id: 1, src: '../../img/4.jpg', title: 'Alas de Cristal' },
    { id: 2, src: '../../img/5.jpg', title: 'Brisa de Fiesta' },
    { id: 3, src: '../../img/6.jpg', title: 'Vuelo de Amor' }
  ];

  currentPage = 0;
  itemsPerPage = 2;
  totalPages = Math.ceil(this.invitaciones.length / this.itemsPerPage);

  get currentItems() {
    const start = this.currentPage * this.itemsPerPage;
    return this.invitaciones.slice(start, start + this.itemsPerPage);
  }

  // Nuevo getter para el título
  get currentTitle(): string {
    return this.currentItems.length > 0 ? this.currentItems[0].title : 'Invitación';
  }

  ngOnInit() {
    this.updateItemsPerPage();
  }

  updateItemsPerPage() {
    this.itemsPerPage = this.invitaciones.length === 3 ? 3 : 2;
    this.totalPages = Math.ceil(this.invitaciones.length / this.itemsPerPage);
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
    }
  }

}
