import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = '';
  password = '';

  submitted = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  onLogin() {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.email || !this.password) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      return;
    }

    this.auth.login(this.email, this.password).subscribe({
      next: () => {
        this.router.navigate(['/classes']);
      },
      error: (err) => {

        if (err.status === 404) {
          this.errorMessage = 'Ezzel az email címmel nincs regisztráció.';
        } else if (err.status === 401 || err.status === 403) {
          this.errorMessage = 'Hibás jelszó.';
        } else {
          this.errorMessage = 'Hiba történt a bejelentkezés során.';
        }
      }
    });
  }
}
