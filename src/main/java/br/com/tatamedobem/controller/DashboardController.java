package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.DashboardSummaryResponse;
import br.com.tatamedobem.dto.SponsorReportResponse;
import br.com.tatamedobem.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    @Operation(summary = "Resumo geral do projeto")
    public DashboardSummaryResponse summary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/sponsor-report")
    @Operation(summary = "Relatorio para patrocinadores e editais")
    public SponsorReportResponse sponsorReport() {
        return dashboardService.getSponsorReport();
    }
}
