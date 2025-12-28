import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap, catchError, of } from 'rxjs';
import {finalize} from 'rxjs/operators';

type MeDto = { email: string; roles: string[] };

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth';

  private me: MeDto | null = null;

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/bejelentkezes`,
      { email, jelszo: password },
      { responseType: 'text' }
    );
  }

  meLoading = false;

  loadMe(force = false): Observable<MeDto | null> {
    if (!this.isLoggedIn()) {
      this.me = null;
      return of(null);
    }

    if (!force && this.me) {
      return of(this.me);
    }

    this.meLoading = true;
    return this.http.get<MeDto>(`${this.apiUrl}/me`).pipe(
      tap(me => (this.me = me)),
      catchError(() => {
        this.me = null;
        return of(null);
      }),
      finalize(() => (this.meLoading = false))
    );
  }

  getMe(): Observable<MeDto> {
    return this.http.get<MeDto>(`${this.apiUrl}/me`);
  }

  isAdmin(): boolean {
    return (this.me?.roles ?? []).includes('ADMIN');
  }

  getLoggedEmail(): string {
    return this.me?.email ?? '';
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
    this.me = null;
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
