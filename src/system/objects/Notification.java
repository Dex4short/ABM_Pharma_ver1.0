package system.objects;

import javax.swing.ImageIcon;

import components._misc_.Graphix;
import res.Resource;
import system.enumerators.Quality;
import system.managers.QualityManager;
import system.ui.appearance.Theme;

public class Notification {
	private static final ImageIcon
	stock_warning_icon = Graphix.alterImageIcon(Resource.getAsImageIcon("out_of_stock.png"), Theme.accent_color[0][2], null),
	stock_caution_icon = Graphix.alterImageIcon(Resource.getAsImageIcon("out_of_stock.png"), Theme.accent_color[0][3], null),
	warning_icon = Resource.getAsImageIcon("warning.png"),
	caution_icon = Resource.getAsImageIcon("caution.png");
	private String title, details;
	private ImageIcon imageIcon;
	private Product product;
	
	public Notification(Product product, ImageIcon imageIcon,String title, String Details) {
		setProduct(product);
		setImageIcon(imageIcon);
		setTitle(title);
		setDetails(Details);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public static class RunningOutOfStock extends Notification{
		public RunningOutOfStock(Product product) {
			super(
				product,
				stock_warning_icon,
				"Warning! Running out of stock...", 
				"Item No.: " + product.getItem().getItemNo() + "\n" +
				product.getItem().getDescription() + "\n" +
				product.getItem().getBrand() + "\n" +
				product.getPackaging().getQty().toString() + " " + product.getPackaging().getUom().getUnitType().name()
			);
		}
	}	
	public static class OutOfStock extends RunningOutOfStock{
		public OutOfStock(Product product) {
			super(product);
			setImageIcon(stock_caution_icon);
			setTitle("OUT OF STOCK!");
		}
	} 
	public static class ProductQuality extends Notification{
		public ProductQuality(Product product) {
			super(product, null, null, null);
			
			Quality quality = QualityManager.checkQuality(product.getItem().getExpDate());
			
			ImageIcon img_icon = null;
			
			switch (quality) {
			case Warning:
				img_icon = warning_icon;
				setImageIcon(Graphix.alterImageIcon(img_icon, Theme.accent_color[0][2], null));
				setTitle("Warning!");
				break;
			case Bad:
				img_icon = caution_icon;
				setImageIcon(Graphix.alterImageIcon(img_icon, Theme.accent_color[0][3], null));
				setTitle("Caution! Bad quality condition...");
				break;
			case Expired:
				img_icon = caution_icon;
				setImageIcon(Graphix.alterImageIcon(img_icon, Theme.accent_color[0][3], null));
				setTitle("EXPIRED!");
				break;
			default:
				break;
			}
			
			setDetails(
				"Item No.: " + product.getItem().getItemNo() + "\n" +
				product.getItem().getDescription() + "\n" +
				product.getItem().getBrand() + "\n" +
				"Date Expiry: " + product.getItem().getExpDate()
			);
		}
	}	
	
}
