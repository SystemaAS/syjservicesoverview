package no.systema.jservices.nortrailstats.services;


import java.util.List;

import no.systema.jservices.nortrailstats.dto.NortrailStatsTurerHeadfTrackfDto;


/**
 * 
 * @author oscardelatorre
 * @date Jun-2018
 */
public interface NortrailStatsMainObjectDaoService  {
	
	public List<NortrailStatsTurerHeadfTrackfDto> findTurerTEI(NortrailStatsTurerHeadfTrackfDto dao);
	public List<NortrailStatsTurerHeadfTrackfDto> findTurerDIREKTE(NortrailStatsTurerHeadfTrackfDto dao);

}
