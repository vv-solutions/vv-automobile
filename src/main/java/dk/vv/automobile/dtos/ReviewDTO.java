package dk.vv.automobile.dtos;

public class ReviewDTO {
    private String email;

    private int productId;
    private String description;

    private int rating;

    public ReviewDTO(String email, int productId, String description, int rating) {
        this.email = email;
        this.productId = productId;
        this.description = description;
        this.rating = rating;
    }

    public ReviewDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
