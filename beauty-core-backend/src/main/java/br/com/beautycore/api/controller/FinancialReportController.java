package br.com.beautycore.api.controller;

import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.projections.TotalProfitProfessionalProjection;
import br.com.beautycore.api.services.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/{professionalId}/professional-total-profit-in-live")
    public ResponseEntity<TotalProfitProfessionalProjection> getProfessionalTotalProfitInLive(@PathVariable Long professionalId) {
        var result = service.getProfessionalTotalProfitInLive(professionalId);

        return ResponseEntity.ok(result);
    }
}