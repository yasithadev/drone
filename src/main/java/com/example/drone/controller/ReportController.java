package com.example.drone.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.dto.BatteryCapacityDto;
import com.example.drone.model.persistent.BatteryCapacity;
import com.example.drone.model.persistent.Drone;
import com.example.drone.model.view.BatteryLevelAuditReportVm;
import com.example.drone.service.LoadService;
import com.example.drone.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@GetMapping("/drone-battery-level-audit")
	public List<BatteryCapacityDto>  droneBatteryLevelAuditReport(@RequestBody BatteryLevelAuditReportVm batteryLevelAuditReportVm){
		try {
			return reportService.generateBatteryLevelAuditReport(batteryLevelAuditReportVm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
