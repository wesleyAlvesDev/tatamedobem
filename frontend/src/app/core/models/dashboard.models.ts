export interface DashboardSummary {
  totalStudents: number;
  activeStudents: number;
  totalAttendanceAlerts: number;
  inventoryAlerts: number;
  resizeAlerts: number;
  totalAchievements: number;
}

export interface SponsorReport {
  semesterLabel: string;
  studentsServed: number;
  attendanceRate: number;
  championshipWinners: number;
  activeStudents: number;
  zeroDropoutCount: number;
  impactMessage: string;
}

export interface InventoryAlert {
  id: number;
  itemName: string;
  category: string;
  sizeLabel: string;
  quantityAvailable: number;
  minimumStock: number;
  belowMinimum: boolean;
}

export interface Achievement {
  id: number;
  studentId: number;
  studentName: string;
  title: string;
  description: string;
  imageUrl: string;
  category: string;
  achievementDate: string;
}
