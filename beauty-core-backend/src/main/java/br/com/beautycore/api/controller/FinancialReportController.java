package br.com.beautycore.api.controller;

import br.com.beautycore.api.projections.TotalProfitByProfessionalProjection;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.services.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/financial-reports")
@RequiredArgsConstructor
public class FinancialReportController {

    private final FinancialReportService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/total-profit-in-live")
    public ResponseEntity<TotalProfitInLiveProjection> getTotalProfitInLive() {
        var result = service.getTotalProfitInLive();

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("#professionalId == authentication.principal.id or hasRole('ADMIN'")
    @GetMapping("/professionals/{professionalId}/total-profit")
    public ResponseEntity<TotalProfitByProfessionalProjection> getTotalProfitByProfessional(
            @PathVariable Long professionalId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {
        var result = service.getTotalProfitByProfessional(professionalId, start, end);
        return ResponseEntity.ok(result);
    }
}