import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { ClassService } from './class.service';
import { ClassSession } from './class.model';
import { ActivatedRoute } from '@angular/router';

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

  currentUserEmail = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private classes: ClassService
  ) {}

  ngOnInit(): void {
    this.sessions = this.route.snapshot.data['sessions'];
    this.loading = false;
  }

  loadSessions(): void {
    this.loading = true;
    this.error = '';

    this.classes.getAll().subscribe({
      next: data => {
        console.log('SESSIONS FROM BACKEND:', data);

        this.sessions = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;

        if (err.status === 401 || err.status === 403) {
          this.error = 'Nincs jogosultságod az órák megtekintéséhez. Jelentkezz be.';
          return;
        }

        this.error = 'Nem sikerült betölteni az órákat';
      }
    });
  }

  getFreeSpots(c: ClassSession): number {
    return c.capacity - c.bookedspots;
  }

  bookClass(c: ClassSession): void {
    this.message = '';
    this.error = '';

    this.classes.book(c.id).subscribe({
      next: () => {
        this.message = 'Sikeres jelentkezés!';

        this.classes.getAll().subscribe(data => {
          this.sessions = data;
        });
      },
      error: (err) => {
        if (err.status === 401 || err.status === 403) {
          this.error = 'A jelentkezéshez be kell jelentkezned.';
          return;
        }
        this.error = err.error?.message || 'Nem sikerült a jelentkezés';
      }
    });
  }

  getCurrentUserEmail(): void {
    this.auth.getUserEmail().subscribe({
      next: (email) => {
        this.currentUserEmail = email || '';
      },
      error: (err) => {

      }
    });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
