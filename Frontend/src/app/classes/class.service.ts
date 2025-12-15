import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClassComponent } from './class.component';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  private apiUrl = 'http://localhost:8080/api/classes';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<ClassComponent[]>(`${this.apiUrl}/findall`);
  }
}
