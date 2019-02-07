
public class Publisher {
	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private int publisherPhone;
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	public int getPublisherPhone() {
		return publisherPhone;
	}
	public void setPublisherPhone(int publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherName=" + publisherName + ", publisherAddress="
				+ publisherAddress + ", publisherPhone=" + publisherPhone + "]";
	}
}
