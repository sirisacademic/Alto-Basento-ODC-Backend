package items;

import java.io.Serializable;
import java.util.List;

public class OrganizationReference implements Serializable {
    String legalName;
    Address address;
    List<Category> categories;

    public OrganizationReference(String legalName, Address address, List<Category> categories) {
        this.legalName = legalName;
        this.address = address;
        this.categories = categories;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
