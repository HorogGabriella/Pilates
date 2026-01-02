import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';

  submitted = false;
  errorMessage = '';
  successMessage = '';

  isLoading = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private zone: NgZone,
    private cdr: ChangeDetectorRef
  ) {}

  onRegister(): void {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.name || !this.email || !this.password) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      this.cdr.detectChanges();
      return;
    }

    if (this.password.length < 8) {
      this.errorMessage = 'A jelszónak legalább 8 karakter hosszúnak kell lennie!';
      this.cdr.detectChanges();
      return;
    }

    this.isLoading = true;

    this.auth.register(this.name, this.email, this.password).subscribe({
      next: () => {
        this.zone.run(() => {
          this.isLoading = false;
          this.successMessage = 'Sikeres regisztráció! Átirányítás...';
          this.cdr.detectChanges();
          setTimeout(() => this.router.navigate(['/login']), 1200);
        });
      },
      error: (err) => {
        this.zone.run(() => {
          this.isLoading = false;
          console.log('REGISTER ERROR:', err);
          this.errorMessage = this.getRegisterErrorMessage(err);
          this.cdr.detectChanges();
        });
      }
    });
  }

  private getRegisterErrorMessage(err: any): string {
    if (typeof err?.error === 'string' && err.error.trim()) {
      return err.error.trim();
    }

    if (err?.error?.message) {
      return String(err.error.message);
    }

    if (err?.status === 409) {
      return 'Ezzel az email címmel már létezik fiók.';
    }

    if (err?.status === 0) {
      return 'Nem érem el a szervert (CORS / backend nem fut).';
    }

    return `Hiba történt a regisztráció során. Lehet már regisztráltál.`;
  }
}
