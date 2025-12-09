import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, NgIf, NgFor } from '@angular/common';
import { AuthService } from '../auth/auth.service';
import { finalize } from 'rxjs/operators';
import { RouterLink } from '@angular/router';


interface ClassSession {
  id: number;
  classtype: string;
  teacher: string;
  time: Date;
  capacity: number;
  bookedspots: number;
}

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css'],
  standalone: true,
  imports: [CommonModule, NgIf, NgFor, RouterLink]
})
export class ClassComponent implements OnInit {

  sessions: ClassSession[] = [];
  loading = false;
  error = '';
  successMessage = '';
  currentUserEmail = '';

  private readonly API_BASE = 'http://localhost:8080/api/classes';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadSessions();
    this.getCurrentUserEmail();
  }

  getCurrentUserEmail(): void {
    this.authService.getUserEmail().subscribe(
      (response) => {
        this.currentUserEmail = response;
      },
      () => {
        console.warn('User email nem elérhető');
      }
    );
  }

  loadSessions(): void {
    this.error = '';

    this.http.get<any[]>(`${this.API_BASE}/findall`).subscribe(
      (response) => {
        console.log('Classes:', response);

        this.sessions = response.map(s => ({
          ...s,
          time: new Date(
            s.time[0],
            s.time[1] - 1,
            s.time[2],
            s.time[3],
            s.time[4]
          )
        }));
      },
      (err) => {
        console.error(err);
        this.error = 'Nem sikerült betölteni az órákat.';
      }
    );
  }

  bookSession(session: ClassSession): void {
    this.error = '';
    this.successMessage = '';

    const body = { classSessionId: session.id };

    this.http.post(`http://localhost:8080/api/foglalas/book/${session.id}`,{}).subscribe(
      () => {
        this.successMessage = 'Foglalás sikeres!';
        this.loadSessions();
      },
      () => {
        this.error = 'Nem sikerült lefoglalni az órát. Lehet, hogy betelt.';
      }
    );
  }

  getFreeSpots(session: ClassSession): number {
    return session.capacity - session.bookedspots;
  }
}
