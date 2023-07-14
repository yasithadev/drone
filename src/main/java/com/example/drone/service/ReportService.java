package com.example.drone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drone.model.dto.BatteryCapacityDto;
import com.example.drone.model.persistent.BatteryCapacity;
import com.example.drone.model.viewmodel.BatteryLevelAuditReportVm;
import com.example.drone.repository.BatteryCapacityRepository;

import java.text.SimpleDateFormat;  
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ReportService {
	  @Autowired
	  private BatteryCapacityRepository batteryCapacityRepository;
	  
	public List<BatteryCapacityDto> generateBatteryLevelAuditReport(BatteryLevelAuditReportVm batteryLevelAuditReportVm) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
		System.out.println("batteryLevelAuditReportVm.getStartDate()" + batteryLevelAuditReportVm.getStartDate());
		Date startDate = formatter.parse(batteryLevelAuditReportVm.getStartDate());
		System.out.println("batteryLevelAuditReportVm.getEndDate()" + batteryLevelAuditReportVm.getEndDate());
		Date endDate = formatter.parse(batteryLevelAuditReportVm.getEndDate());
		List<BatteryCapacity> batteryCapacitys =  batteryCapacityRepository.findAllByCreatedTimeBetween(startDate ,endDate);
		List<BatteryCapacityDto> batteryCapacityDtos = new ArrayList<BatteryCapacityDto>();
		 for(BatteryCapacity batteryCapacity: batteryCapacitys) {
			 BatteryCapacityDto batteryCapacityDto = new BatteryCapacityDto();
			 batteryCapacityDto.setBatteryCapacityId(batteryCapacity.getBatteryCapacityId());
			 batteryCapacityDto.setDroneId(batteryCapacity.getDroneId().getDroneId());
			 batteryCapacityDto.setPercentage(batteryCapacity.getPercentage());
			 batteryCapacityDto.setCreatedTime(batteryCapacity.getCreatedTime());
			 batteryCapacityDtos.add(batteryCapacityDto);
		 }
		 return batteryCapacityDtos;
	}
}
