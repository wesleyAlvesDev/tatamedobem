import { Component, inject } from '@angular/core';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { BehaviorSubject, switchMap } from 'rxjs';
import { StudentsService } from '../../services/students.service';

@Component({
  standalone: true,
  imports: [AsyncPipe, NgFor, NgIf, ReactiveFormsModule],
  templateUrl: './students-page.component.html',
  styleUrl: './students-page.component.css'
})
export class StudentsPageComponent {
  private readonly studentsService = inject(StudentsService);
  private readonly fb = inject(FormBuilder);
  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  readonly form = this.fb.nonNullable.group({
    fullName: ['', Validators.required],
    documentNumber: ['', Validators.required],
    birthDate: ['', Validators.required],
    guardianName: [''],
    guardianPhone: [''],
    schoolName: [''],
    schoolGrade: [''],
    donatedGiSize: ['A1'],
    currentBelt: ['WHITE']
  });

  readonly students$ = this.refresh$.pipe(
    switchMap(() => this.studentsService.getStudents())
  );

  save(): void {
    if (this.form.invalid) {
      return;
    }

    this.studentsService.createStudent({
      ...this.form.getRawValue(),
      active: true
    }).subscribe(() => {
      this.form.reset({
        fullName: '',
        documentNumber: '',
        birthDate: '',
        guardianName: '',
        guardianPhone: '',
        schoolName: '',
        schoolGrade: '',
        donatedGiSize: 'A1',
        currentBelt: 'WHITE'
      });
      this.refresh$.next();
    });
  }
}
