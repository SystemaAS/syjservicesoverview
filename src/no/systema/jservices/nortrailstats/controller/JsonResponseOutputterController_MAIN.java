package no.systema.jservices.nortrailstats.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import no.systema.jservices.entities.JsonGenericContainerDao;
import no.systema.jservices.common.dao.services.BridfDaoService;
import no.systema.jservices.nortrailstats.services.NortrailStatsMainObjectDaoService;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.nortrailstats.dto.NortrailStatsTurerHeadfTrackfDto;
import no.systema.jservices.common.util.StringUtils;


/**
 * 
 * @author oscardelatorre
 * @date Nov 2018
 */
@RestController
public class JsonResponseOutputterController_MAIN {
	private static final Logger logger = Logger.getLogger(JsonResponseOutputterController_MAIN.class.getName());
	private DateTimeManager dateManager = new DateTimeManager();
	
	@Autowired
	private NortrailStatsMainObjectDaoService nortrailStatsMainObjectDaoService;
	@Autowired
	private BridfDaoService bridfDaoService;

	
	/**
	 * Db-file: 	TURER/HEADF/TRACKF/FORARF...
	 * Aim: STATs Nortrail specific
	 * 
	 * @Example 
	 * (1) SELECT list http://gw.systema.no:8080/syjservicesoverview/syjsSYNORTRMAIN.do?user=OSCAR
	 * 
	 * @return
	 */
	@RequestMapping(path="/syjsSYNORTRMAIN.do",method = { RequestMethod.GET, RequestMethod.POST } )
	public JsonGenericContainerDao getSynortrmain(@RequestParam("user") String user, HttpSession session, HttpServletRequest request ){
		JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto> container = new JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto>();
		
		List<NortrailStatsTurerHeadfTrackfDto> list = new ArrayList<NortrailStatsTurerHeadfTrackfDto>();
		
		try {
			logger.info("Inside syjsSYNORTRMAIN.do");
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoService.getUserName(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName)) {
				NortrailStatsTurerHeadfTrackfDto dao = new NortrailStatsTurerHeadfTrackfDto();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				if(!StringUtils.hasValue(dao.getTudtt())){
					dao.setTudtt(dateManager.getNewDateFromNow(-30));
				}
				list = this.fetchRecords(dao);
				
				if (list != null) {
					container.setUser(user);
					container.setList(list);
				} else {
					errMsg = "ERROR on SELECT: Can not find Dao list";
					status = "error";
					logger.info(status + errMsg);
					//sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					container.setErrMsg(errMsg);
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user> ...");
				//sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				container.setErrMsg(errMsg + dbErrorStackTrace.toString());
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			//return "ERROR [JsonResponseOutputterController]" + writer.toString();
			container.setErrMsg("ERROR [JsonResponseOutputterController]" + writer.toString());
		}
		session.invalidate();
		return container;
	}
	
	/**
	 * 
	 * @param gogn
	 * @param gotrnr
	 * @param dftdg
	 * @param dao
	 * @param gogn2
	 * @return
	 */
	private List<NortrailStatsTurerHeadfTrackfDto> fetchRecords(NortrailStatsTurerHeadfTrackfDto dao) {
		List<NortrailStatsTurerHeadfTrackfDto> list = new ArrayList<NortrailStatsTurerHeadfTrackfDto>();
		Calendar calendar = Calendar.getInstance();
		//String ORDER_BY_DESC = "order by gogn desc";
		
		/*
		if(StringUtils.hasValue(gogn)){
			logger.info("MATCH: gogn");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("gogn", gogn + "%");
			if(gotrnr!=null){ //in this special case we must allow empty string but not NULL
				params.put("gotrnr", gotrnr + "%");
			}
			if(StringUtils.hasValue(gogn2)){
				//Special case when search must be within a gogn interval
				list = godsjfDaoService.findGognInterval(gogn2, dao);
			}else{
				//Generic find
				list = godsjfDaoService.findAll(params);
			}
			
		}else if(filterExists(dao)){
			logger.info("Filter in action...");
			Map<String, Object> params = this.getParams(dao); 
			list = godsjfDaoService.findAll(params, new StringBuffer(ORDER_BY_DESC));
		
		}else if(StringUtils.hasValue(dftdg)){
			logger.info("DEFAULT from x-days to now");
			String currentYear = String.valueOf(calendar.get(Calendar.YEAR)); 
			list = godsjfDaoService.findDefault(currentYear, this.getFromDay(dftdg), dao);
			logger.info("LIST SIZE:" + list.size());
		}else{
			Map<String, Object> params = this.getParams(dao);
			if(params != null && params.size()>0){
				list = godsjfDaoService.findAll(params, new StringBuffer(ORDER_BY_DESC));
			}else{
				logger.info("ALL RECORDS...");
				list = godsjfDaoService.findAll(this.ALL_RECORDS, new StringBuffer(ORDER_BY_DESC));
			}
		}
		*/
		
		list = nortrailStatsMainObjectDaoService.findTurerTEI(dao);
		logger.info(list.size());
		return list;
	}
	
	
	
}
