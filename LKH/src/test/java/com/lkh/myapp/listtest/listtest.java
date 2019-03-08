package com.lkh.myapp.listtest;



import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lkh.myapp.aboard.dao.A_bbsDAOImplXML;
import com.lkh.myapp.gboard.dao.GbbsDAOImplXML;
import com.lkh.myapp.gboard.dto.GbbsDTO;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
class listtest {
	
	@Inject
	GbbsDAOImplXML xml;
	
	@Test
	void testz() {
		GbbsDTO gbbsDTO = new GbbsDTO();
		for(int i = 1; i<30; i++) {
			gbbsDTO.setId("111"+i);
			gbbsDTO.setPw("111"+i);
			gbbsDTO.setContent("1111");
			gbbsDTO.setTitle("111"+i);
			try {
				xml.write(gbbsDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
