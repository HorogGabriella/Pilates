import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClassSession} from '../models/class-session';

@Injectable({
  providedIn: 'root',
})
export class PilatesService {

  private apiUrl = 'http://127.0.0.1:8080/api/classes'

  constructor(private http: HttpClient) {
  }
  getClasses(): Observable<ClassSession[]> {
    // A headers-t közvetlenül itt hozzuk létre, hogy elkerüljük a típus-hibákat
    const headers = this.createHeaders();
    return this.http.get<ClassSession[]>(this.apiUrl);
  }

  createClass(data: ClassSession): Observable<ClassSession> {
    const headers = this.createHeaders();
    return this.http.post<ClassSession>(this.apiUrl, headers);
  }

  // Kiemeltük külön függvénybe, és "any"-re castoljuk, ha nagyon makacs a hiba
  private createHeaders(): HttpHeaders {
    let token = '';
    if (typeof localStorage !== 'undefined') {
      token = localStorage.getItem('token') || '';
    }
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
}
