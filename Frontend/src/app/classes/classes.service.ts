import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PilatesClass } from './class';

@Injectable({
  providedIn: 'root'
})
export class ClassesService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getClasses() {
    return this.http.get<PilatesClass[]>(
      `${this.apiUrl}/classes`
    );
  }

  bookClass(oraId: number) {
    return this.http.post(
      `${this.apiUrl}/foglalas/${oraId}`,
      {}
    );
  }

  getMyBookings() {
    return this.http.get<any[]>(
      `${this.apiUrl}/foglalas`
    );
  }
}
