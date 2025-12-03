import {Component} from '@angular/core';
import { ClassList } from './components/class-list/class-list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ClassList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
