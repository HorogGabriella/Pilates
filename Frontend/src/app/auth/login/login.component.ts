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
        this.auth.getMe().subscribe({
          next: (me) => {
            if (me.roles?.includes('ADMIN')) {
              this.router.navigate(['/admin']);
            } else {
              this.router.navigate(['/classes']);
            }
          },
          error: (err) => {
              this.errorMessage = 'Hibás email-cím vagy jelszó.Vagy még nem regisztráltál.';
            }
        });
      }
    })
  }
}
