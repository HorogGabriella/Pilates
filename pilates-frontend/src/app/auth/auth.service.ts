import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string) {
    return this.http.post(
      `${this.apiUrl}/login`,
      { email, password },
      { responseType: 'text' }
    );
  }

  register(name: string, email: string, password: string) {
    return this.http.post(
      `${this.apiUrl}/registration`,
      { name, email, password },
      { responseType: 'text' }
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}

