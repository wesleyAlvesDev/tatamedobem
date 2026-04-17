import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateStudentRequest, Student } from '../../../core/models/student.models';

@Injectable({ providedIn: 'root' })
export class StudentsService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/students';

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.baseUrl);
  }

  createStudent(payload: CreateStudentRequest): Observable<Student> {
    return this.http.post<Student>(this.baseUrl, payload);
  }
}
