package br.com.store.cms.spot.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.ssj.persistence.spot.entity.ContentSpot;
import com.ssj.persistence.spot.entity.Spot;
import com.ssj.service.spot.bean.SpotBean;
import com.ssj.service.spot.interfaces.ContentSpotService;
import com.ssj.service.spot.interfaces.SpotService;

/**
 * 
 * Spot Action CMS to register the spot 
 * @author Carlos Silva
 * @version 1.0
 * @since 1.0
 * @see ActionSupport
 * */
public class SpotCMSAction extends ActionSupport {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	private Spot spot;
	private ContentSpot contentSpot;
	private List<ContentSpot> contentSpots;
	private List<String> activeOptions;
	private List<Spot> spots;
	//logger
	private static Logger logger = 
			Logger.getLogger(SpotCMSAction.class.getName());
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private ContentSpotService contentSpotService;
	
	public SpotCMSAction(){
		this.activeOptions = new ArrayList<String>();
		this.activeOptions.add(getText("spot.activate"));
		this.activeOptions.add(getText("spot.deactivate"));
	}
	
	
	/**
	 * @return the spots
	 */
	public List<Spot> getSpots() {
		return spots;
	}

	/**
	 * @param spots the spots to set
	 */
	public void setSpots(List<Spot> spots) {
		this.spots = spots;
	}

	
	/**
	 * @return the contentSpot
	 */
	public ContentSpot getContentSpot() {
		return contentSpot;
	}


	/**
	 * @param contentSpot the contentSpot to set
	 */
	public void setContentSpot(ContentSpot contentSpot) {
		this.contentSpot = contentSpot;
	}

	/**
	 * @return the activeOptions
	 */
	public List<String> getActiveOptions() {
		return activeOptions;
	}


	/**
	 * @param activeOptions the activeOptions to set
	 */
	public void setActiveOptions(List<String> activeOptions) {
		this.activeOptions = activeOptions;
	}


	@Override
	public String execute() throws Exception {
		addActionMessage(getText("spot.select.option"));
		return INPUT;
	}
	
	/**
	 * List all spots registered on the system
	 * @return String success or error
	 * @exception Exception
	 */
	public String listAll() throws Exception {
		try {
			this.setSpots(this.spotService.listAll());
			return SUCCESS;	
		} catch (Exception e) {
			return ERROR;
		}
	}

	

	/**
	 * @return the spot
	 */
	public Spot getSpot() {
		return spot;
	}


	/**
	 * @param spot the spot to set
	 */
	public void setSpot(Spot spot) {
		this.spot = spot;
	}


	/**
	 * @return the contentSpots
	 */
	public List<ContentSpot> getContentSpots() {
		return contentSpots;
	}


	/**
	 * @param contentSpots the contentSpots to set
	 */
	public void setContentSpots(List<ContentSpot> contentSpots) {
		this.contentSpots = contentSpots;
	}


	/**
	 * Method to create category
	 * @return 
	 * @throws Exception 
	 * */
	public String create() throws Exception {
		
		List<ContentSpot> contents = new ArrayList<ContentSpot>();
		contents.add(this.contentSpot);
		
		Spot spot = new Spot();
		spot.setSpotName(this.spot.getSpotName());
		spot.setActive(this.spot.isActive());
		spot.setSpotDescription(this.spot.getSpotDescription());
		spot.setContentSpots(contents);
		
		SpotBean spotBean = new SpotBean();
		spotBean.setSpot(spot);
		
		try {
			this.spotService.create(spotBean);
			addActionMessage(getText("spot.create.success"));
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("error.spot.create"));
			return ERROR;
		}
	}
	
	
	/**
	 * Prepare to show the spot detail
	 * @return String
	 * @throws Exception
	 */
	public String detail() throws Exception {
		
		try{
			SpotBean spotBean = new SpotBean();
			spotBean.setId(getSpot().getId());
			spotBean = this.spotService.load(spotBean);
			
			Spot spot = spotBean.getSpot();
			ContentSpot contentSpot = new ContentSpot();
			for (ContentSpot content : spot.getContentSpots()) {
				spotBean.setId(content.getId());
				contentSpot = this.contentSpotService.load(spotBean).getContentSpot();
			}
			this.setContentSpots(this.contentSpotService.listAll());
			this.setContentSpot(contentSpot);
			this.setSpot(spot);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(getText("error.detail.spot"));
			return ERROR;
		}		
	}
	
	/**
	 * Update the spot data
	 * @return String
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		SpotBean bean = new SpotBean();
		bean.setId(this.spot.getId());
		bean = this.spotService.load(bean);
		
		Spot spot = bean.getSpot();
		spot.setSpotName(this.spot.getSpotName());
		spot.setSpotDescription(this.spot.getSpotDescription());
		
		if (this.contentSpot.getId() != null){
			List<ContentSpot> contentSpots =  new ArrayList<ContentSpot>();
			contentSpots.add(this.contentSpot);
			spot.setContentSpots(contentSpots);
		}else{
			spot.setContentSpots(null);
		}
		
		bean.setSpot(spot);
		
		try {
			//update spot data
			this.spotService.update(bean);
			this.addActionMessage(getText("sucess.update.spot"));	
		} catch (Exception e) {
			this.addActionError(getText("error.update.spot"));
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * Prepare to create spot
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		this.setContentSpots(this.contentSpotService.listAll());
		this.addActionMessage(getText("spot.page"));
		return INPUT;
	}
	
	/**
	 * Delete the  spot regitered on the system
	 * @return String success or error
	 * @exception Exception
	 */
	public String delete() throws Exception {
		try {
			
			SpotBean bean = new SpotBean();
			bean.setSpot(this.getSpot());
			this.spotService.delete(bean);
			this.addActionMessage(getText("delete.spot.success"));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error to delete the spot", e);
			this.addActionError(getText("delete.spot.error"));
			return ERROR;
		}
		return SUCCESS;	
	}
}
