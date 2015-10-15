package home.vu.ecommerce.admin.model;

public class CountsSortingPagination {

    // Count Fields
    private boolean displayCount; // Boolean to denote if we want to show counts (slower)
    private int count; // Total count of query (number of items)

    // Sort fields
    private boolean sortAsc; // Boolean to denote if we sort ascending
    private String sortItem; // Item to sort on

    // Pagination fields
    private int page; // Page e.g. 5
    private int pages; // Number of pages e.g. 9
    private int rows; // Rows to display per page e.g. 50

    /**
     * Constructor.
     */
    public CountsSortingPagination() {
    }

    // Accessors / mutators

    public int getCount() {
        return count;
    }

    public boolean isDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(boolean displayCount) {
        this.displayCount = displayCount;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(boolean sortAsc) {
        this.sortAsc = sortAsc;
    }

    public String getSortItem() {
        return sortItem;
    }

    public void setSortItem(String sortItem) {
        this.sortItem = sortItem;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    // Additional methods

    /**
     * Return offset which is basically the "page" multipled by the "rows".
     * 
     * @return
     */
    public int getOffset() {
        return page * rows;
    }

    /**
     * Set count and compute pages. Note if count is -1 then pages is also set to -1.
     * 
     * @param count
     */
    public void setCountAndComputePages(int count) {
        this.count = count;
        this.pages = count != -1 ? ((count - 1) / rows) + 1 : -1;
    }
}
