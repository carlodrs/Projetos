package br.com.store.cms.spot.action;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.store.site.upload.action.BaseUploadActionSupport;

import com.opensymphony.xwork2.ActionSupport;
import com.ssj.persistence.product.entity.Category;
import com.ssj.persistence.spot.entity.BannerSlider;
import com.ssj.service.product.interfaces.CategoryService;
import com.ssj.service.spot.bean.BannerSliderBean;
import com.ssj.service.spot.interfaces.BannerSliderService;

/**
 * 
 * Content Banner 
 * @author Carlos Silva
 * @version 1.0
 * @since 1.0
 * @see ActionSupport
 * */
public class ContentBannerSliderCMSAction extends BaseUploadActionSupport {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	private BannerSlider banner;
	private List<BannerSlider> banners;
	private List<Category> categories; 
	
	
	private File image;
	private String imageFileName;
	private String imageContentType;
	
	//logger
	private static Logger logger = 
		Logger.getLogger(ContentBannerSliderCMSAction.class.getName());
	
	@Autowired
	private BannerSliderService bannerService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * @param imageFileName the imageFileName to set
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * @return the imageContentType
	 */
	public String getImageContentType() {
		return imageContentType;
	}

	/**
	 * @param imageContentType the imageContentType to set
	 */
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	/**
	 * @return the banner
	 */
	public BannerSlider getBanner() {
		return banner;
	}

	/**
	 * @param banner the banner to set
	 */
	public void setBanner(BannerSlider banner) {
		this.banner = banner;
	}

	/**
	 * @return the banners
	 */
	public List<BannerSlider> getBanners() {
		return banners;
	}

	/**
	 * @param banners the banners to set
	 */
	public void setBanners(List<BannerSlider> banners) {
		this.banners = banners;
	}
	
	/**
	 * List all banner registered
	 * @return String
	 * @throws Exception
	 */
	public String listAll() throws Exception{
		
		this.setBanners(this.bannerService.listAll());
		return SUCCESS;
	}
	
	/**
	 * List all banner registered
	 * @return String
	 * @throws Exception
	 */
	public String prepare() {
	
		try {
			this.setCategories(this.categoryService.listAllParents());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Cannot list all parent categories. " +
					"See log error stack trace", e);
			return ERROR;
		}
		return SUCCESS;

	}
	
	/**
	 * create the banner slider
	 * @return 
	 * @return String
	 * @throws Exception
	 */
	public String create() {
		
		BannerSliderBean bean = new BannerSliderBean();
		
		/* banner images configurations */
		if (this.getImageFileName() != null && (!this.getImageFileName().equals(""))){
			this.banner.setImage(this.getImageFileName());
		}
		
		bean.setBannerSlider(this.banner);
		try {
			this.uploadImageFiles(this.getServletRequest());
			this.bannerService.create(bean);
			this.addActionMessage(getText("banner.create.success"));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Fail to create the banner", e);
			this.addActionMessage(getText("banner.create.fail"));
			return ERROR;
		}
		return SUCCESS;
			
		
	}
	
	/**
	 * update the banner  slider registered
	 * @return String 
	 * @throws Exception
	 */
	public String update() {
		BannerSliderBean bean = new BannerSliderBean();
		bean.setId(this.banner.getId());
		try {
			/* load banner */
			bean = this.bannerService.load(bean);			
			BannerSlider loadedBanner = bean.getBannerSlider();
	
			/* banner images configurations */
			if (this.getImageFileName() != null && (!this.getImageFileName().equals(""))){
				this.banner.setImage(this.getImageFileName());
			}else{
				this.banner.setImage(loadedBanner.getImage());
			}
		
			/* set banner to bean */
			bean.setBannerSlider(this.banner);
		
			/* update */
			this.bannerService.update(bean);
			
			/* operation success */
			this.addActionMessage(this.getText("banner.detail.success"));
			
		} catch (Exception e) {
			
			/* log */
			logger.log(Level.SEVERE, "Fail to update the banner", e);
			
			/* apply message error */
			this.addActionError(this.getText("banner.detail.fail"));
		
			/* return the error string identifier */
			return ERROR;
		}
		return SUCCESS;

	}
	
	/**
	 * detele the banner slider registered
	 * @return String
	 * @throws Exception
	 */
	public String delete() {
		
		BannerSliderBean bean = new BannerSliderBean();
		bean.setBannerSlider(this.banner);
		try {
			this.bannerService.delete(bean);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Fail to delete the banner", e);
			this.addActionError(getText("error.delete.banner"));
			return ERROR;
		}
		
		this.addActionMessage(getText("success.delete.banner"));
		return SUCCESS;
	}

	
	/**
	 * Method to upload images to banner
	 * @return String
	 * @throws Exception;
	 * */
	public String updateImage() throws Exception{
		
		try {
	
			BannerSliderBean bannerSliderBean = new BannerSliderBean();
			bannerSliderBean.setId(this.banner.getId());
			bannerSliderBean = this.bannerService.load(bannerSliderBean);
			BannerSlider bannerLoaded = bannerSliderBean.getBannerSlider();
			
			/* banner images configurations */
			if (this.getImageFileName() != null && (!this.getImageFileName().equals(""))){
				bannerLoaded.setImage(this.getImageFileName());
			}
			//upload image
			this.uploadImageFiles(this.getServletRequest());
			
			//setting and updating banner
			bannerSliderBean.setBannerSlider(bannerLoaded);
			this.bannerService.update(bannerSliderBean);
			this.addActionMessage(this.getText("banner.images.success"));
			
			return SUCCESS;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on update image banner", e);
			
			this.addActionError(this.getText("banner.images.fail"));
			return ERROR;
		}
		
	}
	
	
	/**
	 * Show Images banner
	 * @return String
	 * @throws Exception
	 */
	public String showImage() throws Exception {
		
		try{
			
			BannerSliderBean bannerSliderBean = new BannerSliderBean();
			bannerSliderBean.setId(this.getBanner().getId());
			bannerSliderBean = this.bannerService.load(bannerSliderBean);

			this.setBanner(bannerSliderBean.getBannerSlider());
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			
			this.addActionError(getText("banner.images.view.fail"));
			return ERROR;
		}		
	}

	/**
	 * Prepare to show banner detail
	 * @return String
	 * @throws Exception
	 */
	public String detail() throws Exception {
		
		try{
			BannerSliderBean bannerSliderBean = new BannerSliderBean();
			bannerSliderBean.setId(this.getBanner().getId());
			
			bannerSliderBean = this.bannerService.load(bannerSliderBean);
			
			this.setBanner(bannerSliderBean.getBannerSlider());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(getText("banner.detail.fail"));
			return ERROR;
		}		
	}
	
	
}
