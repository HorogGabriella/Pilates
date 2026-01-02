import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';

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

  isLoading = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private zone: NgZone,
    private cdr: ChangeDetectorRef
  ) {}

  onLogin(): void {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.email || !this.password) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      this.cdr.detectChanges();
      return;
    }

    this.isLoading = true;

    this.auth.login(this.email, this.password).subscribe({
      next: (res) => {
        this.zone.run(() => {
          this.isLoading = false;

          const text = String(res ?? '').trim();
          const looksLikeJwt = text.split('.').length === 3;

          if (!looksLikeJwt) {
            this.errorMessage =
              text || 'Hibás email-cím vagy jelszó. Vagy még nem regisztráltál.';
            this.cdr.detectChanges();
            return;
          }

          localStorage.setItem('token', text);

          this.auth.loadMe(true).subscribe({
            next: (me) => {
              this.zone.run(() => {
                if (me?.roles?.includes('ADMIN')) this.router.navigate(['/admin']);
                else this.router.navigate(['/classes']);
                this.cdr.detectChanges();
              });
            },
            error: () => {
              this.zone.run(() => {
                this.errorMessage = 'Nem sikerült lekérni a felhasználói adatokat.';
                this.cdr.detectChanges();
              });
            }
          });

          this.cdr.detectChanges();
        });
      },

      error: (err) => {
        this.zone.run(() => {
          console.log('LOGIN ERROR:', err);
          this.isLoading = false;
          this.errorMessage = this.getLoginErrorMessage(err);
          this.cdr.detectChanges();
        });
      }
    });
  }

  private getLoginErrorMessage(err: any): string {
    if (typeof err?.error === 'string' && err.error.trim()) {
      return err.error.trim();
    }

    if (err?.error?.message) {
      return String(err.error.message);
    }

    if (err?.status === 401 || err?.status === 403) {
      return 'Hibás email-cím vagy jelszó. Vagy még nem regisztráltál.';
    }

    if (err?.status === 0) {
      return 'Nem érem el a szervert (CORS / backend nem fut).';
    }

    return `Hiba történt a bejelentkezés során. (${err?.status ?? 'ismeretlen'})`;
  }
}
