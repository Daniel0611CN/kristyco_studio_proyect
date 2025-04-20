import { Component, output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  imports: [FormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  checkActiveSearch: boolean = false;
  searchInput: string = '';
  search = output<{searchText: string}>();
  over2Chars: boolean = false;

  //Callback para evento (ngSubmit) en el formulario de busqueda
  onSubmit() {

    //Se emite el output hacia el componente padre
    // para que este lo recoja en (search)="onSearch($event)"
    this.search.emit({ searchText : this.searchInput});
  }

  onUpdateField(event: KeyboardEvent) {
    if (this.checkActiveSearch && this.searchInput.length > 2) {
      this.over2Chars = true;
      this.search.emit({ searchText : this.searchInput});
    } else if (this.checkActiveSearch && this.over2Chars) {
      this.over2Chars = false;
      this.search.emit({ searchText : ''});
    }
  }

}
