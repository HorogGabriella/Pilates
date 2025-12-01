import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, tap} from 'rxjs';
import {BejelentkezesDto} from '../components/login/login';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';
  private tokenKey = 'token';

  constructor(private http: HttpClient) { }

  public isLoggedIn$:BehaviorSubject<boolean> =
    new BehaviorSubject<boolean>(this.hasToken());

  login(bedto: BejelentkezesDto){
    return this
      .http
      .post(
        `${this.apiUrl}/bejelentkezes`,
        bedto,
        {responseType: 'text'})
      .pipe(
        tap(token => {
          // 2. HIBA JAVÍTÁSA (SSR): Csak böngészőben nyúlunk a localStorage-hoz
          if (typeof localStorage !== 'undefined') {
            localStorage.setItem(this.tokenKey, token);
          }
          this.isLoggedIn$.next(true);
        })
      );
  }

  logout() {
    if (typeof localStorage !== 'undefined') {
      localStorage.removeItem(this.tokenKey);
    }
    this.isLoggedIn$.next(false);
  }

  getToken(): string | null {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem(this.tokenKey);
    }
    return null;
  }

  private hasToken(): boolean {
    if (typeof localStorage !== 'undefined') {
      return !!localStorage.getItem(this.tokenKey);
    }
    return false;
  }
}
