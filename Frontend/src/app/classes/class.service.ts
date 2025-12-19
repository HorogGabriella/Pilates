import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClassSession } from './class.model';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  private apiUrl = 'http://localhost:8080/api/classes';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<ClassSession[]>(`${this.apiUrl}/findall`);
  }

  book(classId: number) {
    return this.http.post(
      `http://localhost:8080/api/foglalas/book/${classId}`,
      {}
    );
  }
}
