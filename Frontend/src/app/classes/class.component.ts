import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';

interface ClassSession {
  id: number;
  classtype: string;
  teacher: string;
  time: string;          // ⬅️ STRING (LocalDateTime JSON)
  capacity: number;
  bookedspots: number;
}

@Component({
  standalone: true,
  selector: 'app-class',
  imports: [CommonModule, RouterModule],
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit {

  sessions: ClassSession[] = [];
  loading = true;
  message = '';
  error = '';

  private API_BASE = 'http://localhost:8080/api/classes';

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadSessions();
  }

  loadSessions(): void {
    this.http.get<ClassSession[]>(`${this.API_BASE}/findall`).subscribe({
      next: data => {
        this.sessions = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Nem sikerült betölteni az órákat';
        this.loading = false;
      }
    });
  }

  getFreeSpots(c: ClassSession): number {
    return c.capacity - c.bookedspots;
  }

  bookClass(c: ClassSession): void {
    this.http.post(`http://localhost:8080/api/foglalas/book/${c.id}`, {})
      .subscribe({
        next: () => {
          this.message = 'Sikeres jelentkezés!';
          this.loadSessions();
        },
        error: err => {
          this.message = err.error?.message || 'Nem sikerült a jelentkezés';
        }
      });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
