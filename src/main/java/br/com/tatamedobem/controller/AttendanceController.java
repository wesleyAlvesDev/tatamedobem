package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.AttendanceRequest;
import br.com.tatamedobem.dto.AttendanceResponse;
import br.com.tatamedobem.dto.MonthlyAttendanceReportResponse;
import br.com.tatamedobem.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@Tag(name = "Frequencia")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping
    @Operation(summary = "Listar registros de frequencia")
    public List<AttendanceResponse> findAll() {
        return attendanceService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar presenca ou falta")
    public AttendanceResponse create(@Valid @RequestBody AttendanceRequest request) {
        return attendanceService.create(request);
    }

    @GetMapping("/alerts")
    @Operation(summary = "Listar alertas de faltas consecutivas")
    public List<AttendanceResponse> findAlerts() {
        return attendanceService.findAlerts();
    }

    @GetMapping("/monthly-report")
    @Operation(summary = "Relatorio mensal automatico de frequencia")
    public MonthlyAttendanceReportResponse getMonthlyReport(@RequestParam YearMonth month) {
        return attendanceService.getMonthlyReport(month);
    }
}
