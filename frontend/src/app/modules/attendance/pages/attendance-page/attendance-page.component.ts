import { Component, inject } from '@angular/core';
import { AsyncPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { BehaviorSubject, combineLatest, map, switchMap } from 'rxjs';
import { AttendanceService } from '../../services/attendance.service';
import { StudentsService } from '../../../students/services/students.service';

@Component({
  standalone: true,
  imports: [AsyncPipe, DatePipe, NgFor, NgIf, ReactiveFormsModule],
  templateUrl: './attendance-page.component.html',
  styleUrl: './attendance-page.component.css'
})
export class AttendancePageComponent {
  private readonly attendanceService = inject(AttendanceService);
  private readonly studentsService = inject(StudentsService);
  private readonly fb = inject(FormBuilder);
  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  readonly form = this.fb.nonNullable.group({
    studentId: ['', Validators.required],
    attendanceDate: [new Date().toISOString().slice(0, 10), Validators.required],
    status: ['PRESENT', Validators.required],
    notes: ['']
  });

  readonly vm$ = combineLatest([
    this.studentsService.getStudents(),
    this.refresh$.pipe(switchMap(() => this.attendanceService.getAttendanceAlerts()))
  ]).pipe(
    map(([students, alerts]) => ({ students, alerts }))
  );

  save(): void {
    if (this.form.invalid) {
      return;
    }

    this.attendanceService.createAttendance({
      studentId: Number(this.form.getRawValue().studentId),
      attendanceDate: this.form.getRawValue().attendanceDate,
      status: this.form.getRawValue().status,
      notes: this.form.getRawValue().notes
    }).subscribe(() => {
      this.refresh$.next();
    });
  }
}
