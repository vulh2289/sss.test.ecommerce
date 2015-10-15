package home.vu.ecommerce.admin.model.result;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.common.model.User;

import java.util.List;

public class UserResult {

    // Query
    private List<User> users;

    // Filters
    private Integer filterId;
    private String filterLoginName;
    private Boolean filterActive;

    // Counts, sorting and pagination object
    private CountsSortingPagination csp;

    public UserResult() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public String getFilterLoginName() {
        return filterLoginName;
    }

    public void setFilterLoginName(String filterLoginName) {
        this.filterLoginName = filterLoginName;
    }

    public Boolean getFilterActive() {
        return filterActive;
    }

    public void setFilterActive(Boolean filterActive) {
        this.filterActive = filterActive;
    }

    public CountsSortingPagination getCsp() {
        return csp;
    }

    public void setCsp(CountsSortingPagination csp) {
        this.csp = csp;
    }
}
