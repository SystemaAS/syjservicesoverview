package no.systema.jservices.nortrailstats.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.nortrailstats.dto.NortrailStatsTurerHeadfTrackfDto;
import no.systema.jservices.common.dao.GenericObjectMapper;
import no.systema.jservices.common.util.StringUtils;

public class NortrailStatsMainObjectDaoServiceImpl implements NortrailStatsMainObjectDaoService{
	private static final Logger logger = Logger.getLogger(NortrailStatsMainObjectDaoServiceImpl.class.getName());
	
	@Override
	public List<NortrailStatsTurerHeadfTrackfDto> findTurerTEI(NortrailStatsTurerHeadfTrackfDto dao) {
		//TEST StringBuilder queryString = new StringBuilder("SELECT tudtt from turer ");
		//TEST queryString.append(" where tudtt > ? ");
		
		
		//SQL syntax variable in order to use the same select with different deltas
		String TEI_TUR = "TEI";
		if("".equals(dao.getQllike()) ){ 
			dao.setQllike("LIKE");
		}else{
			dao.setQllike("NOT LIKE");
		}
		
		
		//START
		StringBuilder queryString = new StringBuilder();
		//info width 
		queryString.append(" SELECT  a.tudtt eta, c.ttdate ata, a.tupro, c.ttacti, b.hesdl, b.heavd, b.heopd, ");
		queryString.append(" a.tusg, a.tubiln, a.tuopdt, a.tustef, a.tudt, a.tustet, a.tudtt,  ");
		queryString.append(" a.turund, a.tutvkt, a.tutm3, a.tutlm2, ");
		queryString.append(" c.ttavd, c.ttopd , ");
		//date functions
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(tudtt as varchar(8)), 'YYYYMMDD')) ETAdateformat, ");                                  
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(ttdate  as varchar(8)),'YYYYMMDD')) ATAdateformat, ");                                  
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(ttdate as varchar(8)), 'YYYYMMDD'))- DATE( TIMESTAMP_FORMAT(cast(tudtt  as varchar(8)), 'YYYYMMDD'))dager, c.ttacti, b.hesdl ");
		//WHERE-clause
		queryString.append(" FROM turer a, headf b, trackf c ");                                
		queryString.append(" where a.tupro = b.hepro ");                                        
		queryString.append(" and UPPER(c.ttacti) " + dao.getQllike() + "'%" + TEI_TUR + "%'");                              
		queryString.append(" and a.tuavd = b.heavd  ");                                         
		queryString.append(" and c.ttopd = b.heopd  ");                                         
		queryString.append(" and c.ttavd = b.heavd   ");                                        
		queryString.append(" and a.tudtt >= ?  ");                                        
		//queryString.append(" and a.tudtt <> c.ttdate "); 
		
		/*
		if(dao.getGotrnr()!=null){ //we must allow 'blank'
			queryString.append(" and gotrnr =  '" + dao.getGotrnr() + "'" );
		}
		if(StringUtils.hasValue(dao.getGobiln())){
			queryString.append(" and gobiln =  '" + dao.getGobiln() + "'" );
		}
		if(StringUtils.hasValue(dao.getGomott())){
			queryString.append(" and gomott =  '" + dao.getGomott() + "%'" );
		}
		if(StringUtils.hasValue(dao.getGoturn())){
			queryString.append(" and goturn =  '" + dao.getGoturn() + "'" );
		}
		queryString.append(" order by GOGN desc ");
		*/
		logger.info(queryString.toString());
		
		return getJdbcTemplate().query( queryString.toString(), new Object[] { dao.getTudtt()  }, new GenericObjectMapper(new NortrailStatsTurerHeadfTrackfDto()));
		
	}
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public List<NortrailStatsTurerHeadfTrackfDto> findTurerDIREKTE(NortrailStatsTurerHeadfTrackfDto dao) {
		//TEST StringBuilder queryString = new StringBuilder("SELECT tudtt from turer ");
		//TEST queryString.append(" where tudtt > ? ");
		
		
		//SQL syntax variable in order to use the same select with different deltas
		String DIREKT_TUR = "DIREKT";
		if("".equals(dao.getQllike()) ){ 
			dao.setQllike("LIKE");
		}else{
			dao.setQllike("NOT LIKE");
		}
		
		
		//START
		StringBuilder queryString = new StringBuilder();
		//info width 
		queryString.append(" SELECT  a.tudtt eta, c.ttdate ata, a.tupro, c.ttacti, b.hesdl, b.heavd, b.heopd, ");
		queryString.append(" a.tusg, a.tubiln, a.tuopdt, a.tustef, a.tudt, a.tustet, a.tudtt,  ");
		queryString.append(" a.turund, a.tutvkt, a.tutm3, a.tutlm2, ");
		queryString.append(" c.ttavd, c.ttopd , ");
		//date functions
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(tudtt as varchar(8)), 'YYYYMMDD')) ETAdateformat, ");                                  
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(ttdate  as varchar(8)),'YYYYMMDD')) ATAdateformat, ");                                  
		queryString.append(" DATE( TIMESTAMP_FORMAT(cast(ttdate as varchar(8)), 'YYYYMMDD'))- DATE( TIMESTAMP_FORMAT(cast(tudtt  as varchar(8)), 'YYYYMMDD'))dager, c.ttacti, b.hesdl ");
		//WHERE-clause
		queryString.append(" FROM turer a, headf b, trackf c ");                                
		queryString.append(" where a.tupro = b.hepro ");                                        
		queryString.append(" and UPPER(b.hesdl) " + dao.getQllike() + "'%" + DIREKT_TUR + "%'");                              
		queryString.append(" and a.tuavd = b.heavd  ");                                         
		queryString.append(" and c.ttopd = b.heopd  ");                                         
		queryString.append(" and c.ttavd = b.heavd   ");                                        
		queryString.append(" and a.tudtt >= ?  ");                                        
		//queryString.append(" and a.tudtt <> c.ttdate "); 
		
		/*
		if(dao.getGotrnr()!=null){ //we must allow 'blank'
			queryString.append(" and gotrnr =  '" + dao.getGotrnr() + "'" );
		}
		if(StringUtils.hasValue(dao.getGobiln())){
			queryString.append(" and gobiln =  '" + dao.getGobiln() + "'" );
		}
		if(StringUtils.hasValue(dao.getGomott())){
			queryString.append(" and gomott =  '" + dao.getGomott() + "%'" );
		}
		if(StringUtils.hasValue(dao.getGoturn())){
			queryString.append(" and goturn =  '" + dao.getGoturn() + "'" );
		}
		queryString.append(" order by GOGN desc ");
		*/
		logger.info(queryString.toString());
		
		return getJdbcTemplate().query( queryString.toString(), new Object[] { dao.getTudtt()  }, new GenericObjectMapper(new NortrailStatsTurerHeadfTrackfDto()));
		
	}
	
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	
	
}
