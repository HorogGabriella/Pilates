import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, NgIf, NgFor } from '@angular/common';
import { AuthService } from '../auth/auth.service';

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
  imports: [CommonModule, NgIf, NgFor]
})
export class ClassComponent implements OnInit {

  sessions: ClassSession[] = [];
  loading = false;
  error = '';
  successMessage = '';

  currentUserEmail: string = '';

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
    this.loading = true;
    this.authService.getUserEmail().subscribe({
      next: (email) => {
        this.currentUserEmail = email;
        //this.loadSessions();
      },
      error: (err) => {
        console.error('Failed to fetch user email:', err);
        this.error = 'Nem vagy bejelentkezve.';
      }
    });
  }


  loadSessions(): void {
    this.http.get<any[]>(`${this.API_BASE}/findall`).subscribe({
      next: data => {
        this.sessions = data.map(s => ({
          ...s,
          time: new Date(
            s.time[0],
            s.time[1] - 1,
            s.time[2],
            s.time[3],
            s.time[4]
          )
        }));
        this.loading = false;
      },
      error: () => {
        this.error = 'Nem sikerült betölteni az órákat';
        this.loading = false;
      }
    });
  }

  bookSession(session: ClassSession): void {
    this.error = '';
    this.successMessage = '';

    const body = { classSessionId: session.id };

    this.http.post(`${this.API_BASE}/foglalas`, body)
      .subscribe(
        (response: any) => {
          this.successMessage = 'Foglalás sikeres! 😊';
          this.loadSessions();
        },
        () => {
          this.error =
            'Nem sikerült lefoglalni az órát. Lehet, hogy betelt az óra.';
        }
      );
  }

  getFreeSpots(session: ClassSession): number {
    return session.capacity - session.bookedspots;
  }
}
