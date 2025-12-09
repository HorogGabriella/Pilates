import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

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
    );
  }

  register(name: string, email: string, password: string): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/regisztracio`,
      { nev: name, email, jelszo: password },
      { responseType: 'text' }
    );
  }

  /** 🔐 aktuális user email lekérdezése */
  getUserEmail(): Observable<string> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    return this.http.get(`${this.apiUrl}/user-email`, { headers, responseType: 'text' });
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
