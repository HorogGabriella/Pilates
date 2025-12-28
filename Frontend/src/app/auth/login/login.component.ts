import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
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

  constructor(
    private auth: AuthService,
    private router: Router
  ) {
  }

  onLogin(): void {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.email || !this.password) {
      this.errorMessage = 'Kérlek tölts ki minden mezőt!';
      return;
    }

    this.auth.login(this.email, this.password).subscribe({
      next: (res) => {
        const text = (res ?? '').trim();

        // ha a backend véletlen nem JWT-t ad vissza, azt is kezeljük
        const looksLikeJwt = text.split('.').length === 3;
        if (!looksLikeJwt) {
          this.errorMessage = text || 'Hibás email-cím vagy jelszó. Vagy még nem regisztráltál.';
          return;
        }

        localStorage.setItem('token', text);

        this.auth.loadMe(true).subscribe((me) => {
          if (me?.roles?.includes('ADMIN')) this.router.navigate(['/admin']);
          else this.router.navigate(['/classes']);
        });
      },

      error: (err) => {
        console.log('LOGIN ERROR:', err); // nézd a Console-ban is

        this.errorMessage = this.getLoginErrorMessage(err);
      }
    });
  }

  private getLoginErrorMessage(err: any): string {
    // 1) ha van szöveges body
    if (typeof err?.error === 'string' && err.error.trim()) {
      return err.error.trim();
    }

    // 2) ha JSON (pl {message: "..."} )
    if (err?.error?.message) {
      return String(err.error.message);
    }

    // 3) tipikus esetek
    if (err?.status === 401 || err?.status === 403) {
      return 'Hibás email-cím vagy jelszó. Vagy még nem regisztráltál.';
    }

    if (err?.status === 0) {
      return 'Nem érem el a szervert (CORS / backend nem fut).';
    }

    return `Hiba történt a bejelentkezés során. (${err?.status ?? 'ismeretlen'})`;
  }
}
