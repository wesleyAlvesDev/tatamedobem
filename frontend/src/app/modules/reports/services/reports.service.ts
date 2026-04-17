import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Achievement, SponsorReport } from '../../../core/models/dashboard.models';

@Injectable({ providedIn: 'root' })
export class ReportsService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api';

  getSponsorReport(): Observable<SponsorReport> {
    return this.http.get<SponsorReport>(`${this.baseUrl}/dashboard/sponsor-report`);
  }

  getAchievements(): Observable<Achievement[]> {
    return this.http.get<Achievement[]>(`${this.baseUrl}/achievements`);
  }
}
