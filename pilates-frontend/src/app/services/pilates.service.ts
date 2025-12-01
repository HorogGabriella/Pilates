import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/types/http';
import {Observable} from 'rxjs';
import {ClassSession} from '../models/class-session';

@Injectable({
  providedIn: 'root',
})
export class PilatesService {

  private apiUrl ='http://localhost:8080/api/classes'

  constructor(private http: HttpClient) {
  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // A böngészőből olvassuk ki
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getClasses(): Observable<ClassSession[]> {
    return this.http.get<ClassSession[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  createClass(data: ClassSession): Observable<ClassSession> {
    return this.http.post<ClassSession>(this.apiUrl, data, { headers: this.getHeaders() });
  }
}
