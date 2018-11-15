	package no.systema.jservices.nortrailstats.dto;

import java.math.BigDecimal;
import java.util.Map;

import no.systema.jservices.common.dao.IDao;
import lombok.Data;

/**
 * * This is the Data Transfer Object between service and UI delivering data
 * Specific to Nortrail STATs on Turer (TEI=bilar til terminal and DIREKT=bilar direkt körning)
 * 
 * @author Oscar de la Torre
 * @date Nov, 2018
 *
 */
@Data
public class NortrailStatsTurerHeadfTrackfDto implements IDao {
	
	
	//HEADF
	private String heavd = ""; //Key	
	private String heopd = ""; //Key	
	private String hesg = "";  //sign		
	private String hedtop = ""; //datum
	private String heur = ""; //Transp.typ (A=Land Import...)
	private String hegn = ""; //Godsnr
	private String hesdl = ""; //'%DIREKT%' eller inte (turer som är direkta)
	private String henas = ""; //Avs	
	private String henak = ""; //Mott.	
	private String hent = ""; //antal	
	private String hevkt = ""; //Vikt	
	private String hem3 = ""; //M3
	
	//TURER
	private String tuavd = ""; //Key	
	private String tupro = ""; //Key	
	private String tusg = ""; //sign	
	private String tubiln = ""; //bilnr
	private String tuopdt = ""; //Opp.type	
	private String tustef = ""; //Fra	
	private String tudt = ""; //Fra dato	
	private String tudtt = ""; //ETA	
	private String tustet = ""; //Til	
	private String turund = ""; //Rundtur	
	private String tutvkt = ""; //Vikt	
	private String tutm3 = "";//M3	
	private String tutlm2 = "";//Lm 
	//TRACKF
	private String ttavd = ""; //Key	
	private String ttopd = ""; //Key
	private String ttdate = ""; //ATA	
	private String ttacti = ""; //Type (TEI or not)
	//FORARF
	private String botur = ""; //Key
	private String boavd = ""; //Key	
	private String bodta = ""; //ATA	
	private String bokna = ""; //Agentnr
	//Alias
	private String dager = ""; //alias to operational columns (with SQL-functions)
	private String eta = ""; //alias 
	private String ata = ""; //alias 
	//Variable String related parameterized SQL words
	private String qllike = ""; //blank=LIKE       -1=NOT LIKE
	
	
	@Override
	public Map<String, Object> getKeys() {
		return null;
	}
}
