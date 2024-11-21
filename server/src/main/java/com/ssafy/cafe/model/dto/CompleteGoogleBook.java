package com.ssafy.cafe.model.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteGoogleBook {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
    private AccessInfo accessInfo;
    private SearchInfo searchInfo;

    // 생성자, getter, setter 메소드

    public CompleteGoogleBook(){}

    public CompleteGoogleBook(String kind, String id, String etag, String selfLink, VolumeInfo volumeInfo, SaleInfo saleInfo, AccessInfo accessInfo, SearchInfo searchInfo) {
        this.kind = kind;
        this.id = id;
        this.etag = etag;
        this.selfLink = selfLink;
        this.volumeInfo = volumeInfo;
        this.saleInfo = saleInfo;
        this.accessInfo = accessInfo;
        this.searchInfo = searchInfo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private ReadingModes readingModes;
        private int pageCount;
        private String printType;
        private List<String> categories;
        private double averageRating;
        private int ratingsCount;
        private String maturityRating;
        private boolean allowAnonLogging;
        private String contentVersion;
        private PanelizationSummary panelizationSummary;
        private ImageLinks imageLinks;
        private String language;
        private String previewLink;
        private String infoLink;
        private String canonicalVolumeLink;

        // 내부 클래스, 생성자, getter, setter 메소드

        public VolumeInfo() {}

        public VolumeInfo(String title, List<String> authors, String publisher, String publishedDate, String description, List<IndustryIdentifier> industryIdentifiers, ReadingModes readingModes, int pageCount, String printType, List<String> categories, double averageRating, int ratingsCount, String maturityRating, boolean allowAnonLogging, String contentVersion, PanelizationSummary panelizationSummary, ImageLinks imageLinks, String language, String previewLink, String infoLink, String canonicalVolumeLink) {
            this.title = title;
            this.authors = authors;
            this.publisher = publisher;
            this.publishedDate = publishedDate;
            this.description = description;
            this.industryIdentifiers = industryIdentifiers;
            this.readingModes = readingModes;
            this.pageCount = pageCount;
            this.printType = printType;
            this.categories = categories;
            this.averageRating = averageRating;
            this.ratingsCount = ratingsCount;
            this.maturityRating = maturityRating;
            this.allowAnonLogging = allowAnonLogging;
            this.contentVersion = contentVersion;
            this.panelizationSummary = panelizationSummary;
            this.imageLinks = imageLinks;
            this.language = language;
            this.previewLink = previewLink;
            this.infoLink = infoLink;
            this.canonicalVolumeLink = canonicalVolumeLink;
        }

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

        public ReadingModes getReadingModes() {
            return readingModes;
        }

        public void setReadingModes(ReadingModes readingModes) {
            this.readingModes = readingModes;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public String getPrintType() {
            return printType;
        }

        public void setPrintType(String printType) {
            this.printType = printType;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public double getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(double averageRating) {
            this.averageRating = averageRating;
        }

        public int getRatingsCount() {
            return ratingsCount;
        }

        public void setRatingsCount(int ratingsCount) {
            this.ratingsCount = ratingsCount;
        }

        public String getMaturityRating() {
            return maturityRating;
        }

        public void setMaturityRating(String maturityRating) {
            this.maturityRating = maturityRating;
        }

        public boolean isAllowAnonLogging() {
            return allowAnonLogging;
        }

        public void setAllowAnonLogging(boolean allowAnonLogging) {
            this.allowAnonLogging = allowAnonLogging;
        }

        public String getContentVersion() {
            return contentVersion;
        }

        public void setContentVersion(String contentVersion) {
            this.contentVersion = contentVersion;
        }

        public PanelizationSummary getPanelizationSummary() {
            return panelizationSummary;
        }

        public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
            this.panelizationSummary = panelizationSummary;
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

        public String getInfoLink() {
            return infoLink;
        }

        public void setInfoLink(String infoLink) {
            this.infoLink = infoLink;
        }

        public String getCanonicalVolumeLink() {
            return canonicalVolumeLink;
        }

        public void setCanonicalVolumeLink(String canonicalVolumeLink) {
            this.canonicalVolumeLink = canonicalVolumeLink;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SaleInfo {
        private String country;
        private String saleability;
        private boolean isEbook;
        private ListPrice listPrice;
        private ListPrice retailPrice;

        // 내부 클래스, 생성자, getter, setter 메소드

        public SaleInfo() {}

        public SaleInfo(String country, String saleability, boolean isEbook, ListPrice listPrice, ListPrice retailPrice) {
            this.country = country;
            this.saleability = saleability;
            this.isEbook = isEbook;
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

        public boolean isEbook() {
            return isEbook;
        }

        public void setEbook(boolean ebook) {
            isEbook = ebook;
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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessInfo {
        private String country;
        private String viewability;
        private boolean embeddable;
        private boolean publicDomain;
        private String textToSpeechPermission;
        private Epub epub;
        private Pdf pdf;
        private String webReaderLink;
        private String accessViewStatus;
        private boolean quoteSharingAllowed;

        // 내부 클래스, 생성자, getter, setter 메소드

        public AccessInfo() {}

        public AccessInfo(String country, String viewability, boolean embeddable, boolean publicDomain, String textToSpeechPermission, Epub epub, Pdf pdf, String webReaderLink, String accessViewStatus, boolean quoteSharingAllowed) {
            this.country = country;
            this.viewability = viewability;
            this.embeddable = embeddable;
            this.publicDomain = publicDomain;
            this.textToSpeechPermission = textToSpeechPermission;
            this.epub = epub;
            this.pdf = pdf;
            this.webReaderLink = webReaderLink;
            this.accessViewStatus = accessViewStatus;
            this.quoteSharingAllowed = quoteSharingAllowed;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getViewability() {
            return viewability;
        }

        public void setViewability(String viewability) {
            this.viewability = viewability;
        }

        public boolean isEmbeddable() {
            return embeddable;
        }

        public void setEmbeddable(boolean embeddable) {
            this.embeddable = embeddable;
        }

        public boolean isPublicDomain() {
            return publicDomain;
        }

        public void setPublicDomain(boolean publicDomain) {
            this.publicDomain = publicDomain;
        }

        public String getTextToSpeechPermission() {
            return textToSpeechPermission;
        }

        public void setTextToSpeechPermission(String textToSpeechPermission) {
            this.textToSpeechPermission = textToSpeechPermission;
        }

        public Epub getEpub() {
            return epub;
        }

        public void setEpub(Epub epub) {
            this.epub = epub;
        }

        public Pdf getPdf() {
            return pdf;
        }

        public void setPdf(Pdf pdf) {
            this.pdf = pdf;
        }

        public String getWebReaderLink() {
            return webReaderLink;
        }

        public void setWebReaderLink(String webReaderLink) {
            this.webReaderLink = webReaderLink;
        }

        public String getAccessViewStatus() {
            return accessViewStatus;
        }

        public void setAccessViewStatus(String accessViewStatus) {
            this.accessViewStatus = accessViewStatus;
        }

        public boolean isQuoteSharingAllowed() {
            return quoteSharingAllowed;
        }

        public void setQuoteSharingAllowed(boolean quoteSharingAllowed) {
            this.quoteSharingAllowed = quoteSharingAllowed;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchInfo {
        private String textSnippet;

        // 생성자, getter, setter 메소드

        public SearchInfo() {}

        public SearchInfo(String textSnippet) {
            this.textSnippet = textSnippet;
        }

        public String getTextSnippet() {
            return textSnippet;
        }

        public void setTextSnippet(String textSnippet) {
            this.textSnippet = textSnippet;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        private String type;
        private String identifier;

        // 생성자, getter, setter 메소드

        public IndustryIdentifier() {}

        public IndustryIdentifier(String type, String identifier) {
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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReadingModes {
        private boolean text;
        private boolean image;

        // 생성자, getter, setter 메소드

        public ReadingModes(){}

        public ReadingModes(boolean text, boolean image) {
            this.text = text;
            this.image = image;
        }

        public boolean isText() {
            return text;
        }

        public void setText(boolean text) {
            this.text = text;
        }

        public boolean isImage() {
            return image;
        }

        public void setImage(boolean image) {
            this.image = image;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PanelizationSummary {
        private boolean containsEpubBubbles;
        private boolean containsImageBubbles;

        // 생성자, getter, setter 메소드

        public PanelizationSummary() {}

        public PanelizationSummary(boolean containsEpubBubbles, boolean containsImageBubbles) {
            this.containsEpubBubbles = containsEpubBubbles;
            this.containsImageBubbles = containsImageBubbles;
        }

        public boolean isContainsEpubBubbles() {
            return containsEpubBubbles;
        }

        public void setContainsEpubBubbles(boolean containsEpubBubbles) {
            this.containsEpubBubbles = containsEpubBubbles;
        }

        public boolean isContainsImageBubbles() {
            return containsImageBubbles;
        }

        public void setContainsImageBubbles(boolean containsImageBubbles) {
            this.containsImageBubbles = containsImageBubbles;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;

        // 생성자, getter, setter 메소드

        public ImageLinks() {}

        public ImageLinks(String smallThumbnail, String thumbnail) {
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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ListPrice {
        private double amount;
        private String currencyCode;

        // 생성자, getter, setter 메소드

        public ListPrice(){}

        public ListPrice(double amount, String currencyCode) {
            this.amount = amount;
            this.currencyCode = currencyCode;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Epub {
        private boolean isAvailable;
        private String acsTokenLink;

        // 생성자, getter, setter 메소드

        public Epub(){}

        public Epub(boolean isAvailable, String acsTokenLink) {
            this.isAvailable = isAvailable;
            this.acsTokenLink = acsTokenLink;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public String getAcsTokenLink() {
            return acsTokenLink;
        }

        public void setAcsTokenLink(String acsTokenLink) {
            this.acsTokenLink = acsTokenLink;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pdf {
        private boolean isAvailable;
        private String acsTokenLink;

        // 생성자, getter, setter 메소드

        public Pdf(){}

        public Pdf(boolean isAvailable, String acsTokenLink) {
            this.isAvailable = isAvailable;
            this.acsTokenLink = acsTokenLink;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public String getAcsTokenLink() {
            return acsTokenLink;
        }

        public void setAcsTokenLink(String acsTokenLink) {
            this.acsTokenLink = acsTokenLink;
        }
    }
}