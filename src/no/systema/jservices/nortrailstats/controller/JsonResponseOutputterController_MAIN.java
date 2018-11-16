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
	private Integer DAFAULT_NUMBER_OF_DAYS_SQL = -10;
	
	@Autowired
	private NortrailStatsMainObjectDaoService nortrailStatsMainObjectDaoService;
	@Autowired
	private BridfDaoService bridfDaoService;

	
	/**
	 * Db-file: 	TURER/HEADF/TRACKF/FORARF...
	 * Aim: STATs Nortrail specific
	 * 
	 * @Example 
	 * (1) SELECT list http://gw.systema.no:8080/syjservicesoverview/syjsSYNORTRMAIN_TEI.do?user=OSCAR
	 * 
	 * TEI - TURER
	 * @return
	 */
	@RequestMapping(path="/syjsSYNORTRMAIN_TEI.do",method = { RequestMethod.GET, RequestMethod.POST } )
	public JsonGenericContainerDao getSyNortrMainTei(@RequestParam("user") String user, HttpSession session, HttpServletRequest request ){
		JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto> container = new JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto>();
		
		List<NortrailStatsTurerHeadfTrackfDto> list = new ArrayList<NortrailStatsTurerHeadfTrackfDto>();
		
		try {
			logger.info("Inside syjsSYNORTRMAIN_TEI.do");
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoService.getUserName(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName)) {
				NortrailStatsTurerHeadfTrackfDto dao = new NortrailStatsTurerHeadfTrackfDto();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				//EXECUTE
				this.setSQLFilter(dao);
				list = nortrailStatsMainObjectDaoService.findTurerTEI(dao);
				
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
	 * Db-file: 	TURER/HEADF/TRACKF/FORARF...
	 * Aim: STATs Nortrail specific
	 * 
	 * DIREKT - TURER
	 * 
	 * @Example 
	 * (1) SELECT list http://gw.systema.no:8080/syjservicesoverview/syjsSYNORTRMAIN_DIR.do?user=OSCAR
	 * @param user
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/syjsSYNORTRMAIN_DIR.do",method = { RequestMethod.GET, RequestMethod.POST } )
	public JsonGenericContainerDao getSyNortrMainDir(@RequestParam("user") String user, HttpSession session, HttpServletRequest request ){
		JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto> container = new JsonGenericContainerDao<NortrailStatsTurerHeadfTrackfDto>();
		
		List<NortrailStatsTurerHeadfTrackfDto> list = new ArrayList<NortrailStatsTurerHeadfTrackfDto>();
		
		try {
			logger.info("Inside syjsSYNORTRMAIN_DIR.do");
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoService.getUserName(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName)) {
				NortrailStatsTurerHeadfTrackfDto dao = new NortrailStatsTurerHeadfTrackfDto();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				//EXECUTE
				this.setSQLFilter(dao);
				list = nortrailStatsMainObjectDaoService.findTurerDIREKTE(dao);
				
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
	 * Filter population for SQL
	 * @param dao
	 */
	private void setSQLFilter(NortrailStatsTurerHeadfTrackfDto dao){
		//fromDate/ toDate
		if(!StringUtils.hasValue(dao.getFromDate())){
			dao.setFromDate(dateManager.getNewDateFromNow(this.DAFAULT_NUMBER_OF_DAYS_SQL));
		}
		if(!StringUtils.hasValue(dao.getToDate())){
			dao.setToDate(dateManager.getCurrentDate_ISO());
		}
		//days back (if applicable)
		if(dao.getNrDaysBack()!=0){
			dao.setFromDate( dateManager.getNewDateFromNow(dao.getNrDaysBack()) );
			
		}
	}
	
}
