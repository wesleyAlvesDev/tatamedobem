export interface Student {
  id: number;
  fullName: string;
  documentNumber: string;
  birthDate: string;
  guardianName: string;
  guardianPhone: string;
  schoolName: string;
  schoolGrade: string;
  donatedGiSize: string;
  active: boolean;
  currentBelt: string;
}

export interface CreateStudentRequest {
  fullName: string;
  documentNumber: string;
  birthDate: string;
  guardianName: string;
  guardianPhone: string;
  schoolName: string;
  schoolGrade: string;
  donatedGiSize: string;
  currentBelt: string;
  active: boolean;
}
