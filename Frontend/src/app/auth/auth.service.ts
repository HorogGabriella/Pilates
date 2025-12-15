import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/bejelentkezes`,
      { email, jelszo: password },
      { responseType: 'text' }
    ).pipe(
      tap(token => localStorage.setItem('token', token))
    );
  }

  register(name: string, email: string, password: string): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/regisztracio`,
      { nev: name, email, jelszo: password },
      { responseType: 'text' }
    );
  }

  getUserEmail(): Observable<string> {
    return this.http.get(
      `${this.apiUrl}/user-email`,
      { responseType: 'text' }
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
