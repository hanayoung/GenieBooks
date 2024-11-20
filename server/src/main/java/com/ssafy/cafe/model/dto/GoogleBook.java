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
	    private String language;
	    private String previewLink;
	    
		public VolumeInfo(String title, List<String> authors, String publisher, String publishedDate, String description,
				List<IndustryIdentifier> industryIdentifiers, List<String> categories, ImageLinks imageLinks,
				String language, String previewLink) {
			super();
			this.title = title;
			this.authors = authors;
			this.publisher = publisher;
			this.publishedDate = publishedDate;
			this.description = description;
			this.industryIdentifiers = industryIdentifiers;
			this.categories = categories;
			this.imageLinks = imageLinks;
			this.language = language;
			this.previewLink = previewLink;
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
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getPreviewLink() {
			return previewLink;
		}
		public void setPreviewLink(String previewLink) {
			this.previewLink = previewLink;
		}
		
		@Override
		public String toString() {
			return "VolumeInfo [title=" + title + ", authors=" + authors + ", publisher=" + publisher + ", publishedDate="
					+ publishedDate + ", description=" + description + ", industryIdentifiers=" + industryIdentifiers
					+ ", categories=" + categories + ", imageLinks=" + imageLinks + ", language=" + language
					+ ", previewLink=" + previewLink + "]";
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ImageLinks {
		    private String smallThumbnail;
		    private String thumbnail;
		    
		    
			public ImageLinks() {
			}


			public ImageLinks(String smallThumbnail, String thumbnail) {
				super();
				this.smallThumbnail = smallThumbnail;
				this.thumbnail = thumbnail;
			}


			public String getSmallThumbnail() {
				return smallThumbnail;
			}


			public void setSmallThumbnail(String smallThumbnail) {
				this.smallThumbnail = smallThumbnail;
			}


			public String getThumbnail() {
				return thumbnail;
			}


			public void setThumbnail(String thumbnail) {
				this.thumbnail = thumbnail;
			}


			@Override
			public String toString() {
				return "ImageLinks [smallThumbnail=" + smallThumbnail + ", thumbnail=" + thumbnail + "]";
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
	    private String country;
	    private String saleability;
	    private ListPrice listPrice;
	    private ListPrice retailPrice;
	    
	    public SaleInfo() {}
	    
		public SaleInfo(String country, String saleability, ListPrice listPrice, ListPrice retailPrice) {
			super();
			this.country = country;
			this.saleability = saleability;
			this.listPrice = listPrice;
			this.retailPrice = retailPrice;
		}
		
		
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getSaleability() {
			return saleability;
		}
		public void setSaleability(String saleability) {
			this.saleability = saleability;
		}
		public ListPrice getListPrice() {
			return listPrice;
		}
		public void setListPrice(ListPrice listPrice) {
			this.listPrice = listPrice;
		}
		public ListPrice getRetailPrice() {
			return retailPrice;
		}
		public void setRetailPrice(ListPrice retailPrice) {
			this.retailPrice = retailPrice;
		}
		
		
		@Override
		public String toString() {
			return "SaleInfo [country=" + country + ", saleability=" + saleability + ", listPrice=" + listPrice
					+ ", retailPrice=" + retailPrice + "]";
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