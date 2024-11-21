package com.ssafy.cafe.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBook {
    private String id;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
    private AccessInfo accessInfo;
    private SearchInfo searchInfo;
    
    public GoogleBook() {}
    
	public GoogleBook(String id, VolumeInfo volumeInfo, SaleInfo saleInfo, AccessInfo accessInfo, SearchInfo searchInfo) {
		super();
		this.id = id;
		this.volumeInfo = volumeInfo;
		this.saleInfo = saleInfo;
		this.accessInfo = accessInfo;
		this.searchInfo = searchInfo;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}
	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	public SaleInfo getSaleInfo() {
		return saleInfo;
	}
	public void setSaleInfo(SaleInfo saleInfo) {
		this.saleInfo = saleInfo;
	}
	public AccessInfo getAccessInfo() {
		return accessInfo;
	}
	public void setAccessInfo(AccessInfo accessInfo) {
		this.accessInfo = accessInfo;
	}
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}
	public void setSearchInfo(SearchInfo searchInfo) {
		this.searchInfo = searchInfo;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", volumeInfo=" + volumeInfo + ", saleInfo=" + saleInfo + ", accessInfo=" + accessInfo
				+ ", searchInfo=" + searchInfo + "]";
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AccessInfo {
	    private String country;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public AccessInfo() {}
		
		public AccessInfo(String country) {
			super();
			this.country = country;
		}

		@Override
		public String toString() {
			return "AccessInfo [country=" + country + "]";
		}

	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class VolumeInfo {
	    private String title;
	    private List<String> authors;
	    private String publisher;
	    private String publishedDate;
	    private String description;
	    private List<IndustryIdentifier> industryIdentifiers;
	    private List<String> categories;
	    private ImageLinks imageLinks;
	    
		public VolumeInfo(String title, List<String> authors, String publisher, String publishedDate, String description,
				List<IndustryIdentifier> industryIdentifiers, List<String> categories, ImageLinks imageLinks) {
			super();
			this.title = title;
			this.authors = authors;
			this.publisher = publisher;
			this.publishedDate = publishedDate;
			this.description = description;
			this.industryIdentifiers = industryIdentifiers;
			this.categories = categories;
			this.imageLinks = imageLinks;
		}
		
		public VolumeInfo() {}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<String> getAuthors() {
			return authors;
		}
		public void setAuthors(List<String> authors) {
			this.authors = authors;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public String getPublishedDate() {
			return publishedDate;
		}
		public void setPublishedDate(String publishedDate) {
			this.publishedDate = publishedDate;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<IndustryIdentifier> getIndustryIdentifiers() {
			return industryIdentifiers;
		}
		public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
			this.industryIdentifiers = industryIdentifiers;
		}
		public List<String> getCategories() {
			return categories;
		}
		public void setCategories(List<String> categories) {
			this.categories = categories;
		}
		public ImageLinks getImageLinks() {
			return imageLinks;
		}
		public void setImageLinks(ImageLinks imageLinks) {
			this.imageLinks = imageLinks;
		}
		
		@Override
		public String toString() {
			return "VolumeInfo [title=" + title + ", authors=" + authors + ", publisher=" + publisher + ", publishedDate="
					+ publishedDate + ", description=" + description + ", industryIdentifiers=" + industryIdentifiers
					+ ", categories=" + categories + ", imageLinks=" + imageLinks
					+ "]";
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ImageLinks {
		    private String thumbnail;
		    
		    
			public ImageLinks() {
			}


			public ImageLinks(String thumbnail) {
				super();
				this.thumbnail = thumbnail;
			}

			public String getThumbnail() {
				return thumbnail;
			}


			public void setThumbnail(String thumbnail) {
				this.thumbnail = thumbnail;
			}


			@Override
			public String toString() {
				return "ImageLinks [thumbnail=" + thumbnail + "]";
			}
		    

		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class IndustryIdentifier {
		    private String type;
		    private String identifier;
		    
		    public IndustryIdentifier() {}
		    
			public IndustryIdentifier(String type, String identifier) {
				super();
				this.type = type;
				this.identifier = identifier;
			}
			
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getIdentifier() {
				return identifier;
			}
			public void setIdentifier(String identifier) {
				this.identifier = identifier;
			}
			
			@Override
			public String toString() {
				return "IndustryIdentifier [type=" + type + ", identifier=" + identifier + "]";
			}
		    
		    
		}
	    
	
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SaleInfo {
	    private ListPrice listPrice;
	    
	    public SaleInfo() {}
	    
		public SaleInfo(ListPrice listPrice) {
			super();
			this.listPrice = listPrice;
		}

		public ListPrice getListPrice() {
			return listPrice;
		}
		public void setListPrice(ListPrice listPrice) {
			this.listPrice = listPrice;
		}
		
		
		@Override
		public String toString() {
			return "SaleInfo [listPrice=" + listPrice
					+"]";
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class ListPrice {
		    private int amount;
		    private String currencyCode;
		    
		    public ListPrice() {}
		    
			public ListPrice(int amount, String currencyCode) {
				super();
				this.amount = amount;
				this.currencyCode = currencyCode;
			}
			
			
			public int getAmount() {
				return amount;
			}
			public void setAmount(int amount) {
				this.amount = amount;
			}
			public String getCurrencyCode() {
				return currencyCode;
			}
			public void setCurrencyCode(String currencyCode) {
				this.currencyCode = currencyCode;
			}
			
			
			@Override
			public String toString() {
				return "ListPrice [amount=" + amount + ", currencyCode=" + currencyCode + "]";
			}
			
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SearchInfo {
	    private String textSnippet;

		public String getTextSnippet() {
			return textSnippet;
		}

		public void setTextSnippet(String textSnippet) {
			this.textSnippet = textSnippet;
		}

		public SearchInfo() {}
		
		public SearchInfo(String textSnippet) {
			super();
			this.textSnippet = textSnippet;
		}

		@Override
		public String toString() {
			return "SearchInfo [textSnippet=" + textSnippet + "]";
		}

	}


}