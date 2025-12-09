import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import {response} from 'express';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    NgIf
  ],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  name = '';
  email = '';
  password = '';

  successMessage = '';
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  onRegister() {
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.name || !this.email || !this.password) {
      this.errorMessage = 'Minden mezőt ki kell tölteni.';
      return;
    }

    if (this.password.length < 8) {
      this.errorMessage = 'A jelszónak legalább 8 karakter hosszúnak kell lennie.';
      return;
    }

    this.authService.register(this.name, this.email, this.password).subscribe(
      (response: string) => {
        localStorage.setItem('token', response)
        console.log('Sikeres regisztráció!');
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Hiba történt a regisztráció során', error);
        this.errorMessage = 'A regisztráció sikertelen.';
        this.cdr.detectChanges();
      }
    );
  }
  /*
  this.authService.register(this.name, this.email, this.password).subscribe({
      (response: string) => {
        this.successMessage = 'Sikeres regisztráció!';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1200);
      },
      error: (err) => {
        if (typeof err?.error === 'string') {
          this.errorMessage = err.error;
        } else {
          this.errorMessage = 'A regisztráció sikertelen.';
          this.cdr.detectChanges();
        }
      }
    });
   */
}

