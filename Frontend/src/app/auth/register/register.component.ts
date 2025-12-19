import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  name ='';
  email = '';
  password = '';

  submitted = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  onRegister() {
    console.log('REGISZTRÁCIÓ GOMB MEGNYOMVA');

    this.errorMessage = '';
    this.successMessage = '';

    if (!this.email || !this.password || !this.name) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      return;
    }

    if (this.password.length < 8) {
      this.errorMessage = 'A jelszónak legalább 8 karakter hosszúnak kell lennie!';
      return;
    }

    this.auth.register(this.name, this.email, this.password).subscribe({
      next: (res: any) => {
        if (res?.success === false) {
          this.errorMessage = res.message ?? 'Hiba történt a regisztráció során.';
          return;
        }
        this.successMessage = 'Sikeres regisztráció! Átirányítás...';
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
    })
  }
}
