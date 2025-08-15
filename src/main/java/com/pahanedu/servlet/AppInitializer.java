package com.pahanedu.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.pahanedu.business.bill.service.BillingService;
import com.pahanedu.persistence.observer.SalesReportObserver;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Create BillingService instance
        BillingService billingService = new BillingService();

        // Register SalesReportObserver
        billingService.addObserver(new SalesReportObserver());

        // Store BillingService in ServletContext for global access
        sce.getServletContext().setAttribute("billingService", billingService);

        System.out.println("SalesReportObserver registered and listening to BillingService.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources if necessary
    }
}
