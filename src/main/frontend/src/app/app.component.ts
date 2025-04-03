import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/shared/navbar/navbar.component";
import { StorageService } from './services/storage/storage.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';

  isLoggedIn  = false;

  constructor(private storageService: StorageService, private router: Router) {}

  logout() {
    this.storageService.clean();
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  }

}
