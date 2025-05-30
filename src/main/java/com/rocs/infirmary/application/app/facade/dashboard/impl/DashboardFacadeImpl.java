package com.rocs.infirmary.application.app.facade.dashboard.impl;

import com.rocs.infirmary.application.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.application.data.dao.report.dashboard.DashboardReports;
import com.rocs.infirmary.application.data.dao.report.dashboard.impl.DashboardReportsImpl;
import com.rocs.infirmary.application.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.application.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.application.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.application.data.model.report.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DashboardFacadeImpl class is an implementation of the DashboardFacade interface.
 * It provides methods for managing reports and notification.
 */
public class DashboardFacadeImpl implements DashboardFacade {

    /** The data access object for Dashboard. */
    private final DashboardReports dashboard = new DashboardReportsImpl();
    private static final Logger logger = LoggerFactory.getLogger(DashboardFacadeImpl.class);

    @Override
    public List<LowStockReport> getAllLowStockMedicine() {
        logger.info("Entering getAllLowStockMedicine");
        List<LowStockReport> lowStockItems = dashboard.findAllLowStockMedicine();
        logger.info("Exiting getAllLowStockMedicine with {} items found.", lowStockItems.size());
        return lowStockItems;
    }

    @Override
    public List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section) {
        logger.info("Entering generateCommonAilmentReport with startDate: {}, endDate: {}, gradeLevel: {}, section: {}", startDate, endDate, gradeLevel, section);
        List<CommonAilmentsReport> report = this.dashboard.findCommonAilmentReport(startDate, endDate, gradeLevel, section);
        logger.info("Exiting generateCommonAilmentReport with {} records found.", report.size());
        return report;
    }

    @Override
    public List<FrequentVisitReport> generateFrequentVisitReport(Date startDate, Date endDate, String gradeLevel) {
        logger.info("Entering generateFrequentVisitReport with startDate: {}, endDate: {}, gradeLevel: {}", startDate, endDate, gradeLevel);
        List<FrequentVisitReport> frequentVisitReportList = this.dashboard.findFrequentVisit(gradeLevel, startDate, endDate);
        logger.info("Exiting generateFrequentVisitReport with {} records found.", frequentVisitReportList.size());
        return frequentVisitReportList;
    }

    @Override
    public List<MedicationTrendReport> generateMedicationReport(Date startDate, Date endDate) {
        logger.info("Entering generateMedicationReport with startDate: {}, endDate: {}", startDate, endDate);
        List<MedicationTrendReport> medicationTrendReportList = dashboard.findMedicationTrend(startDate, endDate);
        logger.info("Exiting generateMedicationReport with {} records found.", medicationTrendReportList.size());
        return medicationTrendReportList;
    }
}
