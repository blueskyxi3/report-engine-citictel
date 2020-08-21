package com.citictel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.citictel.report.jobhandler.ReportJobHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportSrpingBootApplicationTests {

	@Autowired
	ReportJobHandler reportJobHandler;
	
    @Test
    public void contextLoads() {
    	String content = "{\"reportName\":\"DCO_Monthly_Provisioning.html\",\"datasource\":\"CDR\",\"params\":{\"startDate\":\"$M-1-S\",\"endDate\":\"$M-1-E\"},\"to\":[\"vincentzou@citictel.com\"],\"cc\":[\"leozou@citictel.com\"],\"subject\":\"IDC-BSS DCO Monthly Provisioning Records\",\"content\":\"Dir sir,\\n Attached the last month IDC-BSS DCO Monthly Provisioning Records.\"}{\"reportName\":\"DCO_Monthly_Provisioning.html\",\"datasource\":\"CDR\",\"params\":{\"startDate\":\"$M-1-S\",\"endDate\":\"$M-1-E\"},\"to\":[\"vincentzou@citictel.com\"],\"cc\":[\"leozou@citictel.com\"],\"subject\":\"IDC-BSS DCO Monthly Provisioning Records\",\"content\":\"Dir sir,\\n Attached the last month IDC-BSS DCO Monthly Provisioning Records.\"}";
    	
    	reportJobHandler.execute(content);
    	
    }
   
}

