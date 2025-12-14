import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Auth } from '../auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  email = '';
  password = '';

  submitted = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private auth: Auth,
    private router: Router
  ) {}

  onRegister() {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.email || !this.password) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      return;
    }

    if (this.password.length < 8) {
      this.errorMessage = 'A jelszónak legalább 8 karakter hosszúnak kell lennie!';
      return;
    }

    this.auth.register(this.email, this.password).subscribe({
      next: () => {
        this.successMessage = 'Sikeres regisztráció! Átirányítás...';
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = 'Ezzel az email címmel már regisztráltak.';
        } else {
          this.errorMessage = 'Hiba történt a regisztráció során.';
        }
      }
    });
  }
}
