import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {NgIf, NgFor, CommonModule} from '@angular/common';
import { DatePipe } from '@angular/common';

interface Class {
  id: number;
  classtype: string;
  teacher: string;
  time: string;         // ISO dátum string
  capacity: number;
  bookedspots: number;
}

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css'],
  standalone: true,
  imports: [CommonModule,NgIf, NgFor, DatePipe]
})
export class ClassComponent implements OnInit {
  sessions: Class[] = [];
  loading = false;
  error = '';
  successMessage = '';

  // TODO: ezt igazítsd a backend-edhez (pl. http://localhost:8080/api)
  private readonly API_BASE =" http://localhost:8080/api/classes";

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadSessions();
  }

  loadSessions(): void {
    this.loading = true;
    this.error = '';
    this.http
      .get<Class[]>(`${this.API_BASE}/classes`)
      .subscribe({
        next: (data) => {
          this.sessions = data;
          this.loading = false;
        },
        error: () => {
          this.error = 'Nem sikerült betölteni az órákat. Próbáld meg később.';
          this.loading = false;
        },
      });
  }

  bookSession(session: Class): void {
    this.error = '';
    this.successMessage = '';

    const body = { classSessionId: session.id };

    this.http.post(`${this.API_BASE}/foglalas`, body).subscribe({
      next: () => {
        this.successMessage = 'Foglalás sikeres! 😊';
        this.loadSessions(); // ha a backend frissíti a szabad helyeket
      },
      error: () => {
        this.error =
          'Nem sikerült lefoglalni az órát. Lehet, hogy be kell jelentkezned, vagy betelt az óra.';
      },
    });
  }

  getFreeSpots(session: Class): number | null {
    if (session.capacity == null || session.bookedspots == null) {
      return null;
    }
    return session.capacity - session.bookedspots;
  }
}
