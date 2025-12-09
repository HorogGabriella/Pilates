import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterLink } from '@angular/router';
import {NgIf} from '@angular/common';
import { AuthService } from '../auth/auth.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = '';
  password = '';
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  onLogin() {
    this.errorMessage = '';

    if (!this.email || !this.password) {
      this.errorMessage = 'Add meg az e-mail címet és a jelszót.';
      return;
    }

    this.authService.login(this.email, this.password).subscribe(
      (response: any) => {
        localStorage.setItem('token', response)
        console.log('Login Successful');
        this.router.navigate(['/class']);
      },
      error => {
        this.errorMessage = 'Hibás e-mail cím vagy jelszó.';
        this.cdr.detectChanges();
        console.error('An error occur during login', error)
      }
    );
  }
}
